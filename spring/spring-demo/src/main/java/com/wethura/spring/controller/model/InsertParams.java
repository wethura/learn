package com.wethura.spring.controller.model;

/**
 * @author sola
 **/
public class InsertParams<T extends Info> {
    T info;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "InsertParams{" +
                "info=" + info.toString() +
                '}';
    }
}
