package com.marstalk.concurrent.jmm;

public class VolatileOperand {
    private volatile int i = 0;

    public int get(){
        int j = 0;
        j++;
        i++;
        return i+j;
    }
}
