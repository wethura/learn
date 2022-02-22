package com.wethura.design.observer;

public class ConcreteObserverApple implements Observer{
    @Override
    public void response() {
        System.out.println("concrete {apple} do activity for observer...");
    }
}
