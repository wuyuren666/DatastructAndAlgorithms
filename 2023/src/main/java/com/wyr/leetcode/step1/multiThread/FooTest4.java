package com.wyr.leetcode.step1.multiThread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

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
 * 方案4，使用CountDownLatch
 */
public class FooTest4 {

    /**
     * 减法计数器
     */
    private final CountDownLatch cdl1 = new CountDownLatch(1);
    private final CountDownLatch cdl2 = new CountDownLatch(1);


    public FooTest4() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        cdl1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        cdl1.await();
        printSecond.run();
        cdl2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        cdl2.await();
        printThird.run();
    }
}
