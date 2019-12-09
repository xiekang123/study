package com.xiekang.exercise.concurret.condition;

/**
* @Description:  阻塞队列的测试类
* @Author:  xiekang
* @CreateDate: 2019/11/28 17:29
*/
public class BlockQueueTest {

    public static void main(String[] args) throws Exception{
        BlockQueueDemo blockQueue = new BlockQueueDemo(2);
        new Thread(()->{
            for (int i = 0; i <3 ; i++) {
                System.out.println(blockQueue.take());
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i <3 ; i++){
                blockQueue.add(i);
            }
        }).start();

    }
}
