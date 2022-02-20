package com.wethura.learn.jdk8;

/**
 * @author wethura
 * @date 2020/11/22 上午2:22
 */
public class MethodQuote {

    public static void main(String[] args) {
        Tester tester = Tester.create(Tester::new);

        System.out.println("Static Method: " + tester.operate(1, 2, Tester::add));

        System.out.println("Instance Method: " + tester.operate(2, 1, tester::sub));

        tester.test(Tester::testMethod);

    }

    @FunctionalInterface
    interface SolaOperation {

        int operation(int a, int b);
    }

    interface Create<T> {

        T get();
    }

    interface TestInt {

        int cp(Tester var1, Tester var2, Tester var3);

        default String defaultTester() {
            return "You have a default value of method's return.";
        }
    }

    static class Tester {

        public static Tester create(Create<Tester> creater) {
            return creater.get();
        }

        private static int add(int a, int b) {
            return a + b;
        }

        private int operate(int a, int b, SolaOperation opt) {
            return opt.operation(a, b);
        }

        private int sub(int a, int b) {
            return a - b;
        }

        public int testMethod(Tester var1, Tester var2) {
            System.out.println("this: " + this);
            System.out.println("testMethod: var1: " + var1 + " - var2: " + var2);
            System.out.println("Class's Instance Method Quote.");
            return 0;
        }

        public void test(TestInt test) {
            Tester var1 = Tester.create(Tester::new);
            Tester var2 = Tester.create(Tester::new);
            Tester var3 = Tester.create(Tester::new);
            System.out.println("var1" + var1 + " - var2: " + var2 + " - var3: " + var3);

//            test.cp(var1, var2, var3);
            System.out.println(test.defaultTester());
            System.out.println("test: " + this);
        }


    }
}
