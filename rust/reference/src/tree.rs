use std::cell::RefCell;
use std::rc::{Rc, Weak};

#[derive(Debug)]
struct Node {
    val: i32,
    parent: RefCell<Weak<Node>>, // 父节点不一定存在, 只能用弱引用.
    children: RefCell<Vec<Rc<Node>>>,
}

#[cfg(test)]
mod tree {
    use std::cell::RefCell;
    use std::rc::{Rc, Weak};
    use crate::tree::Node;

    #[test]
    fn test_tree() {
        let leaf = Rc::new(Node {
            val: 3,
            parent: RefCell::new(Weak::new()),
            children: RefCell::new(vec![]),
        });

        println!(
            "leaf strong = {}, weak = {}",
            Rc::strong_count(&leaf),
            Rc::weak_count(&leaf),
        );

        {
            // branch 实在代码块中的, 生命周期会在本代码块结束后结束.

            let branch = Rc::new(Node {
                val: 5,
                parent: RefCell::new(Weak::new()), // 父节点为弱引用.
                children: RefCell::new(vec![Rc::clone(&leaf)]), // 叶子节点为强引用, 确保父节点在访问子节点的时候子节点不会被释放
            });

            // 为叶子添加父节点
            *leaf.parent.borrow_mut() = Rc::downgrade(&branch);

            println!(
                "branch strong: {}, weak: {}",
                Rc::strong_count(&branch),
                Rc::weak_count(&branch),
            );

            println!(
                "leaf strong = {}, weak = {}",
                Rc::strong_count(&leaf),
                Rc::weak_count(&leaf),
            );
        }

        // 在代码块之后访问, Option 为 None.
        println!("leaf parent = {:?}", leaf.parent.borrow().upgrade());
        println!(
            "leaf strong = {}, weak = {}",
            Rc::strong_count(&leaf),
            Rc::weak_count(&leaf),
        );
    }
}