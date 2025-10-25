// Asio TCP Echo 服务器示例
// 演示如何使用 C++20 模块版本的 asio 库创建回环 echo 服务

import asio;
import std;

using asio::ip::tcp;
using namespace std::chrono_literals;

// ============= Echo 会话类 =============
class EchoSession : public std::enable_shared_from_this<EchoSession> {
public:
    EchoSession(tcp::socket socket)
        : socket_(std::move(socket)) {}
    
    void start() {
        do_read();
    }

private:
    void do_read() {
        auto self(shared_from_this());
        socket_.async_read_some(
            asio::buffer(data_, max_length),
            [this, self](asio::error_code ec, std::size_t length) {
                if (!ec) {
                    std::println("  [服务器] 接收到 {} 字节: {}", 
                        length, std::string_view(data_, length));
                    do_write(length);
                }
            });
    }
    
    void do_write(std::size_t length) {
        auto self(shared_from_this());
        asio::async_write(
            socket_,
            asio::buffer(data_, length),
            [this, self](asio::error_code ec, std::size_t /*length*/) {
                if (!ec) {
                    do_read();
                }
            });
    }
    
    tcp::socket socket_;
    static constexpr std::size_t max_length = 1024;
    char data_[max_length];
};

// ============= Echo 服务器类 =============
class EchoServer {
public:
    EchoServer(asio::io_context& io_context, unsigned short port)
        : acceptor_(io_context, tcp::endpoint(tcp::v4(), port)) {
        do_accept();
    }

private:
    void do_accept() {
        acceptor_.async_accept(
            [this](asio::error_code ec, tcp::socket socket) {
                if (!ec) {
                    std::println("  [服务器] 接受新连接");
                    std::make_shared<EchoSession>(std::move(socket))->start();
                }
                do_accept();
            });
    }
    
    tcp::acceptor acceptor_;
};

// ============= Echo 客户端类 =============
class EchoClient {
public:
    EchoClient(asio::io_context& io_context, 
               const std::string& host, 
               const std::string& port)
        : socket_(io_context) {
        
        tcp::resolver resolver(io_context);
        auto endpoints = resolver.resolve(host, port);
        
        asio::connect(socket_, endpoints);
        std::println("  [客户端] 已连接到服务器");
    }
    
    void send(const std::string& message) {
        std::println("  [客户端] 发送: {}", message);
        asio::write(socket_, asio::buffer(message));
        
        char reply[1024];
        size_t reply_length = asio::read(socket_, 
            asio::buffer(reply, message.length()));
        
        std::println("  [客户端] 接收回显: {}", 
            std::string_view(reply, reply_length));
    }
    
private:
    tcp::socket socket_;
};

// ============= 示例函数 =============

void simple_echo_example() {
    std::println("\n╔═══════════════════════════════════════════╗");
    std::println("║   示例 1: 简单的 Echo 服务器和客户端    ║");
    std::println("╚═══════════════════════════════════════════╝\n");
    
    try {
        asio::io_context io_context;
        
        // 启动服务器（监听 localhost:8888）
        std::println("✓ 启动 Echo 服务器，监听端口 8888...");
        EchoServer server(io_context, 8888);
        
        // 在单独的线程中运行 io_context
        std::thread server_thread([&io_context]() {
            io_context.run();
        });
        
        // 等待服务器启动
        std::this_thread::sleep_for(100ms);
        
        // 创建客户端并发送消息
        std::println("\n✓ 创建客户端连接到 localhost:8888...");
        asio::io_context client_io;
        EchoClient client(client_io, "127.0.0.1", "8888");
        
        // 发送多条消息
        client.send("Hello, Asio!");
        client.send("测试中文消息");
        client.send("C++20 Modules");
        
        std::println("\n✅ Echo 测试完成！");
        
        // 停止服务器
        io_context.stop();
        server_thread.join();
        
    } catch (const std::exception& e) {
        std::println("❌ 错误: {}", e.what());
    }
}

void async_echo_client_example() {
    std::println("\n╔═══════════════════════════════════════════╗");
    std::println("║   示例 2: 异步 Echo 客户端              ║");
    std::println("╚═══════════════════════════════════════════╝\n");
    
    class AsyncEchoClient {
    public:
        AsyncEchoClient(asio::io_context& io, const std::string& host, const std::string& port)
            : socket_(io) {
            tcp::resolver resolver(io);
            auto endpoints = resolver.resolve(host, port);
            
            asio::async_connect(socket_, endpoints,
                [this](asio::error_code ec, tcp::endpoint) {
                    if (!ec) {
                        std::println("  [异步客户端] 已连接");
                    }
                });
        }
        
        void send(const std::string& message) {
            std::println("  [异步客户端] 发送: {}", message);
            
            auto msg = std::make_shared<std::string>(message);
            asio::async_write(socket_, asio::buffer(*msg),
                [this, msg](asio::error_code ec, std::size_t) {
                    if (!ec) {
                        do_read();
                    }
                });
        }
        
    private:
        void do_read() {
            auto buf = std::make_shared<std::array<char, 1024>>();
            socket_.async_read_some(asio::buffer(*buf),
                [this, buf](asio::error_code ec, std::size_t length) {
                    if (!ec) {
                        std::println("  [异步客户端] 接收回显: {}", 
                            std::string_view(buf->data(), length));
                    }
                });
        }
        
        tcp::socket socket_;
    };
    
    try {
        // 启动服务器
        asio::io_context server_io;
        std::println("✓ 启动 Echo 服务器，监听端口 8889...");
        EchoServer server(server_io, 8889);
        
        std::thread server_thread([&server_io]() {
            server_io.run();
        });
        
        std::this_thread::sleep_for(100ms);
        
        // 异步客户端
        asio::io_context client_io;
        std::println("\n✓ 创建异步客户端...");
        
        AsyncEchoClient client(client_io, "127.0.0.1", "8889");
        
        // 等待连接完成
        std::this_thread::sleep_for(100ms);
        
        // 发送消息
        client.send("异步消息 1");
        client.send("异步消息 2");
        client.send("异步消息 3");
        
        // 运行客户端 io_context
        client_io.run();
        
        std::println("\n✅ 异步 Echo 测试完成！");
        
        server_io.stop();
        server_thread.join();
        
    } catch (const std::exception& e) {
        std::println("❌ 错误: {}", e.what());
    }
}

void multiple_clients_example() {
    std::println("\n╔═══════════════════════════════════════════╗");
    std::println("║   示例 3: 多个客户端同时连接            ║");
    std::println("╚═══════════════════════════════════════════╝\n");
    
    try {
        // 启动服务器
        asio::io_context server_io;
        std::println("✓ 启动 Echo 服务器，监听端口 8890...");
        EchoServer server(server_io, 8890);
        
        std::thread server_thread([&server_io]() {
            server_io.run();
        });
        
        std::this_thread::sleep_for(100ms);
        
        // 创建多个客户端
        std::vector<std::thread> client_threads;
        
        for (int i = 1; i <= 3; ++i) {
            client_threads.emplace_back([i]() {
                try {
                    asio::io_context io;
                    EchoClient client(io, "127.0.0.1", "8890");
                    
                for (int j = 1; j <= 2; ++j) {
                    // 使用字符串拼接避免 MSVC 格式字符串常量表达式问题
                    std::string msg = "客户端 " + std::to_string(i) + " 的消息 " + std::to_string(j);
                    client.send(msg);
                    std::this_thread::sleep_for(50ms);
                }
                } catch (const std::exception& e) {
                    std::println("  客户端 {} 错误: {}", i, e.what());
                }
            });
        }
        
        // 等待所有客户端完成
        for (auto& t : client_threads) {
            t.join();
        }
        
        std::println("\n✅ 多客户端测试完成！");
        
        server_io.stop();
        server_thread.join();
        
    } catch (const std::exception& e) {
        std::println("❌ 错误: {}", e.what());
    }
}

void udp_echo_example() {
    std::println("\n╔═══════════════════════════════════════════╗");
    std::println("║   示例 4: UDP Echo 服务器               ║");
    std::println("╚═══════════════════════════════════════════╝\n");
    
    using asio::ip::udp;
    
    class UdpEchoServer {
    public:
        UdpEchoServer(asio::io_context& io, unsigned short port)
            : socket_(io, udp::endpoint(udp::v4(), port)) {
            do_receive();
        }
        
    private:
        void do_receive() {
            socket_.async_receive_from(
                asio::buffer(recv_buffer_), remote_endpoint_,
                [this](asio::error_code ec, std::size_t bytes_recvd) {
                    if (!ec && bytes_recvd > 0) {
                        std::println("  [UDP服务器] 接收: {}", 
                            std::string_view(recv_buffer_.data(), bytes_recvd));
                        do_send(bytes_recvd);
                    } else {
                        do_receive();
                    }
                });
        }
        
        void do_send(std::size_t length) {
            socket_.async_send_to(
                asio::buffer(recv_buffer_, length), remote_endpoint_,
                [this](asio::error_code /*ec*/, std::size_t /*bytes_sent*/) {
                    do_receive();
                });
        }
        
        udp::socket socket_;
        udp::endpoint remote_endpoint_;
        std::array<char, 1024> recv_buffer_;
    };
    
    try {
        asio::io_context server_io;
        std::println("✓ 启动 UDP Echo 服务器，监听端口 8891...");
        UdpEchoServer server(server_io, 8891);
        
        std::thread server_thread([&server_io]() {
            server_io.run();
        });
        
        std::this_thread::sleep_for(100ms);
        
        // UDP 客户端
        asio::io_context client_io;
        udp::socket socket(client_io, udp::endpoint(udp::v4(), 0));
        
        udp::resolver resolver(client_io);
        udp::endpoint receiver_endpoint = 
            *resolver.resolve(udp::v4(), "127.0.0.1", "8891").begin();
        
        std::println("\n✓ 发送 UDP 消息...");
        
        std::string messages[] = {"UDP消息1", "UDP消息2", "UDP消息3"};
        for (const auto& msg : messages) {
            std::println("  [UDP客户端] 发送: {}", msg);
            socket.send_to(asio::buffer(msg), receiver_endpoint);
            
            std::array<char, 1024> reply;
            udp::endpoint sender_endpoint;
            size_t reply_length = socket.receive_from(
                asio::buffer(reply), sender_endpoint);
            
            std::println("  [UDP客户端] 接收回显: {}", 
                std::string_view(reply.data(), reply_length));
            
            std::this_thread::sleep_for(50ms);
        }
        
        std::println("\n✅ UDP Echo 测试完成！");
        
        server_io.stop();
        server_thread.join();
        
    } catch (const std::exception& e) {
        std::println("❌ 错误: {}", e.what());
    }
}

// ============= 主函数 =============

int main() {
    std::println("╔═══════════════════════════════════════════════════╗");
    std::println("║   Asio 回环 Echo 服务测试 - C++20 模块版本      ║");
    std::println("║   TCP/UDP Echo Server & Client Examples          ║");
    std::println("╚═══════════════════════════════════════════════════╝");
    
    try {
        // 运行所有示例
        simple_echo_example();
        std::this_thread::sleep_for(200ms);
        
        async_echo_client_example();
        std::this_thread::sleep_for(200ms);
        
        multiple_clients_example();
        std::this_thread::sleep_for(200ms);
        
        udp_echo_example();
        
        std::println("\n==================================================");
        std::println("✅ 所有 Echo 测试完成！");
        std::println("==================================================\n");
        
    } catch (const std::exception& e) {
        std::println("\n❌ 程序错误: {}", e.what());
        return 1;
    }
    
    return 0;
}
