package com.wethura.design;

import java.util.HashMap;

/**
 * @author wethura
 * @date 2021/2/5 上午2:30
 */
public class FlyweightPattern {

    public static class UnsharedConcreteFlyweight {
        private String info;

        public UnsharedConcreteFlyweight(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public static interface Flyweight {
        void execute(UnsharedConcreteFlyweight info);
    }

    public static class ConcreteFlyweight implements Flyweight {

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

    public static class FlyweightFactory {
        private  static HashMap<String, Flyweight> flyweights = new HashMap<>();

        public static Flyweight getFlyweight(String keyword) {
            Flyweight flyweight = flyweights.get(keyword);
            if (flyweight != null) {
                System.out.println("You getting the exist FLYWEIGHT! ");
            } else {
                flyweight = new ConcreteFlyweight(keyword);
                flyweights.put(keyword, flyweight);
            }
            return flyweight;
        }
    }

    public static void main(String[] args) {
        final Flyweight flyweight_a_01 = FlyweightFactory.getFlyweight("flyweight a");
        final Flyweight flyweight_a_02 = FlyweightFactory.getFlyweight("flyweight a");
        final Flyweight flyweight_a_03 = FlyweightFactory.getFlyweight("flyweight a");
        final Flyweight flyweight_b_01 = FlyweightFactory.getFlyweight("flyweight b");
        final Flyweight flyweight_b_02 = FlyweightFactory.getFlyweight("flyweight b");

        System.out.println("-----------------------------sep------------------------------");

        flyweight_a_01.execute(new UnsharedConcreteFlyweight("1st time execute flyweight a"));
        flyweight_a_02.execute(new UnsharedConcreteFlyweight("2st time execute flyweight a"));
        flyweight_a_03.execute(new UnsharedConcreteFlyweight("3st time execute flyweight a"));
        flyweight_b_01.execute(new UnsharedConcreteFlyweight("1st time execute flyweight b"));
        flyweight_b_02.execute(new UnsharedConcreteFlyweight("2st time execute flyweight b"));
    }
}
