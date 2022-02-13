package com.wethura.design;

/**
 * reduce the same code in concrete method. extract the same code to abstract class.
 * @author wethura
 * @date 2021/2/3 上午1:40
 */
public class TemplateMethod {
    public static abstract class AbstractClass {
        public void templateExecute() {
            concreteMethod();
            abstractMethod();
        }

        protected void concreteMethod() {
            System.out.println("You are execute abstract class's method.");
        }

        protected abstract void abstractMethod();
    }

    public static class ConcreteClass extends AbstractClass{
        @Override
        public void abstractMethod() {
            System.out.println("You are execute concrete class's method.");
        }
    }

    public static void main(String[] args) {
        AbstractClass abstractClass = new ConcreteClass();

        abstractClass.templateExecute();
    }
}
