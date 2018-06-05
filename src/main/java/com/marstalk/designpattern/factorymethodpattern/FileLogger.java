package com.marstalk.designpattern.factorymethodpattern;

public class FileLogger extends Logger {

    @Override
    public void writeLog() {
        System.out.println("FileLogger.writeLog()...write to file");
    }
}
