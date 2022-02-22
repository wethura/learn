package com.wethura.design.compositecommand;

public class ConcreteCommandApple implements AbstractCommand {
    private CompositeReceiver receiver;

    public ConcreteCommandApple() {
        this.receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        receiver.actionFirst();
    }
}
