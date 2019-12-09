package com.xiekang.exercise.concurret.wait;

public class ThreadDemoB extends Thread{

    Object lock;

    public ThreadDemoB(Object o) {
        this.lock = o;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("ThreadDemoB start");
            lock.notify();
            System.out.println("ThreadDemoB end");
        }
    }
}
