
#[cfg(test)]
mod tls {
    #[test]
    fn test_vector() {
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

        //     测试借用元素
        let x = &v02[1];
        println!("length: {:?}, element[1]: {:?}.", v02.len(), x);

        // 重新操作 v02 的时候 x 的权限就理应被归还了.
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

    #[derive(Debug)]
    enum IpAddr {
        V4(String),
        V6(String),
    }

    #[test]
    fn test_diff_element_in_vector() {
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

    #[test]
    fn test_hashmap() {

    }
}