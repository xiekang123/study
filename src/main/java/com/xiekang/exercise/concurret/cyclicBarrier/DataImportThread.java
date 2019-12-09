package com.xiekang.exercise.concurret.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class DataImportThread extends Thread {
    private String path;

    private CyclicBarrier cyclicBarrier;


    public DataImportThread(String path, CyclicBarrier cyclicBarrier) {
        this.path = path;
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        try{
            System.out.println("线程："+Thread.currentThread().getName()+" 开始导入： "+path+"位置的数据");
            cyclicBarrier.await();
        }catch (Exception var){

        }
    }
}
