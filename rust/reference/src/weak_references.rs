use std::cell::RefCell;
use std::rc::{Rc, Weak};

// 工具
struct Gadget {
    id: i32,
    owner: Rc<Owner>
}

// 拥有者 - 一对多工具
struct Owner {
    name: String,
    gadgets: RefCell<Vec<Weak<Gadget>>>,
}

#[cfg(test)]
mod tools {
    use std::cell::RefCell;
    use std::rc::Rc;
    use crate::weak_references::{Gadget, Owner};

    #[test]
    fn test_gadget_with_owner() {
        // 创建一个 Owner
        let gadget_owner = Rc::new(Owner {
            name: "Gadget Man".to_string(),
            gadgets: RefCell::new(Vec::new()),
        });

        // 为 Owner 准备 2 个 Gadget
        let gadget_01 = Rc::new(Gadget {
            id: 1,
            owner: gadget_owner.clone(),
        });

        let gadget_02 = Rc::new(Gadget {
            id: 2,
            owner: gadget_owner.clone(),
        });

        // 将 Gadget 给 Owner 持有
        gadget_owner.gadgets.borrow_mut().push(Rc::downgrade(&gadget_01));
        gadget_owner.gadgets.borrow_mut().push(Rc::downgrade(&gadget_02));

        for gg_ref in gadget_owner.gadgets.borrow().iter() {
            let gg = gg_ref.upgrade().unwrap();
            println!("Gadget {} owned by {}", gg.id, gg.owner.name);
        }
    }
}