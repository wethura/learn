struct SelfRef<'a> {
    val: String,
    self_val_ref: Option<&'a str>
}

#[derive()(Debug)]
struct WhatAboutThis<'a> {
    name: String,
    nickname: Option<&'a str>,
}

impl WhatAboutThis {

}

#[cfg(test)]
mod self_ref {
    use crate::self_reference::SelfRef;

    #[test]
    fn test_self_ref() {
        let str = "Hello World".to_string();
        let mut self_ref = SelfRef {
            val: str,
            self_val_ref: None,
        };

        self_ref.self_val_ref = Some(&self_ref.val);
    }

    #[test]
    fn test_what_about_this() {

    }
}