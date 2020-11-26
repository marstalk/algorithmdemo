package com.marstalk.basic.clone;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象的clone方法是默认是浅拷贝，即只拷贝基本类型，其中包括reference类型。
 * Object独享的clone方法是native。
 * 实现深度拷贝的方法1，使用cloneable接口，并实现clone方法。
 *
 * @author shanzhonglaosou
 */
public class DeepCloneDemo1 {
    @Test
    public void basicClone() throws CloneNotSupportedException {
        Car car1 = new Car();
        Car cloneCard = car1.clone();
        System.out.println("car1 == cloneCard " + (car1 == cloneCard));
        System.out.println("engine == engine " + (car1.engine == cloneCard.engine));
        //false
        //false
    }

    @Test
    public void deepClone() {
        Car car1 = new Car();
        final Car cloneCard = car1.deepClone();
        System.out.println("car1 == cloneCard " + (car1 == cloneCard));
        System.out.println("engine == engine " + (car1.engine == cloneCard.engine));
    }

    class Engine implements Cloneable, Serializable {
        int power = 3;

        @Override
        protected Engine clone() throws CloneNotSupportedException {
            return (Engine) super.clone();
        }
    }

    class Car implements Cloneable, Serializable {
        private Engine engine = new Engine();
        private String brand = "Audi";

        @Override
        protected Car clone() throws CloneNotSupportedException {
            Car car = (Car) super.clone();
            car.engine = this.engine.clone();
            //如果手动拷贝自定义对象，那么engine就是浅拷贝，即拷贝的是引用。
            return car;
        }

        public Car deepClone() {
            try (final ByteArrayOutputStream bo = new ByteArrayOutputStream();
                 final ObjectOutputStream oo = new ObjectOutputStream(bo);) {
                oo.writeObject(this);
                try (final ByteArrayInputStream in = new ByteArrayInputStream(bo.toByteArray());
                     final ObjectInputStream bi = new ObjectInputStream(in)) {
                    return (Car) bi.readObject();
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
