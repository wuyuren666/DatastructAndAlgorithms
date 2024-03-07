package com.wyr.leetcode.step1.multiThread;

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
 * 方案1，使用synchronized，自旋去实现
 */
public class FooTest1 {

    private volatile int flag = 1;

    private Object object = new Object();

    public FooTest1() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (object){
            while(flag!=1)
                object.wait();
            printFirst.run();
            flag=2;
            object.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (object) {
            while (flag != 2)
                object.wait();
            printSecond.run();
            flag = 3;
            object.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (object){
            while (flag!=3)
                object.wait();
            printThird.run();
        }
    }
}
