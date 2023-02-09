# 网络编程

## LINUX 的5种网络IO模型

1. [阻塞IO](./block_io.md)  
  这是最简单的模型,一般配合多线程来实现.
2. [非阻塞IO](./non_block_io.md)  
  这是最简单的模型,一般配合多线程来实现.
3. [多路复用select/poll/epoll](./signal_drive_io.md)  
  一个线程解决多个链接的问题
4. [信号驱动IO模型]()  
  一种同步IO, 更加灵活
5. [异步IO模型]()  
  高效主流的模型, 效率高