package com.asange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * com.asange
 * icourt
 * 2018/9/16
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class SingletonTest {

    private static class Singleton {
        private Singleton() {

        }

        static Singleton singleton;

        public static  synchronized Singleton getInstance() {
            if (singleton == null) {
                synchronized (Singleton.class) {
                    if (singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }
    }

    public static void main(String[] args) {
        Map<Singleton, Object> objectHashMap = new ConcurrentHashMap();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    objectHashMap.put(Singleton.getInstance(), "xx");
                    StringBuilder stringBuilder = new StringBuilder("len:" + objectHashMap.keySet().size());
                    for (Singleton singleton : objectHashMap.keySet()) {
                        stringBuilder.append("\n");
                        stringBuilder.append("" + singleton);
                    }
                    System.out.println(stringBuilder.toString());
                }
            });
        }
    }
}
