package com.marstalk.concurrent.d5_twinslock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 这是一个利用AQS实现的一个同步组件：
 * 任意时刻，只能有2个线程同步访问。
 */
public class TwinsLock implements Lock {
    private Sync sync;

    static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected int tryAcquireShared(int arg) {
            int state = getState();
            if (state < 2) {
                if(compareAndSetState(state, state + 1)){
                    return state + 1;
                }
            }
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            int stat = getState();
            return compareAndSetState(stat, stat - 1);
        }
    }

    public TwinsLock(){
        sync = new Sync();
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
