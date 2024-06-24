enum Direction {
    West,
    East,
    North,
    South,
}

#[test]
fn test_matches() {
    let direction = Direction::North;
    match direction {
        Direction::East => println!("East"),
        Direction::West|Direction::North|Direction::South => println!("Total"),
    }
}

#[test]
fn test_letmatches() {
    let direction = Direction::North;

    let num = match direction {
        Direction::East=>0,
        Direction::South=>1,
        Direction::West=>2,
        Direction::North=>3,
    };

    println!("direction code: {:?}", num)
}