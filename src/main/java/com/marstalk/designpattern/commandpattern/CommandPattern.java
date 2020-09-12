package com.marstalk.designpattern.commandpattern;

/**
 * @author shanzhonglaosou
 */
public class CommandPattern {
    public static void main(String[] args) {
        Receriver receriver = new Receriver();
        Command concreteCommand = new ConcreteCommand(receriver);

        Invoker invoker = new Invoker();
        //客户负责把命令设置给调用者。一旦命令被保存下来，可能会被丢弃或者执行多次。
        invoker.setCommand(concreteCommand);

        //在某个未来时间点，执行command的execute方法。
        invoker.action();
    }
}
