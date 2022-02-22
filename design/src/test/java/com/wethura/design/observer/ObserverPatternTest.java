package com.wethura.design.observer;

import org.junit.Test;

public class ObserverPatternTest {
    @Test
    public void testObserver() {
        ConcreteSubject subject = new ConcreteSubject();
        subject.add(new ConcreteObserverApple());
        subject.add(new ConcreteObserverBanana());

        subject.notifyObservers();
    }
}
