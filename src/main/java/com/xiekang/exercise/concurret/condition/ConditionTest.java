package com.xiekang.exercise.concurret.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
* @Description:
 *  condition:  实现线程之间的通讯，通常结合Lock使用
 *  condition.await() 让当前线程阻塞并且释放锁(将该线程放到等待队列（单项链表 condition中）)
 *  condition.single()  将阻塞的线程进行唤醒，重新去竞争锁(把等待队列中的最前面的线程删除，并且添加到同步队列（双向链表 AQS中）)
 *
 *
* @Author:  xiekang
* @CreateDate: 2019/11/28 14:50
*/
public class ConditionTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new ConditionWait(lock,condition),"thread-first").start();
        new Thread(new ConditionWait(lock,condition),"thread-two").start();
        new Thread(new ConditionSingle(lock,condition)).start();
    }
}
