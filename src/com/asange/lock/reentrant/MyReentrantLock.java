package com.asange.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * com.asange.lock.reentrant
 * icourt
 * 2018/9/18
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class MyReentrantLock implements Lock {
    private boolean isLocked;
    private int lockedCount;
    private Thread lockedBy;

    @Override
    public synchronized void lock() {
        while (isLocked && lockedBy != Thread.currentThread()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockedCount++;
        lockedBy = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (lockedBy == Thread.currentThread()) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
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
