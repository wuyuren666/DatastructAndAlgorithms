package com.wyr.leetcode.step1.multiThread;


import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * https://leetcode.cn/problems/print-in-order/description/
 *
 * 方案3，使用信号量
 */
public class FooTest3 {
    //初始时候信号量是0
    private final Semaphore semaphore1 = new Semaphore(0);
    private final Semaphore semaphore2 = new Semaphore(0);

    public FooTest3() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        semaphore1.release(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaphore1.acquire(1);
        printSecond.run();
        semaphore2.release(1);
    }

    public void third(Runnable printThird) throws InterruptedException {
        semaphore2.acquire(1);
        printThird.run();
    }
}
