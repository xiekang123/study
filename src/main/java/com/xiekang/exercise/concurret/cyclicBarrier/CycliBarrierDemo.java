package com.xiekang.exercise.concurret.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
* @Description:
 *  CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会 开 门 ，
 * 所有被 屏 障 拦 截的线 程 才 会 继续工 作 。
 * CyclicBarrier 默 认 的构造 方 法 是CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量，
 * 每个线程调用 await 方法告诉CyclicBarrier 当前线程已经到达了屏障，然后当前线程被阻塞。
 *
 * 使用场景
 * 当存在需要所有的子任务都完成时，才执行主任务，这个时候就可以选择使用 CyclicBarrier
 *
* @Author:  xiekang
* @CreateDate: 2019/11/28 16:14
*/
public class CycliBarrierDemo extends Thread {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new CycliBarrierDemo());
        new  DataImportThread("path1",cyclicBarrier).start();
        new  DataImportThread("path2",cyclicBarrier).start();
        new  DataImportThread("path3",cyclicBarrier).start();
    }

    @Override
    public void run() {
        System.out.println("开始分析数据");
    }
}
