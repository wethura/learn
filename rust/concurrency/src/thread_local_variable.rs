
#[cfg(test)]
mod variable {
    use std::cell::RefCell;
    use std::thread;

    #[test]
    fn test() {
        // Thread Local Variable, the value would bind to the thread
        thread_local!(static FOO: RefCell<u32> = RefCell::new(1));

        FOO.with(|f| {
            assert_eq!(*f.borrow(), 1);
            *f.borrow_mut() = 3;
        });

        // sub thread might not affect the main thread
        let t = thread::spawn(move || {
            FOO.with(|f| {
                assert_eq!(*f.borrow(), 1);
                *f.borrow_mut() = 4;
            })
        });

        t.join().unwrap();

        FOO.with(|f| {
            assert_eq!(*f.borrow(), 3);
        })

    }
}

#[cfg(test)]
mod third_part {
    use std::cell::Cell;
    use std::sync::Arc;
    use std::thread;
    use thread_local::ThreadLocal;

    #[test]
    fn test() {
        let tls = Arc::new(ThreadLocal::new());
        let mut v = vec![];

        for _ in 0..5 {
            let t = tls.clone();
            let handle = thread::spawn(move || {
                let x = t.get_or(|| Cell::new(0));
                x.set(x.get() + 1);
            });
            v.push(handle);
        }

        assert_eq!(v.len(), 5);
        for x in v {
            x.join().unwrap();
        }

        let tls = Arc::try_unwrap(tls).unwrap();
        let total = tls.into_iter().fold(0, |x, y| {
            println!("x: {}, y: {}", x, y.get());
            x + y.get()
        });

        assert_eq!(total, 5);
    }
}

#[cfg(test)]
mod reference_thread_local {
    use std::cell::RefCell;
    use std::thread::LocalKey;
    thread_local! {
        static FOO: RefCell<u32> = RefCell::new(0);
    }

    struct Bar {
        ref_thread_local_variable: &'static LocalKey<RefCell<u32>>,
    }

    impl Bar {
        fn constructor() -> Self {
            Self{
                ref_thread_local_variable: &FOO
            }
        }
    }

    #[test]
    fn test() {
        let bar = Bar::constructor();

        bar.ref_thread_local_variable.with(|f|{
            println!("variable: {}", f.borrow());
        });

        bar.ref_thread_local_variable.with(|f| {
            let mut mut_f = f.borrow_mut();
            *mut_f = 3;
        });

        bar.ref_thread_local_variable.with(|f|{
            println!("variable: {}", f.borrow());
        });
    }
}
