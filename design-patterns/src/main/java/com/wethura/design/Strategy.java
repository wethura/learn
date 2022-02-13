package com.wethura.design;

/**
 * support a union interface to execute the method what we need.
 * change the detail algorithm by the method.
 * @author wethura
 * @date 2021/2/3 上午1:19
 */
public class Strategy {
    public static interface InterfaceStrategy {
        void execute();
    }

    public static class AStrategy implements InterfaceStrategy {
        @Override
        public void execute() {
            System.out.println("A Algorithm execute.");
        }
    }

    public static class BStrategy implements InterfaceStrategy {
        @Override
        public void execute() {
            System.out.println("B Algorithm execute.");
        }
    }

    public static class Context {
        private static Context context = new Context();

        private static Context get() {
            return context;
        }

        private InterfaceStrategy strategy;

        public void execute() {
            strategy.execute();
        }

        public void setStrategy(InterfaceStrategy strategy) {
            this.strategy = strategy;
        }
    }

    public static void main(String[] args) {
        Context.get().setStrategy(new AStrategy());
        Context.get().execute();
        Context.get().setStrategy(new BStrategy());
        Context.get().execute();
    }
}
