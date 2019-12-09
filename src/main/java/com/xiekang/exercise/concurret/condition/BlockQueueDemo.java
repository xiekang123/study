package com.xiekang.exercise.concurret.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* @Description:  使用Lock和condition实现一个阻塞队列
* @Author:  xiekang
* @CreateDate: 2019/11/28 16:43
*/
public class BlockQueueDemo {

    private int maxSize ;//队列的最大数量

    private LinkedList<Object> buffer;    //生产者容器

    private Lock lock;

    private Condition fullCondition;

    private Condition notFullCondition;

    public BlockQueueDemo(int maxSize ) {
        this.maxSize  = maxSize ;
        //初始化参数
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    /**
     *  添加一个元素
     * @param obj
     */
    public void add(Object obj){
        try {
            lock.lockInterruptibly(); //获取到锁
            while (maxSize == buffer.size()) {//队列已经满了
                notFullCondition.await();//添加的线程进入等待状态
            }
            buffer.add(obj);
            System.out.println("写入元素为:"+obj);
            fullCondition.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     *  取出元素
     * @return
     */
    public Object take(){
        Object obj = null ;
        try{
            lock.lockInterruptibly();//加锁
            while (buffer.size() == 0){//容器里没有元素
                fullCondition.await();//让消费者阻塞
            }
            obj = buffer.poll();
            notFullCondition.signal();//通知生产者，生产数据
        } catch (InterruptedException e) {
            e.printStackTrace();
            notFullCondition.signal();
        } finally {
            lock.unlock();
        }
        return obj;
    }

}
