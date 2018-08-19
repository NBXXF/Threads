package com.asange;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.asange
 * icourt
 * 2018/8/19
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class ConditionTest {
    /**
     * java一道多线程题，子线程循环10次，主线程接着循环100次，如此循环50次的问题
     *
     * @param args
     */
    public static void main(String[] args) {
        Busniess business = new Busniess();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    business.sub();
                }
            }
        }).start();
        for (int i = 0; i < 50; i++) {
            business.main();
        }

    }

    public static class Busniess {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        boolean isMain;

        public void main() {
            lock.lock();
            try {
                while (!isMain) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 100; i++) {
                    System.out.println("main:" + i);
                }
                isMain = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub() {
            lock.lock();
            try {
                while (isMain) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("sub:" + i);
                }
                isMain = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
