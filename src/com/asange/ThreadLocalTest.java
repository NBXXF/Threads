package com.asange;

/**
 * com.asange
 * icourt
 * 2018/8/17
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class ThreadLocalTest {
    static boolean first = false;

    public static void main(String[] args) {
        String lock = "lock";

        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (!first) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Integer integer = threadLocal.get();
                        if (integer == null) {
                            integer = new Integer(0);
                        }
                        integer++;
                        threadLocal.set(integer);
                        if (threadLocal.get() % 2 != 0) {
                            System.out.println("thread:0:data:" + threadLocal.get());
                            first = true;
                            lock.notify();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (lock) {
                        if (first) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Integer integer = threadLocal.get();
                        if (integer == null) {
                            integer = new Integer(0);
                        }
                        integer++;
                        threadLocal.set(integer);
                        if (threadLocal.get() % 2 == 0) {
                            System.out.println("thread:1:data:" + threadLocal.get());
                            first = false;
                            lock.notify();
                        }
                    }
                }
            }
        }).start();

    }
}
