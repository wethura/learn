#[test]
fn test_type_inference() {
    let guess_i64:i64 = "1024".parse().expect("Not i64");
    println!("num: {:?}", guess_i64);

    let guess_u64 = guess_i64 as u64;
    println!("u64: {:?}", guess_u64);

    // 整型溢出.
    let a : u8 = 255;
    let b = a.wrapping_add(5);
    println!("{:?}", b);
}