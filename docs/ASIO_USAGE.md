# Asio C++20 模块使用指南

本项目集成了 [DanielaE/asio](https://github.com/DanielaE/asio) 的 C++20 模块版本，提供现代化的异步 I/O 功能。

## 📦 库信息

- **版本**: module 分支
- **仓库**: https://github.com/DanielaE/asio
- **位置**: `3rdparty/asio/`
- **C++ 标准**: C++20 及以上
- **模块文件**: `asio/module/asio.ixx`

## 🚀 快速开始

### 导入模块

```cpp
import asio;
import std;

int main() {
    asio::io_context io;
    // 你的代码...
}
```

### 基本定时器

```cpp
import asio;
import std;

using namespace std::chrono_literals;

int main() {
    asio::io_context io;
    asio::steady_timer timer(io, 1s);
    
    timer.async_wait([](const asio::error_code& ec) {
        if (!ec) {
            std::println("定时器触发！");
        }
    });
    
    io.run();
}
```

## 🌐 TCP Echo 服务器示例

### 服务器端

```cpp
import asio;
import std;

using asio::ip::tcp;

class EchoSession : public std::enable_shared_from_this<EchoSession> {
public:
    EchoSession(tcp::socket socket) : socket_(std::move(socket)) {}
    
    void start() {
        do_read();
    }

private:
    void do_read() {
        auto self(shared_from_this());
        socket_.async_read_some(
            asio::buffer(data_, 1024),
            [this, self](asio::error_code ec, std::size_t length) {
                if (!ec) {
                    do_write(length);
                }
            });
    }
    
    void do_write(std::size_t length) {
        auto self(shared_from_this());
        asio::async_write(
            socket_,
            asio::buffer(data_, length),
            [this, self](asio::error_code ec, std::size_t) {
                if (!ec) {
                    do_read();
                }
            });
    }
    
    tcp::socket socket_;
    char data_[1024];
};

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
                    std::make_shared<EchoSession>(std::move(socket))->start();
                }
                do_accept();
            });
    }
    
    tcp::acceptor acceptor_;
};
```

### 客户端

```cpp
import asio;
import std;

using asio::ip::tcp;

int main() {
    asio::io_context io;
    tcp::socket socket(io);
    
    // 连接到服务器
    tcp::resolver resolver(io);
    asio::connect(socket, resolver.resolve("127.0.0.1", "8888"));
    
    // 发送消息
    std::string message = "Hello, Server!";
    asio::write(socket, asio::buffer(message));
    
    // 接收回显
    char reply[1024];
    size_t length = asio::read(socket, asio::buffer(reply, message.length()));
    
    std::println("回显: {}", std::string_view(reply, length));
}
```

## 📡 UDP Echo 服务器示例

### 服务器端

```cpp
import asio;
import std;

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
                    do_send(bytes_recvd);
                } else {
                    do_receive();
                }
            });
    }
    
    void do_send(std::size_t length) {
        socket_.async_send_to(
            asio::buffer(recv_buffer_, length), remote_endpoint_,
            [this](asio::error_code, std::size_t) {
                do_receive();
            });
    }
    
    udp::socket socket_;
    udp::endpoint remote_endpoint_;
    std::array<char, 1024> recv_buffer_;
};
```

## ⚙️ CMake 配置

项目的 CMakeLists.txt 已经配置好 asio：

```cmake
# 添加 asio 子目录
add_subdirectory(3rdparty/asio)

# 链接 asio 库
target_link_libraries(YourTarget
    PRIVATE
    asio::asio
)

# Windows 平台需要链接网络库
if(WIN32)
    target_link_libraries(YourTarget PUBLIC ws2_32 mswsock)
endif()
```

## 🧪 运行示例和测试

### 运行示例程序

```bash
# 构建项目
cmake -B build
cmake --build build

# 运行 asio echo 示例
./build/examples/example_asio
```

示例包括：
- ✅ TCP Echo 服务器和客户端（回环）
- ✅ 异步 TCP Echo 客户端
- ✅ 多客户端同时连接
- ✅ UDP Echo 服务器和客户端

### 运行测试

```bash
# 运行所有测试
./build/ZhouYiLabTests

# 只运行 asio 测试
./build/ZhouYiLabTests --test-suite="Asio*"
```

测试包括：
- ✅ io_context 基本功能
- ✅ 定时器（同步/异步）
- ✅ post/dispatch 操作
- ✅ TCP Echo 功能（同步/异步）
- ✅ UDP Echo 功能
- ✅ 定时器取消

## 📚 核心概念

### io_context

`io_context` 是 asio 的核心，提供 I/O 功能的执行上下文。

```cpp
asio::io_context io;

// 投递任务
asio::post(io, []() {
    std::println("执行任务");
});

// 运行事件循环
io.run();
```

### 异步操作

所有 `async_*` 函数都是异步的，需要提供回调函数：

```cpp
timer.async_wait([](const asio::error_code& ec) {
    if (!ec) {
        std::println("操作成功");
    }
});
```

### 错误处理

使用 `asio::error_code` 处理错误：

```cpp
timer.async_wait([](const asio::error_code& ec) {
    if (ec) {
        std::println("错误: {}", ec.message());
    }
});
```

## 🔧 常见问题

### Q: 为什么要使用 C++20 模块版本？

A: C++20 模块提供：
- 更快的编译速度
- 更好的封装性
- 避免头文件污染
- 更现代的代码风格

### Q: Windows 上需要额外配置吗？

A: 需要链接 Windows 网络库：

```cmake
if(WIN32)
    target_link_libraries(YourTarget PUBLIC ws2_32 mswsock)
endif()
```

### Q: 如何在多线程中使用？

A: 可以使用 `strand` 或多个 `io_context`：

```cpp
// 使用 strand 保证串行执行
auto strand = asio::make_strand(io);

asio::post(strand, []() {
    // 这些操作会串行执行
});
```

### Q: 如何停止 io_context？

A:

```cpp
io.stop();  // 停止事件循环

// 或者
asio::post(io, [&io]() {
    io.stop();  // 在事件循环中停止
});
```

## 📖 更多资源

- [Asio 官方文档](https://think-async.com/Asio/)
- [Asio C++20 模块分支](https://github.com/DanielaE/asio/tree/module)
- [本项目示例代码](../examples/example_asio.cpp)
- [本项目测试代码](../tests/test_asio.cpp)

## 🎯 使用场景

Asio 适用于：

- 🌐 网络编程（TCP/UDP 服务器/客户端）
- ⏰ 定时器和延迟操作
- 📡 异步 I/O 操作
- 🔀 协程支持（C++20）
- 🚀 高性能服务器开发
- 🔌 串口通信
- 📁 异步文件操作

## 💡 最佳实践

1. **使用智能指针管理会话**：使用 `std::shared_ptr` 和 `enable_shared_from_this`
2. **正确处理错误**：始终检查 `error_code`
3. **避免阻塞操作**：在 `io_context` 线程中使用异步操作
4. **资源管理**：确保在异步操作完成前对象不被销毁
5. **线程安全**：使用 `strand` 或同步原语保证线程安全

---

最后更新：2025-10


