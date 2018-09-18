package com.asange.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * com.asange.lock
 * icourt
 * 2018/9/18
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class MyLock implements Lock {

    private boolean isLocked = false;

    @Override
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    @Override
    public synchronized void unlock() {
        isLocked = false;
        notify();
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
    public Condition newCondition() {
        return null;
    }
}
