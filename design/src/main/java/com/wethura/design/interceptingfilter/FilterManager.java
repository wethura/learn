package com.wethura.design.interceptingfilter;

public class FilterManager {
    protected FilterChain chain = new FilterChain();

    public FilterManager(Target target) {
        this.chain = chain;
        chain.setTarget(target);

    }

    public void filterRequest(String request) {
        chain.execute(request);
    }

    public void setFilter(Filter filter) {
        chain.addFilter(filter);
    }
}
