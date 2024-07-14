package com.wethura.spring.controller.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author sola
 **/
@JsonTypeName(value = "apple")
public class AppleInfo extends Info {
    private String apple;

    public String getApple() {
        return apple;
    }

    public void setApple(String apple) {
        this.apple = apple;
    }

    @Override
    public String toString() {
        return "AppleInfo{" +
                "apple='" + apple + '\'' +
                '}';
    }
}
