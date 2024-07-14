
#[cfg(test)]
mod thread_login_control {
    use std::sync::{Arc, Condvar, Mutex};
    use std::thread;

    #[test]
    fn test() {
        let pair = Arc::new((Mutex::new(false), Condvar::new()));
        let cloned_pair = pair.clone();

        thread::spawn(move || {
            let (lock, cvar) = &*cloned_pair;

            let mut guard = lock.lock().unwrap();
            println!("changing guard");

            *guard = true;
            cvar.notify_one();
        });

        let (lock, cvar) = &*pair;
        let mut guard = lock.lock().unwrap();
        while !*guard {
            println!("waiting guard");
            guard = cvar.wait(guard).unwrap();
        }

        println!("guard changed");
    }
}

#[cfg(test)]
mod call_once {
    use std::sync::Once;
    use std::thread;

    static mut VAL: usize = 0;
    static INIT: Once = Once::new();

    #[test]
    fn test() {
        let h1 = thread::spawn(move || {
            // thread::sleep(Duration::from_millis(1));
            INIT.call_once(|| {
                println!("call 1");
                unsafe {
                    VAL = 1;
                }
            });
        });

        let h2 = thread::spawn(move || {
            INIT.call_once(|| {
                println!("call 2");
                unsafe {
                    VAL = 2;
                }
            });
        });

        h1.join().unwrap();
        h2.join().unwrap();

        println!("{}", unsafe{VAL});
    }
}

#[cfg(test)]
mod thread_barrier {
    use std::sync::{Arc, Barrier};
    use std::thread;

    #[test]
    fn test() {
        let mut handlers = Vec::with_capacity(6);
        let barrier = Arc::new(Barrier::new(6));

        for _ in 0..6 {
            let b = barrier.clone();
            handlers.push(thread::spawn(move || {
                println!("before");
                // 线程屏障: 所有的线程都会等所有线程都打印before之后才会往下继续执行.
                b.wait();
                println!("after");
            }));
        }

        for handler in handlers {
            handler.join().unwrap();
        }

        println!("all 6 thread finished");
    }
}