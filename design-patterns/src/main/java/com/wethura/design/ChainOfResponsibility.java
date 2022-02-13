package com.wethura.design;

/**
 * 责任链设计模式
 * @author wethura
 * @date 2021/2/2 下午4:20
 */
public class ChainOfResponsibility {
    public static abstract class Header {
        protected Header next;

        public Header getNext() {
            return next;
        }

        public void setNext(Header next) {
            this.next = next;
        }

        public abstract void handle(Integer money);
    }

    public static class Father extends Header {
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

    public static class Mother extends Header {
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

    public static void main(String[] args) {
//        设置责任链
        final Header mother = new Mother();
        final Header father = new Father();
        father.setNext(mother);

        father.handle(102);
    }
}
