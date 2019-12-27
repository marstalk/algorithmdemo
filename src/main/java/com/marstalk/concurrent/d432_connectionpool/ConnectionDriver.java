package com.marstalk.concurrent.d432_connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class ConnectionDriver {
    public static Connection getConnection() {
        return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equalsIgnoreCase("commit")) {
                    Thread.sleep(300);
                }
                System.out.println(method.getName() + " invokes");
                return null;
            }
        });
    }
}
