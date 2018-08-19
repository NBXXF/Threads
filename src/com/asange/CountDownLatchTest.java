package com.asange;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.asange
 * icourt
 * 2018/8/19
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3) {
            @Override
            public void await() throws InterruptedException {
                super.await();
                System.out.println("全部已经跑完,公布成绩啦"+Thread.currentThread().getName());
            }
        };

        System.out.println("枪声:开始起跑");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 3; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("运动员:" + Thread.currentThread().getName() + " 开始起跑");
                    try {
                        Thread.sleep(new Random().nextInt(10) * 1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("运动员:" + Thread.currentThread().getName() + " 跑到终点");
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
