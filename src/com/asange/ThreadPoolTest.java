package com.asange;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.asange
 * icourt
 * 2018/8/18
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int tid = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread:" + Thread.currentThread().getName() + " taskid:" + tid);
                }
            });
        }
    }
}
