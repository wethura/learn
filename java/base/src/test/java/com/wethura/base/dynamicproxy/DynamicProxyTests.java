package com.wethura.base.dynamicproxy;

import com.wethura.base.common.proxy.EmailService;
import com.wethura.base.common.proxy.EmailServiceImpl;
import org.junit.Test;

public class DynamicProxyTests {
    @Test
    public void testDynamicProxy() {
        EmailService service = (EmailService) JdkProxyFactory.getProxyObject(new EmailServiceImpl());

        service.send("测试动态代理");
        try {
            service.send(null);
        } catch (NullPointerException ignored) {
            System.out.println("----");
        }
    }
}
