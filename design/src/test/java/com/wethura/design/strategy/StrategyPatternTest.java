package com.wethura.design.strategy;

import org.junit.Test;

public class StrategyPatternTest {
    @Test
    public void testStrategy() {
        Context.get().setStrategy(new StrategyApple());
        Context.get().execute();
        Context.get().setStrategy(new StrategyBanana());
        Context.get().execute();
    }
}
