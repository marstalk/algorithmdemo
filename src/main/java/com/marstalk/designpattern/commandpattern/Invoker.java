package com.marstalk.designpattern.commandpattern;

/**
 * 客户端调用
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }
    public void action(){
        this.command.execute();
    }
}
