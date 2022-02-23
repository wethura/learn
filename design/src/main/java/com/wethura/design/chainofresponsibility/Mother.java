package com.wethura.design.chainofresponsibility;

public class Mother extends Header {
    @Override
    public void handle(Integer money) {
        if (money <= 1000) {
            System.out.println("您妈审批该金额中...");
        } else {
            System.out.println("您妈没有权限审批！");
        }

        if (getNext() == null) {
            System.out.println("没人敢审批！");
        } else {
            getNext().handle(money);
        }
    }
}
