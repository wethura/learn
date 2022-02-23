package com.wethura.design.memento;

import org.junit.Assert;
import org.junit.Test;

public class MementoTest extends Assert {
    @Test
    public void testMemento() {
        CareTaker taker = new CareTaker();

        Originator originator = new Originator();
        originator.setState("S1");
        originator.setState("S2");
        taker.add(originator.saveToMemento());
        originator.setState("S3");
        taker.add(originator.saveToMemento());
        originator.setState("S4");

        assertEquals(originator.getState(), "S4");
        originator.rollbackStateFromMemento(taker.get(0));
        assertEquals(originator.getState(), "S2");
        originator.rollbackStateFromMemento(taker.get(1));
        assertEquals(originator.getState(), "S3");

    }
}
