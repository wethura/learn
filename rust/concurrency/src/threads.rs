#[cfg(test)]
mod threads {
    use std::thread;
    use std::time::Duration;

    #[test]
    fn test_echo_num() {
        thread::spawn(|| {
            for i in 1..10 {
                println!("sub num: {}", i);
                thread::sleep(Duration::from_millis(1));
            }
        });

        for i in 1..5 {
            println!("main num: {}", i);
            thread::sleep(Duration::from_millis(1));
        }
    }

    #[test]
    fn test_wait_sub_thread() {
        let join_handle = thread::spawn(|| {
            for i in 1..10 {
                println!("sub num: {}", i);
                thread::sleep(Duration::from_millis(1));
            }
        });

        join_handle.join().unwrap();

        for i in 1..5 {
            println!("main num: {}", i);
            thread::sleep(Duration::from_millis(1));
        }
    }

    #[test]
    fn test_move_ownership() {
        let v = vec![1, 2, 3];

        let handle = thread::spawn(move || {
            println!("vec: {:?}", v);
        });

        handle.join().unwrap();

        // vec 所有权被转移走了, 且没有归还所有权, 则无法访问.
        // println!("vec: {:?}", v);
    }
}