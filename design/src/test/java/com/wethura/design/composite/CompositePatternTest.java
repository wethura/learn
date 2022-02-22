package com.wethura.design.composite;

import com.wethura.design.compositecommand.CompositeInvoker;
import com.wethura.design.compositecommand.ConcreteCommandApple;
import com.wethura.design.compositecommand.ConcreteCommandBanana;
import org.junit.Test;

public class CompositePatternTest {
    @Test
    public void testCompositePattern() {
        CompositeInvoker invoker = new CompositeInvoker();

        invoker.add(new ConcreteCommandApple()).add(new ConcreteCommandBanana());

        invoker.execute();
    }
}
