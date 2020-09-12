package com.marstalk.designpattern.commandpattern;

/**
 * 抽象命令
 */
public interface Command {
    void execute();
    void undo();
    void redo();
}
