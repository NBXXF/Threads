package com.asange;

/**
 * com.asange
 * icourt
 * 2018/8/14
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class User {

    public synchronized void add() {
        try {
            System.out.println("add exe");
            Thread.sleep(3000);
            System.out.println("add complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void add2() {
        try {
            System.out.println("add2 exe");
            Thread.sleep(3000);
            System.out.println("add2 complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
