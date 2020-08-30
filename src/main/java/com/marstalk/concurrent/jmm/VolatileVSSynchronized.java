package com.marstalk.concurrent.jmm;

public class VolatileVSSynchronized {
    volatile long l = 0L;
    public void set(long l){
        this.l = l;
    }
    public long get(){
        return l;
    }
    public void getAndIncrement(){
        this.l++;
    }
}
class Synch{
    long l = 0L;
    public synchronized void set(long l){
        this.l = l;
    }
    public synchronized long get(){
        return l;
    }
    public void getAndIncrement(){
        long temp = get();
        temp += 1L;
        set(temp);
    }
}