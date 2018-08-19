package com.asange;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.asange
 * icourt
 * 2018/8/19
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class CyclicbarrierTest {
    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("=====已经有3个乘客到达站台,可以发一辆车..."+Thread.currentThread().getName());
            }
        });

        System.out.println("我是司机,至少有3位乘客上车,才能出发哦..."+Thread.currentThread().getName());
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 15; i++) {
            final int finali = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("乘客%s从家里出发", finali));
                    // System.out.println(String.format("等待:%s", cb.getNumberWaiting()));
                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1_000);
                        //System.out.println(String.format("线程%s到达战场,已经到达线程数量%s", finali,(cb.getNumberWaiting())));
                        System.out.println(String.format("乘客%s到达上车地点", finali+" "+Thread.currentThread().getName()));
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}
