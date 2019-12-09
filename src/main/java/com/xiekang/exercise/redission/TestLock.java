package com.xiekang.exercise.redission;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestLock implements Runnable {
    private CountDownLatch countDownLatch;

    private RedissonClient client;

    private String name;

    public TestLock(String name, RedissonClient client,CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.client = client;
        this.name = name;
    }

    @Override
    public void run() {
        RLock lock = client.getLock("testLock");//分布式重入锁
        try{
            System.out.println("---------"+this.name+"---------等待获取锁-----");
            lock.lock();
            try{
                System.out.println("---------"+this.name+"---------获得到了锁-----");
                Thread.sleep(2*100);
                System.out.println("---------"+this.name+"---------使用锁完毕-----");
                countDownLatch.countDown();
            }finally {
                System.out.println("---------"+this.name+"---------释放了锁-----");
                lock.unlock();
            }
            /*if(lock.tryLock(300,30, TimeUnit.MILLISECONDS)){


            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
