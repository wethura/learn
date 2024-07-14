#[cfg(test)]
mod message_passing {
    use std::sync::mpsc;
    use std::thread;
    use std::time::Duration;

    #[test]
    fn test_sync_recv() {
        let (tx, rx) = mpsc::channel();

        let sender01 = tx.clone();
        thread::spawn(move || {
            thread::sleep(Duration::from_secs(1));
            sender01.send(1).unwrap();

            // 类型推断在上一行代码已经确定类型了, 则在这一行再传其余类型则会导致编译失败.
            // tx.send(Some(1)).unwrap();
        });

        let sender02 = tx.clone();
        thread::spawn(move || {
            thread::sleep(Duration::from_secs(3));
            sender02.send(2).unwrap();
        });

        println!("rec: {}", rx.recv().unwrap());
        println!("rec: {}", rx.recv().unwrap());
    }

    #[test]
    fn test_try_recv() {
        let (tx, rx) = mpsc::channel();
        thread::spawn(move || {
            thread::sleep(Duration::from_millis(10));
            tx.send(1).unwrap();
        });

        // 接受子线程传递过来的参数，接受到 Err(Empty) 意味着该管道未关闭且未传递消息过来
        println!("recv: {:?}", rx.try_recv());
        thread::sleep(Duration::from_millis(50));
        println!("recv: {:?}", rx.try_recv());
        // Disconnected: sender 在子线程结束后被回收了, 释放后则可观察到通道已经被关闭了
        println!("recv: {:?}", rx.try_recv());
    }

    #[test]
    fn test_transfer_message() {
        let (tx, rx) = mpsc::channel();
        thread::spawn(move || {
            let str = String::from("传输具有所有权的数据且没有实现Copy的参数");
            tx.send(str).unwrap();
            // str 参数被传输到主线程之后, 则该参数的所有权被转移走了，后面就没有办法获取到参数的所有权了。
            // println!("val: {}", str);
        });

        println!("recv: {:?}", rx.recv());
    }
}