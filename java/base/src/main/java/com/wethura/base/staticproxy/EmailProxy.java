package com.wethura.base.staticproxy;

import com.wethura.base.common.proxy.EmailService;
import com.wethura.base.common.proxy.EmailServiceImpl;

public class EmailProxy implements EmailService {
    private EmailService service;

    public EmailProxy() {
        service = new EmailServiceImpl();
    }

    @Override
    public void send(String msg) {
        System.out.println("handle before send email...");

        service.send(msg);

        System.out.println("handle after send email...");
    }
}
