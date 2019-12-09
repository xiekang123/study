package com.xiekang.exercise.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedissionLockTest {
    static int fixNum = 5;

    public static void main(String[] args) throws Exception{
        CountDownLatch count = new CountDownLatch(fixNum);
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.0.44:6379").setPassword("123456");
        RedissonClient redisson = Redisson.create(config);
        ExecutorService executorService = Executors.newFixedThreadPool(fixNum);
        for (int i = 0; i < fixNum; i++) {
            executorService.submit(new TestLock("client-"+i,redisson,count));
        }
        executorService.shutdown();
        count.await();
        System.out.println("所有的任务执行完成");
    }
}
