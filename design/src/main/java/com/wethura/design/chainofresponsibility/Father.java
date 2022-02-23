package com.wethura.design.chainofresponsibility;

public class Father extends Header {
    @Override
    public void handle(Integer money) {
        if (money <= 100) {
            System.out.println("您爹审批审批中...");
        } else {
            System.out.println("您爹没有权限审批！");
        }

        if (getNext() == null) {
            System.out.println("没人敢审批！");
        } else {
            getNext().handle(money);
        }
    }
}
