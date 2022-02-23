package com.wethura.design.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private List<Memento> mementos = new ArrayList<>(2);

    public void add(Memento state) {
        mementos.add(state);
    }

    public Memento get(int index) {
        return mementos.get(index);
    }
}
