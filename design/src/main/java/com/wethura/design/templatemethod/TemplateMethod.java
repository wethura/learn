package com.wethura.design.templatemethod;

/**
 * reduce the same code in concrete method. extract the same code to abstract class.
 * @author wethura
 * @date 2021/2/3 上午1:40
 */
public class TemplateMethod {

    public static void main(String[] args) {
        AbstractClass abstractClass = new ConcreteClass();

        abstractClass.templateExecute();
    }
}
