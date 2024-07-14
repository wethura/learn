package com.wethura.spring.controller.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author sola
 **/
@JsonTypeName(value = "banana")
public class BananaInfo extends Info {
    private String banana;

    public String getBanana() {
        return banana;
    }

    public void setBanana(String banana) {
        this.banana = banana;
    }

    @Override
    public String toString() {
        return "BananaInfo{" +
                "bananaName='" + banana + '\'' +
                '}';
    }
}
