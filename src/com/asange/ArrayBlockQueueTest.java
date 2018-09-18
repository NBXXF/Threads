package com.asange;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.asange
 * icourt
 * 2018/8/20
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class ArrayBlockQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3,true);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final String finali = String.valueOf(i);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.put(finali);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    StringBuilder sb = new StringBuilder(String.format("存放:%s", finali));
                    sb.append(";队列中已经包含:");
                    for (String s : queue) {
                        sb.append(s+",");
                    }
                    System.out.println(sb.toString());
                    try {
                        String take = queue.take();
                        System.out.println("------------------取出:" + take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
