package com.wethura.design.templatemethod;

import org.junit.Test;

public class TemplateMethodTest {
    @Test
    public void testTemplateMethod() {
        AbstractClass method = new ConcreteClass();

        // 执行算法的整体，实现却是算法需要根据不同的实现特意留出来的方法给到用户进行处理的
        method.templateExecute();
    }
}
