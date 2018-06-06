package com.marstalk.designpattern.factorymethodpattern;

public abstract class LoggerFactory {

    public abstract Logger createLogger();

    public void writeLog(){
        this.createLogger().writeLog();
    }
}
