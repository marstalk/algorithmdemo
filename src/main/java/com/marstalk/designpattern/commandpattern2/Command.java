package com.marstalk.designpattern.commandpattern2;

/**
 * 命令模式下的Command是连接控制器和设备的纽带。
 * 一般有两个方法：execute，undo
 */
public interface Command  {
    void execute();

    /**
     * 撤销命令可以撤销上一个命令的效果，需要遥控器记录下上一个具体的命令。
     */
    void undo();

    /**
     * 将命令持久化
     */
    void store();

    /**
     * 去读命令，用于还原现场。
     */
    void load();
}
