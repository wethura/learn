package com.wethura.design.flyweight;

public class ConcreteFlyweight implements Flyweight{
    private String keyword;

    public ConcreteFlyweight(String keyword) {
        this.keyword = keyword;
        System.out.println("concrete flyweight: " + keyword + " was created! ");
    }

    @Override
    public void execute(UnsharedConcreteFlyweight info) {
        System.out.println("share message: " + keyword);
        System.out.println("unshared message: " + info.getInfo());
    }

}
