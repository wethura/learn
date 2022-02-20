package com.wethura.learn.agenttest;

import java.util.concurrent.TimeUnit;

/**
 * @author sola
 */
public class AgentTestMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("sleep 1 second to test agent main.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("sleep 1 second finished.");
    }
}
