package com.asange;

/**
 * com.asange
 * icourt
 * 2018/8/15
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class CountTest {

    /**
     * java一道多线程题，子线程循环10次，主线程接着循环100次，如此循环50次的问题
     *
     * @param args
     */
    public static void main(String[] args) {
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    business.sub();
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            business.main();
        }

    }

    public static class Business {
        static boolean shouldSub=true;

        public synchronized void sub() {
            while (!shouldSub) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("sub:" + i);
            }
            shouldSub = false;
            notify();
        }

        public synchronized void main() {
           while (shouldSub) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("main:" + i);
            }
            shouldSub = true;
            notify();
        }


    }

}
