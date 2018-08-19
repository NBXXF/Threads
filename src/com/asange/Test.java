package com.asange;

import java.util.Timer;
import java.util.TimerTask;

/**
 * com.asange
 * icourt
 * 2018/8/14
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class Test {
    static volatile int ticketNum = 1000;

    static synchronized int getTicketNum() {
        return ticketNum--;
    }

    public static void main(String[] args) {
       /* new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(""+System.currentTimeMillis());
            }
        },0,1000);*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                int ticketNum = getTicketNum();
                while (ticketNum > 0) {
                    System.out.println("a get num:" + ticketNum);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int ticketNum = getTicketNum();
                while (ticketNum > 0) {
                    System.out.println("b get num:" + ticketNum);
                }
            }
        }).start();
    }
}
