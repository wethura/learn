package com.wethura.design;

import java.util.ArrayList;
import java.util.List;

/**
 * observer pattern.
 * @author wethura
 * @date 2021/2/8 下午2:48
 */
public class ObserverPattern {

    public static interface Observer {
        void response();
    }

    public static class ConcreteObserverOne implements Observer{
        @Override
        public void response() {
            System.out.println("concrete no.1 do activity for observer...");
        }
    }

    public static class ConcreteObserverTwo implements Observer{
        @Override
        public void response() {
            System.out.println("concrete no.2 do activity for observer...");
        }
    }

    public static abstract class Subject {
        protected List<Observer> observers = new ArrayList<>();

        public void add(Observer observer) {
            observers.add(observer);
        }

        public void remove(Observer observer) {
            observers.remove(observer);
        }

        public abstract void notifyObservers();
    }

    public static class ConcreteSubject extends Subject{
        @Override
        public void notifyObservers() {
            System.out.println("notify all observers.");

            observers.forEach(Observer::response);
        }
    }

    public static void main(String[] args) {
        final ConcreteSubject subject = new ConcreteSubject();
        subject.add(new ConcreteObserverOne());
        subject.add(new ConcreteObserverTwo());

        subject.notifyObservers();
    }
}
