package com.xiekang.exercise.concurret.wait;

public class ThreadDemoA extends Thread{

    Object lock;

    public ThreadDemoA(Object o) {
        this.lock = o;
    }

    @Override
    public void run() {

        synchronized (lock){
            System.out.println("ThreadDemoA start");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadDemoA end");
        }
    }

}
