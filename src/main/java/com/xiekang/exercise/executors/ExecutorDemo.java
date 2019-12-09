package com.xiekang.exercise.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @Description:  线程池
* @Author:  xiekang
* @CreateDate: 2019/11/29 15:32
*/
public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(()->{

        });

    }
}
