package com.xiekang.exercise.concurret;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
* @Description: 带返回值的线程
* @Author:  xiekang
* @CreateDate: 2019/11/26 10:20
*/
public class CallableDemo implements Callable<String> {

    int a;
    int b;

    public CallableDemo(int a,int b){
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> submit = service.submit(new CallableDemo(1,3));
        System.out.println("result = "+submit.get());
        service.shutdown();
    }

    
    @Override
    public String call() throws Exception {
        return a+b+"";
    }
}
