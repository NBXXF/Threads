package com.asange;

import java.util.concurrent.Semaphore;

/**
 * com.asange
 * icourt
 * 2018/8/19
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        final int permits = 3;
        Semaphore semaphore = new Semaphore(permits);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("当前已有%s个线程并发",(permits-semaphore.availablePermits())));
                    semaphore.release();
                }
            }).start();
        }
    }
}
