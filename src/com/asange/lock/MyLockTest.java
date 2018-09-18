package com.asange.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.asange.lock
 * icourt
 * 2018/9/18
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class MyLockTest {

    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Set<Integer> numbers=new HashSet<>();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int next = sequence.getNext();
                    numbers.add(next);
                    System.out.println("next：" + next+"  size："+numbers.size());
                }
            });
        }
    }

    private static class Sequence {
        int num;
        static MyLock lock=new MyLock();

        public int getNext() {
            lock.lock();
            num++;
            lock.unlock();
            return num;
        }
    }
}
