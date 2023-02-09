# None Block IO model

```txt
当用户线程发起一个IO操作后，并不需要等待，而是马上就得到了一个结果。如果结果是一个error时，它就知道数据还没有准备好，于是它可以再次发送IO操作。
一旦内核中的数据准备好了，并且又再次收到了用户线程的请求，那么它马上就将数据拷贝到了用户线程，然后返回。
在非阻塞IO模型中，用户线程需要不断地询问内核数据是否就绪，也就说非阻塞IO不会交出CPU，而会一直占用CPU。
```

## 时序图

```mermaid
sequenceDiagram
    application ->> kernel: fread
    kernel ->> kernel: 数据未就绪
    kernel ->> application: 返回 eagain
    application ->> application: 判断返回值
    application ->> kernel: fread
    kernel ->> kernel: 准备好数据, 拷贝至用户空间
    kernel ->> application: 拷贝完成, 返回成功
```