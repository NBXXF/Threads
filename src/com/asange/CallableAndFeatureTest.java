package com.asange;

import java.util.concurrent.*;

/**
 * com.asange
 * icourt
 * 2018/8/18
 * author:asange
 * email:xuanyouwu@163.com
 **/
public class CallableAndFeatureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("" + Thread.currentThread().getName());
            }
        });
        Future<String> submit1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "hello";
            }
        });
        System.out.println("拿到结果:");
        try {
            System.out.println("" + submit1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService<Integer>(Executors.newFixedThreadPool(3));
        for (int i = 0; i < 10; i++) {
            final int finali = i;
            executorCompletionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return finali;
                }
            });
        }


        for (int i = 0; i < 10; i++) {
            try {
                Integer integer = executorCompletionService.take().get();
                System.out.println("i:" + integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(""+submit1.get(10,TimeUnit.SECONDS));
    }
}
