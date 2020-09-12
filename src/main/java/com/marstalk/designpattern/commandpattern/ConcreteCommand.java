package com.marstalk.designpattern.commandpattern;

/**
 * 具体命令的实现
 */
public class ConcreteCommand implements Command{
    private Receriver receriver;

    public ConcreteCommand(Receriver receriver) {
        this.receriver = receriver;
    }

    @Override
    public void execute() {
        this.receriver.action();

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
