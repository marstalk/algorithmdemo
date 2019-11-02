package com.marstalk.concurrent.javapsynchronize;

/**
 * Created by louisliu on 12/24/18.
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class){
        }
        m();
    }

    public static synchronized void m(){}
}

