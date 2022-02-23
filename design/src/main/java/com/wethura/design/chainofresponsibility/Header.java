package com.wethura.design.chainofresponsibility;

public abstract class Header {
    protected Header next;

    public Header getNext() {
        return next;
    }

    public void setNext(Header next) {
        this.next = next;
    }

    public abstract void handle(Integer money);

}
