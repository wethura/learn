
#[cfg(test)]
mod reference {
    use std::rc::Rc;

    #[test]
    fn circle_ref() {
        let five = Rc::new(5);
        let weak_five = Rc::downgrade(&five);

        let strong_five = weak_five.upgrade();
        let extra_val = *strong_five.unwrap();
        assert_eq!(extra_val, 5);

        // 手动释放
        drop(five);

        let drop_five = weak_five.upgrade();
        assert_eq!(drop_five, None);

        assert_eq!(extra_val, 5);
    }
}