// Asio 异步 I/O 库测试
#include <doctest/doctest.h>

import asio;
import std;

using asio::ip::tcp;
using asio::ip::udp;
using namespace std::chrono_literals;

TEST_SUITE("Asio 基础功能测试") {
    
    TEST_CASE("io_context 基本功能") {
        SUBCASE("创建 io_context") {
            asio::io_context io;
            CHECK(io.stopped() == false);
        }
        
        SUBCASE("运行空的 io_context") {
            asio::io_context io;
            auto count = io.run();
            CHECK(count == 0);
            CHECK(io.stopped() == true);
        }
    }
    
    TEST_CASE("steady_timer 定时器测试") {
        SUBCASE("同步等待定时器") {
            asio::io_context io;
            asio::steady_timer timer(io, 50ms);
            
            auto start = std::chrono::steady_clock::now();
            timer.wait();
            auto end = std::chrono::steady_clock::now();
            
            auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
            CHECK(duration.count() >= 50);
            CHECK(duration.count() < 150); // 允许一些误差
        }
        
        SUBCASE("异步等待定时器") {
            asio::io_context io;
            asio::steady_timer timer(io, 50ms);
            
            bool completed = false;
            auto start = std::chrono::steady_clock::now();
            
            timer.async_wait([&](const asio::error_code& ec) {
                CHECK(!ec);
                completed = true;
            });
            
            CHECK(completed == false); // 异步操作还未完成
            
            io.run();
            
            CHECK(completed == true); // 异步操作已完成
            auto end = std::chrono::steady_clock::now();
            auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
            CHECK(duration.count() >= 50);
        }
    }
    
    TEST_CASE("定时器取消") {
        SUBCASE("取消未完成的定时器") {
            asio::io_context io;
            asio::steady_timer timer(io, 1s); // 长时间定时器
            
            bool cancelled = false;
            
            timer.async_wait([&](const asio::error_code& ec) {
                if (ec == asio::error::operation_aborted) {
                    cancelled = true;
                }
            });
            
            // 立即取消定时器
            timer.cancel();
            
            io.run();
            
            CHECK(cancelled == true);
        }
    }
    
    TEST_CASE("post 操作") {
        SUBCASE("立即执行任务") {
            asio::io_context io;
            
            bool executed = false;
            
            asio::post(io, [&]() {
                executed = true;
            });
            
            CHECK(executed == false); // 还未运行
            
            io.run();
            
            CHECK(executed == true);
        }
        
        SUBCASE("多个 post 任务按顺序执行") {
            asio::io_context io;
            
            std::vector<int> execution_order;
            
            asio::post(io, [&]() { execution_order.push_back(1); });
            asio::post(io, [&]() { execution_order.push_back(2); });
            asio::post(io, [&]() { execution_order.push_back(3); });
            
            io.run();
            
            REQUIRE(execution_order.size() == 3);
            CHECK(execution_order[0] == 1);
            CHECK(execution_order[1] == 2);
            CHECK(execution_order[2] == 3);
        }
    }
}

TEST_SUITE("Asio TCP Echo 测试") {
    
    // 简单的同步 Echo 服务器
    class SyncEchoServer {
    public:
        SyncEchoServer(asio::io_context& io, unsigned short port)
            : acceptor_(io, tcp::endpoint(tcp::v4(), port)) {}
        
        void accept_one() {
            tcp::socket socket(acceptor_.get_executor());
            acceptor_.accept(socket);
            
            char data[1024];
            asio::error_code ec;
            size_t length = socket.read_some(asio::buffer(data), ec);
            
            if (!ec) {
                asio::write(socket, asio::buffer(data, length));
            }
        }
        
    private:
        tcp::acceptor acceptor_;
    };
    
    TEST_CASE("TCP 回环连接") {
        SUBCASE("基本连接测试") {
            asio::io_context io;
            
            // 创建服务器
            tcp::acceptor acceptor(io, tcp::endpoint(tcp::v4(), 0));
            auto port = acceptor.local_endpoint().port();
            
            bool accepted = false;
            tcp::socket server_socket(io);
            
            acceptor.async_accept(server_socket, [&](asio::error_code ec) {
                accepted = !ec;
            });
            
            // 创建客户端
            tcp::socket client_socket(io);
            client_socket.async_connect(
                tcp::endpoint(asio::ip::address_v4::loopback(), port),
                [](asio::error_code ec) {
                    CHECK(!ec);
                });
            
            io.run();
            
            CHECK(accepted == true);
            CHECK(client_socket.is_open() == true);
            CHECK(server_socket.is_open() == true);
        }
    }
    
    TEST_CASE("TCP Echo 功能") {
        SUBCASE("简单 echo 测试") {
            const unsigned short port = 12345;
            
            asio::io_context server_io;
            SyncEchoServer server(server_io, port);
            
            // 在单独线程运行服务器
            std::thread server_thread([&server]() {
                server.accept_one();
            });
            
            // 给服务器时间启动
            std::this_thread::sleep_for(50ms);
            
            // 客户端连接并发送数据
            asio::io_context client_io;
            tcp::socket socket(client_io);
            
            tcp::resolver resolver(client_io);
            auto endpoints = resolver.resolve("127.0.0.1", std::to_string(port));
            asio::connect(socket, endpoints);
            
            // 发送消息
            std::string message = "Hello, Echo!";
            asio::write(socket, asio::buffer(message));
            
            // 接收回显
            char reply[1024];
            size_t reply_length = asio::read(socket, asio::buffer(reply, message.length()));
            
            CHECK(reply_length == message.length());
            CHECK(std::string(reply, reply_length) == message);
            
            server_thread.join();
        }
        
        SUBCASE("中文消息 echo 测试") {
            const unsigned short port = 12346;
            
            asio::io_context server_io;
            SyncEchoServer server(server_io, port);
            
            std::thread server_thread([&server]() {
                server.accept_one();
            });
            
            std::this_thread::sleep_for(50ms);
            
            asio::io_context client_io;
            tcp::socket socket(client_io);
            
            tcp::resolver resolver(client_io);
            auto endpoints = resolver.resolve("127.0.0.1", std::to_string(port));
            asio::connect(socket, endpoints);
            
            std::string message = "测试中文消息";
            asio::write(socket, asio::buffer(message));
            
            char reply[1024];
            size_t reply_length = asio::read(socket, asio::buffer(reply, message.length()));
            
            CHECK(reply_length == message.length());
            CHECK(std::string(reply, reply_length) == message);
            
            server_thread.join();
        }
    }
}

TEST_SUITE("Asio UDP Echo 测试") {
    
    class SyncUdpEchoServer {
    public:
        SyncUdpEchoServer(asio::io_context& io, unsigned short port)
            : socket_(io, udp::endpoint(udp::v4(), port)) {}
        
        void echo_once() {
            char data[1024];
            udp::endpoint remote_endpoint;
            
            size_t length = socket_.receive_from(
                asio::buffer(data), remote_endpoint);
            
            socket_.send_to(
                asio::buffer(data, length), remote_endpoint);
        }
        
    private:
        udp::socket socket_;
    };
    
    TEST_CASE("UDP Echo 功能") {
        SUBCASE("基本 UDP echo") {
            const unsigned short port = 12347;
            
            asio::io_context server_io;
            SyncUdpEchoServer server(server_io, port);
            
            std::thread server_thread([&server]() {
                server.echo_once();
            });
            
            std::this_thread::sleep_for(50ms);
            
            // 客户端
            asio::io_context client_io;
            udp::socket socket(client_io, udp::endpoint(udp::v4(), 0));
            
            udp::resolver resolver(client_io);
            udp::endpoint receiver_endpoint = 
                *resolver.resolve(udp::v4(), "127.0.0.1", std::to_string(port)).begin();
            
            std::string message = "UDP Test";
            socket.send_to(asio::buffer(message), receiver_endpoint);
            
            char reply[1024];
            udp::endpoint sender_endpoint;
            size_t reply_length = socket.receive_from(
                asio::buffer(reply), sender_endpoint);
            
            CHECK(reply_length == message.length());
            CHECK(std::string(reply, reply_length) == message);
            
            server_thread.join();
        }
    }
}

TEST_SUITE("Asio 异步操作测试") {
    
    TEST_CASE("异步 TCP echo") {
        SUBCASE("异步读写") {
            const unsigned short port = 12348;
            
            asio::io_context io;
            tcp::acceptor acceptor(io, tcp::endpoint(tcp::v4(), port));
            
            bool echo_done = false;
            
            // 异步接受连接
            tcp::socket server_socket(io);
            acceptor.async_accept(server_socket, [&](asio::error_code ec) {
                if (!ec) {
                    auto buffer = std::make_shared<std::array<char, 1024>>();
                    
                    // 异步读
                    server_socket.async_read_some(
                        asio::buffer(*buffer),
                        [&, buffer](asio::error_code ec, std::size_t length) {
                            if (!ec) {
                                // 异步写（回显）
                                asio::async_write(
                                    server_socket,
                                    asio::buffer(*buffer, length),
                                    [&](asio::error_code ec, std::size_t) {
                                        echo_done = !ec;
                                    });
                            }
                        });
                }
            });
            
            // 客户端
            tcp::socket client_socket(io);
            client_socket.async_connect(
                tcp::endpoint(asio::ip::address_v4::loopback(), port),
                [&](asio::error_code ec) {
                    if (!ec) {
                        auto msg = std::make_shared<std::string>("Async Test");
                        asio::async_write(
                            client_socket,
                            asio::buffer(*msg),
                            [](asio::error_code, std::size_t) {});
                    }
                });
            
            io.run();
            
            CHECK(echo_done == true);
        }
    }
}
