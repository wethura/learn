package com.wethura.design.observer;

public class ConcreteSubject extends Subject{
    /**
     * 正常这里应该对调用的通知方法进行 异常处理
     */
    @Override
    public void notifyObservers() {
        System.out.println("notify all observers.");

        observers.forEach(Observer::response);
    }

}
