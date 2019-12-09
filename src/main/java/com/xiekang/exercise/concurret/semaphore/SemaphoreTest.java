package com.xiekang.exercise.concurret.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
* @Description:
 *   Semaphore 比较常见的就是用来做限流操作了
 *semaphore 也就是我们常说的信号灯， semaphore 可以控制同时访问的线程个数，通过acquire 获取一个许可，
 * 如果没有就等待，通过 release 释放一个许可。有点类似限流的作用。叫信号灯的原因也和他的用处有关，
 * 比如某商场就 5 个停车位，每个停车位只能停一辆车，如果这个时候来了 10 辆车，必须要等前面有空的车位才能进入。
 *
* @Author:  xiekang
* @CreateDate: 2019/11/28 16:01
*/
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Car(i,semaphore).start();
        }
    }


    static class Car extends Thread{

        private int num;
        private Semaphore semaphore;

        public Car(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        public void run(){
            try{
                semaphore.acquire();//获取的一个许可证
                System.out.println("第"+num+"开进来了");
                TimeUnit.SECONDS.sleep(2);
                semaphore.release();
                System.out.println("第"+num+"开走了");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
