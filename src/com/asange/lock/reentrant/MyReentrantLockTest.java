package com.asange.lock.reentrant;

/**
 * com.asange.lock.reentrant
 * icourt
 * 2018/9/18
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class MyReentrantLockTest {
    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        sequence.a();
    }

    private static class Sequence {
        MyReentrantLock myReentrantLock = new MyReentrantLock();

        void say(String msg) {
            System.out.println(msg);
        }

        public void a() {
            myReentrantLock.lock();
            say("a");
            b();
            myReentrantLock.unlock();
        }

        public void b() {
            myReentrantLock.lock();
            say("b");
            myReentrantLock.unlock();
        }
    }
}
