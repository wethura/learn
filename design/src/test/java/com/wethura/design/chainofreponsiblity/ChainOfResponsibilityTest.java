package com.wethura.design.chainofreponsiblity;

import com.wethura.design.chainofresponsibility.Father;
import com.wethura.design.chainofresponsibility.Header;
import com.wethura.design.chainofresponsibility.Mother;
import org.junit.Test;

public class ChainOfResponsibilityTest {
    @Test
    public void testChainOfResponsibility() {
        Header father = new Father();
        Header mother = new Mother();

        father.setNext(mother);
        father.handle(102);
    }
}
