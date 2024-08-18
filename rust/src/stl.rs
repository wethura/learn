
#[cfg(test)]
mod vector {
    use std::cmp::Ordering;

    #[test]
    fn test() {
        let mut v01 = Vec::new();
        v01.push(1);
        v01.push(2);
        v01.push(3);
        println!("{:?}", v01);

        let mut v02 = Vec::with_capacity(3);
        v02.push(0);
        v02.push(1);
        v02.push(2);
        v02.push(3);
        v02.push(4);
        println!("{:?}", v02);

        println!("{:?}", vec![1, 2, 3, 4, 5]);

        println!("length: {:?}, element[1]: {:?}.", v02.len(), v02.get(1).unwrap());

        // 测试借用元素; 由于是借用, 所以这里可以借用俩个元素
        let x_1 = &v02[1];
        let x_2 = &v02[2];
        println!("length: {:?}, element[1]: {:?}.", v02.len(), x_1);
        println!("length: {:?}, element[2]: {:?}.", v02.len(), x_2);

        // 重新操作 v02 的时候由于拿到了可变权变, x_1, x_2 的权限就理应被归还了.
        v02.push(5);

        // 所以在这里操作 x 的时候发生了借用权限的问题.
        // println!("length: {:?}, element[1]: {:?}.", v02.len(), x);

        // 但是重新从数组里面获取出来是没有问题的.
        println!("length: {:?}, element[1]: {:?}.", v02.len(), &v02[1]);

        // 通过指针操作数组中的元素
        for e in &mut v02 {
            *e *= 2;
        }
        println!("{:?}", v02);
    }

    #[test]
    fn test_sort() {
        let mut v = vec![1, 3, 2, 5, 4];
        v.sort_by(|a, b| {
            if a < b {
                std::cmp::Ordering::Less
            } else {
                std::cmp::Ordering::Greater
            }
        });

        println!("{:?}", v);
    }

    #[derive(Debug)]
    struct Point {
        x: i32,
        y: i32,
    }

    impl Eq for Point {}

    impl PartialEq<Self> for Point {
        fn eq(&self, other: &Self) -> bool {
            self.x == other.x && self.y == other.y
        }
    }

    impl PartialOrd<Self> for Point {
        fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
            if self.x < other.x {
                return Some(Ordering::Less);
            } else if self.x > other.x {
                return Some(Ordering::Greater);
            } else {
                return Some(Ordering::Equal);
            }
        }
    }

    impl Ord for Point {
        fn cmp(&self, other: &Self) -> Ordering {
            if self.x < other.x {
                return Ordering::Less;
            } else if self.x > other.x {
                return Ordering::Greater;
            } else {
                return Ordering::Equal;
            }
        }
    }

    #[test]
    fn test_sort_structure() {
        let mut v = Vec::with_capacity(10);
        v.push(Point { x: 3, y: 2 });
        v.push(Point { x: 2, y: 2 });
        v.push(Point { x: 6, y: 2 });
        v.push(Point { x: 4, y: 2 });
        v.push(Point { x: 5, y: 2 });
        v.push(Point { x: 1, y: 2 });
        v.push(Point { x: 8, y: 2 });
        v.push(Point { x: 10, y: 2 });
        v.push(Point { x: 7, y: 2 });
        v.push(Point { x: 9, y: 2 });

        v.sort();

        println!("{:?}", v);
    }

    #[derive(Debug)]
    enum IpAddr {
        V4(String),
        V6(String),
    }

    #[test]
    fn test_diff_element() {
        let v = vec![IpAddr::V4("127.0.0.1".to_string()), IpAddr::V6("::1".to_string())];
        for e in &v {
            println!("{:?}", e);
        }
        for e in &v {
            match e {
                IpAddr::V4(s) => println!("{}", s),
                IpAddr::V6(s) => println!("{}", s),
            }
        }
    }
}

#[cfg(test)]
mod hashmap {
    use std::collections::HashMap;

    #[test]
    fn test() {
        use std::collections::HashMap;

        let mut scores = HashMap::new();

        scores.insert(String::from("Blue"), 10);
        scores.insert(String::from("Yellow"), 50);

        let team_name = String::from("Blue");
        let score = scores.get(&team_name).copied().unwrap_or(0);
        println!("{:?}", score);

        for (key, value) in &scores {
            println!("{:?} {:?}", key, value);
        }
    }

    #[test]
    fn test_move_out_privilege() {
        use std::collections::HashMap;

        let mut handsome_boys = HashMap::new();

        let zk = String::from("Zookeeper");
        handsome_boys.insert(&zk, 20);

        println!("{:?}", handsome_boys);
        drop(zk);

        // 无法运行一下的命令, 由于zk 已经被释放了.
        // println!("{:?}", handsome_boys);
    }

    #[warn(dropping_copy_types)]
    #[derive(Clone, Eq, PartialEq,Hash,Debug,Copy)]
    struct CloneableI32 {
        data :i32,
    }

    #[test]
    fn test_move_out_cloneable_privilege() {
        let zk = CloneableI32 { data: 36 };
        let rb = CloneableI32 { data: 40 };

        let mut cool_plugin = HashMap::new();

        cool_plugin.insert(&zk, 18);
        cool_plugin.insert(&rb, 20);

        println!("{:?}", &cool_plugin);

        // 由于 CloneableI32 实现了Copy 特性, 所以在进行 drop 的时候相当于无作用.
        // Invoking `std::mem::drop` with a value that implements `Copy` does nothing
        // drop(zk);

        println!("{:?}", &cool_plugin);
    }
}