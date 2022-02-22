package com.wethura.learn.jdk8;

/**
 * @author wethura
 * @date 2020/11/22 上午2:05
 * @desc com.wethura.learn.jdk8.Lambda and functional interface.
 */
public class Lambda {

    @FunctionalInterface
    interface Operation {

        int operation(int a, int b);
    }

    static class Tester {

        public static void main(String[] args) {
            Tester tester = new Tester();

            System.out.println(tester.operation(1, 2, (a, b) -> a + b));
        }

        private int operation(int a, int b, Operation operation) {
            return operation.operation(a, b);
        }
    }
}
