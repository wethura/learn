package com.wethura.design.strategy;

public class StrategyApple implements Strategy {
    @Override
    public void execute() {
        System.out.println("Strategy {apple} execute.");
    }
}
