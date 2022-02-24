package com.wethura.design.interceptingfilter;

import org.junit.Test;

public class InterceptingFilterTest {
    @Test
    public void testInterceptingFilter() {
        Client client = new Client();
        FilterManager manager = new FilterManager(new Target());
        manager.setFilter(new AuthenticationFilter());
        manager.setFilter(new DebugFilter());
        client.setFilterManager(manager);

        client.sendRequest("Hello World!");
    }
}
