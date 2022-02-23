package com.wethura.design.memento;

public class Memento {
    protected String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
