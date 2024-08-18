#[test]
fn test_closure() {
    let mut x = 0;

    let mut count = move || -> i32 {
        x += 1;
        return x;
    };

    println!("第 1 次调用, x={:?}", count());
    println!("第 2 次调用, x={:?}", count());
    println!("第 3 次调用, x={:?}", count());

    let add = |x, y| x + y;
    println!("1+2 = {:?}", add(1, 2));
}

struct Cache<T>
where
    T: Fn(i32) -> i32,
{
    calc: T,
    value: Option<i32>,
}

impl<T> Cache<T>
where
    T: Fn(i32) -> i32,
{
    fn new(calc: T) -> Cache<T> {
        Cache {
            calc,
            value: None,
        }
    }

    fn value(&mut self, arg: i32) -> i32 {
        if self.value.is_none() {
            self.value = Some((self.calc)(arg));
        }

        self.value.unwrap()
    }
}

#[test]
fn test_struct_closure() {
    let add3 = |x| x + 3;
    let mut cache = Cache::new(add3);

    println!("value: {}", cache.value(1))
}

#[test]
fn test_iterator_in_closure() {
    let arr = [1, 2, 3];
    for i in 0..arr.len() {
        println!("for ind {}", arr[i])
    }

    for e in arr {
        println!("for ele {}", e)
    }

    for mut e in arr.into_iter() {
        println!("0 iter: {}", e)
    }
}