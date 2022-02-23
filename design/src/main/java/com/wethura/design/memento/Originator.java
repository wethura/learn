package com.wethura.design.memento;

public class Originator {
    protected String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento saveToMemento() {
        return new Memento(getState());
    }

    public void rollbackStateFromMemento(Memento memento) {
        setState(memento.getState());
    }
}
