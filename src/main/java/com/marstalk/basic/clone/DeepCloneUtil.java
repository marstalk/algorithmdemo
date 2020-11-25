package com.marstalk.basic.clone;

public class DeepCloneUtil<T> {

    public static <T> T clone(T t) {
        if (!(t instanceof Cloneable)) {
            System.err.println("object is not cloneable");
            return null;
        }
        return null;
    }

}
