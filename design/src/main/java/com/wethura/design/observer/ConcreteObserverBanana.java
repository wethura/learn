package com.wethura.design.observer;

public class ConcreteObserverBanana implements Observer{
    @Override
    public void response() {
        System.out.println("concrete {banana} do activity for observer...");
    }
}
