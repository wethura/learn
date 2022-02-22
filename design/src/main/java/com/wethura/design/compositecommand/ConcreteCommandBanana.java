package com.wethura.design.compositecommand;

public class ConcreteCommandBanana implements AbstractCommand{
    private CompositeReceiver receiver;

    public ConcreteCommandBanana() {
        this.receiver = new CompositeReceiver();
    }

    @Override
    public void execute() {
        receiver.actionSecond();
    }

}
