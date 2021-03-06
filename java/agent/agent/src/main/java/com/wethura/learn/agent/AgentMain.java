package com.wethura.learn.agent;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

/**
 * @author wethura
 * @date 2021/1/7 上午4:09
 */
public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new DefineTransformer(), true);
        for (Class loadedClass : inst.getAllLoadedClasses()) {
            StringBuilder buffer = new StringBuilder();
            Arrays.stream(loadedClass.getMethods()).forEach(method -> buffer.append("\n" + method.getName()));
        }
    }
}
