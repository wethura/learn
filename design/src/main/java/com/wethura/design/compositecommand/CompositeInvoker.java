package com.wethura.design.compositecommand;

import java.util.ArrayList;
import java.util.List;

public class CompositeInvoker implements AbstractCommand {
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
