package com.asange;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.asange
 * icourt
 * 2018/9/15
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class ThreaStopTest {

    public static void main(String[] args) {
        final Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("xx_" + System.currentTimeMillis());
                }
            }
        };
        t.start();

    }
}
