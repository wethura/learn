package com.wethura.design.compositecommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wethura
 * @date 2021/2/20 上午1:11
 */
public class CompositeCommandPattern {
    public static interface AbstractCommand {
        void execute();
    }

    public static class ConcreteCommandApple implements AbstractCommand {
        private CompositeReceiver receiver;

        public ConcreteCommandApple() {
            this.receiver = new CompositeReceiver();
        }

        @Override
        public void execute() {
            receiver.actionFirst();
        }
    }

    public static class ConcreteCommandBanana implements AbstractCommand {
        private CompositeReceiver receiver;

        public ConcreteCommandBanana() {
            this.receiver = new CompositeReceiver();
        }

        @Override
        public void execute() {
            receiver.actionSecond();
        }
    }

    public static class CompositeInvoker implements AbstractCommand {
        private List<AbstractCommand> commands = new ArrayList<>();

        public CompositeInvoker add(AbstractCommand command) {
            commands.add(command);
            return this;
        }

        public CompositeInvoker remove(AbstractCommand command) {
            commands.remove(command);
            return this;
        }

        public AbstractCommand commands(int index) {
            return commands.get(index);
        }

        @Override
        public void execute() {
            commands.forEach(AbstractCommand::execute);
        }
    }

    public static class CompositeReceiver {
        public void actionFirst() {
            System.out.println("CompositeReceiver.actionFirst");
        }

        public void actionSecond() {
            System.out.println("CompositeReceiver.actionSecond");
        }
    }

    public static void main(String[] args) {
        final ConcreteCommandApple  apple  = new ConcreteCommandApple();
        final ConcreteCommandBanana banana = new ConcreteCommandBanana();
        final CompositeInvoker      invoker = new CompositeInvoker();

        invoker.add(apple).add(banana);
        invoker.execute();
    }
}
