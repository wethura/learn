package com.wethura.base.common.proxy;

public class EmailServiceImpl implements EmailService {

    @Override
    public void send(String msg) {
        if(msg == null) {
            throw new NullPointerException("msg must not be null.");
        }
        System.out.println(String.format("sending: [%s]", msg));
    }
}
