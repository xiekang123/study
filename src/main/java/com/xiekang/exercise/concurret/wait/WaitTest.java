package com.xiekang.exercise.concurret.wait;

public class WaitTest {

    public static void main(String[] args) {
        Object lock = new Object();
        ThreadDemoA threadDemoA = new ThreadDemoA(lock);
        ThreadDemoB threadDemoB = new ThreadDemoB(lock);
        threadDemoA.start();
        threadDemoB.start();
    }
}
