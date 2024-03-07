package com.wyr.leetcode.step1.multiThread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class FooBarTest1 {
    /**
     * 给你一个类：
     *
     * class FooBar {
     *   public void foo() {
     *     for (int i = 0; i < n; i++) {
     *       print("foo");
     *     }
     *   }
     *
     *   public void bar() {
     *     for (int i = 0; i < n; i++) {
     *       print("bar");
     *     }
     *   }
     * }
     * 两个不同的线程将会共用一个 FooBar 实例：
     *
     * 线程 A 将会调用 foo() 方法，而
     * 线程 B 将会调用 bar() 方法
     * 请设计修改程序，以确保 "foobar" 被输出 n 次。
     *
     *
     * 输入：n = 2
     * 输出："foobarfoobar"
     * 解释："foobar" 将被输出两次。
     *
     * https://leetcode.cn/problems/print-foobar-alternately/description/
     *
     * 解决方案：使用Semaphore+CyclicBarrier
     */
    private int n;

    private final Semaphore s = new Semaphore(0); //控制先A后B
    private final CyclicBarrier cb=new CyclicBarrier(2);

    public FooBarTest1(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            printFoo.run();
            s.release();
            try{
                cb.await();
            }catch(Exception e){

            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            s.acquire();
            printBar.run();
            try{
                cb.await();
            }catch(Exception e){

            }
        }
    }
}
