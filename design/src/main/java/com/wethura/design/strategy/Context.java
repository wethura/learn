package com.wethura.design.strategy;

public class Context {
    protected static Context context = new Context();

    protected static Context get() {
        return context;
    }

    private Strategy strategy;

    public void execute() {
        strategy.execute();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
