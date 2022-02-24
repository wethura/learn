# 责任链模式

**行为型模式**

> 为请求创建一个接受者对象的链。这个模式给予请求的类型，对请求的发送者和接受者进行解耦。

## 目的

避免请求发送者和接受者耦合在一起，让多个对象都可能接受请求，将请求组成一条链，并且沿着链传递请求，直到有对象请求结束为止。
