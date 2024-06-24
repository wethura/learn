use std::fmt::Debug;

pub trait Draw {
    fn draw(&self);
}


#[derive(Debug)]
pub struct Button {
    width: f64,
    height: f64,
}

#[derive(Debug)]
pub struct SelectButton {
    width: f64,
    height: f64,
    options: Vec<String>,
}

impl Draw for Button {
    fn draw(&self) {
        println!("{:?}", self)
    }
}

impl Draw for SelectButton {
    fn draw(&self) {
        println!("{:?}", self)
    }
}

pub struct Screen<T: Draw> {
    pub components: Vec<Box<T>>,
}

impl<T> Screen<T>
where T:Draw{
    pub fn run(&self) {
        for comp in &self.components {
            comp.draw()
        }
    }
}

#[cfg(test)]
mod test {

    #[test]
    fn test_add() {
        assert_eq!(1 + 1, 2);
    }
}

#[test]
pub fn test_draw() {
    let screen = Screen {
        components: vec![
            Box::new(Button {
                width: 5.0,
                height: 12.0,
            }),
            // Box::new(SelectButton{
            //     width: 5.0,
            //     height: 12.0,
            //     options: vec![String::from("blue"), String::from("red")],
            // })
        ]
    };

    screen.run()
}