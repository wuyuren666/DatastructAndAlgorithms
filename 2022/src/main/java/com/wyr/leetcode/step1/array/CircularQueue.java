package com.wyr.leetcode.step1.array;

/**
 * 数组实现循环队列
 * https://leetcode.cn/problems/design-circular-queue/submissions/
 */
public class CircularQueue {
    private int front;//队头指针
    private int rear;//队尾指针，需要注意的是rear指向的元素不是队列中的
    private int[] arr;


    public CircularQueue(int k) {
        System.out.println("hot-fix");
        //数组的长度应为k+1
        arr=new int[k+1];
    }

    //入队
    public boolean enQueue(int value) {
        //队列已满
        if((rear+1)%arr.length==front){
            return false;
        }
        arr[rear]=value;
        //队尾指针后移
        rear=(rear+1)%arr.length;
        return true;
    }

    //出队
    public boolean deQueue() {
        //队列为空
        if(rear==front){
            return false;
        }
        //队头指针后移
        front=(front+1)%arr.length;
        return true;
    }

    //获取队头元素，并不是出队
    public int Front() {
        if(rear==front){
            return -1;
        }
        //因为只是获取队头元素，不出队，所以队头指针不用动
        int value=arr[front];
        return value;
    }


    //获取队尾元素
    public int Rear() {
        //队列为空
        if(rear==front){
            return -1;
        }
        int value=0;
        //需要注意rear并不是指向的就是队尾元素，后移一位才是真正队尾元素所在的位置
        if(rear==0){
            value=arr[rear+arr.length-1];
        }else{
            value=arr[rear-1];
        }
        return value;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        //代表为空
        if(rear==front){
            return true;
        }
        return false;
    }

    //判断队列是否已满
    public boolean isFull() {
        //代表队列已满
        if((rear+1)%arr.length==front){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

    }



}
