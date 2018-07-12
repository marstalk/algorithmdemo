package com.marstalk.designpattern.factorymethodpattern.test;

import com.marstalk.designpattern.factorymethodpattern.DatabaseLoggerFactory;
import com.marstalk.designpattern.factorymethodpattern.LoggerFactory;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

public class Client {

    @MyAutowired
    private LoggerFactory loggerFactory;

    public void doSomething() {
        loggerFactory.writeLog();
    }

    public Client() {

        Field[] fields = Client.class.getDeclaredFields();
        if (null != fields && fields.length != 0) {
            for (int i = 0; i < fields.length; i++) {
                AnnotatedType annotatedType = fields[i].getAnnotatedType();
                if (annotatedType instanceof MyAutowired) {

                }

            }
        }

        this.loggerFactory = loggerFactory;
    }

    public static void main(String[] args) {
        LoggerFactory loggerFactory = new DatabaseLoggerFactory();


        loggerFactory.createLogger().writeLog();
        loggerFactory.createLogger().writeLog();


    }
}
