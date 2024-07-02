mod generics;
mod matcher;
mod reference;
mod base_types;
mod stl;

// async fn test_call_sync_method() {
//     println!("calling rust out");
//     echo().await;
//     println!("calling rust finished");
// }

async fn echo() {
    println!("rust out!");
}

#[tokio::main]
async fn main() {
    // let listener = TcpListener::bind("0.0.0.0:8088").unwrap();
    //
    //
    // for stream in listener.incoming() {
    //     let stream = stream.unwrap();
    //
    //     handle_request(stream)
    // }

    println!("calling rust out");
    echo().await;
    println!("calling rust finished");
}

// fn handle_request(mut stream: TcpStream) {
//     let buffer_reader = BufReader::new(stream);
//
//     let http_request: Vec<_> = buffer_reader.lines().map(|r| r.unwrap())
//         .take_while(|line| !line.is_empty())
//         .collect();
//
//     println!("Request: {:#?}", http_request)
// }
//
// #[cfg(test)]
// struct Circle {
//     x: i64,
//     y: i64,
//     radio: f64,
// }
//
// pub trait Share {
//     fn area(&self) -> f64;
// }
//
// impl Share for Circle {
//     fn area(&self) -> f64 {
//         return self.radio*self.radio
//     }
// }
//
// impl PartialEq<Self> for Circle {
//     fn eq(&self, other: &Self) -> bool {
//         self.radio == other.radio
//     }
// }

// impl PartialOrd for Circle {
//     fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
//         if self.radio == other.radio {
//             Some(Ordering::Equal)
//         } else if self.radio > other.radio {
//             Some(Ordering::Greater)
//         } else {
//             Some(Ordering::Less)
//         }
//     }
// }

// impl Circle {
//     fn new(x: i64, y: i64, radio: f64) -> Circle {
//         Circle{ x, y, radio}
//     }
// }
//
// pub fn show(item: &impl Share) {
//     println!("Area: {:?}", item.area())
// }
//
// pub fn showDuplicate<T: Share>(item_01: &T, item_02: &T) {
//     println!("01: Area: {:?}", item_01.area());
//     println!("02: Area: {:?}", item_02.area());
// }
//
// #[test]
// pub fn test() {
//     let circle = Circle::new(1, 2, 12.3f64);
//     show(&circle);
//     showDuplicate(&circle, &circle);
// }