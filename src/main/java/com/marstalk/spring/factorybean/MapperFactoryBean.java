package com.marstalk.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class MapperFactoryBean implements FactoryBean<UserMapper> {
    @Override
    public UserMapper getObject() throws Exception {
        System.out.println("create proxy");
        UserMapper o = (UserMapper)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("update")) {
                    System.out.println("do update");
                    return 1;
                }
                return 0;
            }
        });
        return o;
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }

    @Override
    public boolean isSingleton() {
        //如果不是单例，那么每次从容器中获取Bean的时候，都会调用一次getObject方法。
        //return false;

        //如果是单例，那么第一次从容器中获取bean的时候，会调用一次getObject方法，然后把它放入到容器中。
        return true;
    }
}
