package com.xiekang.exercise.concurret.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    public static void main(String[] args)throws Exception{
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();
        lock.lock();
        lock.unlock();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    }
}
