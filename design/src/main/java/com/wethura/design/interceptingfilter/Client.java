package com.wethura.design.interceptingfilter;

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager manager) {
        this.filterManager = manager;
    }

    public void sendRequest(String request ) {
        filterManager.filterRequest(request);
    }
}
