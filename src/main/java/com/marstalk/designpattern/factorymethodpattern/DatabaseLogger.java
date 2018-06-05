package com.marstalk.designpattern.factorymethodpattern;

public class DatabaseLogger extends Logger {

    @Override
    public void writeLog() {
        System.out.println("DatabaseLogger.writeLog()...write to db");
    }
}
