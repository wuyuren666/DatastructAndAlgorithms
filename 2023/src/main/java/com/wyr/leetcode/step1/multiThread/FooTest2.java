package com.wyr.leetcode.step1.multiThread;


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
 * 方案2，使用原子类
 */
public class FooTest2 {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public FooTest2() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        atomicInteger.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (atomicInteger.get()!=1){

        }
        printSecond.run();
        atomicInteger.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (atomicInteger.get()!=2){

        }
        printThird.run();
        atomicInteger.getAndIncrement();
    }
}
