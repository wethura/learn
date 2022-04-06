package com.wethura.base.staticproxy;

import org.junit.Test;

public class StaticProxyTests {

    @Test
    public void testStaticProxy() {
        EmailProxy email = new EmailProxy();
        email.send("测试静态代理！");
    }

}
