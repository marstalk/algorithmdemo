package com.marstalk.designpattern.factorymethodpattern;

public class FileLoggerFactory extends LoggerFactory{
    @Override
    public Logger createLogger() {
        return new FileLogger();
    }
}
