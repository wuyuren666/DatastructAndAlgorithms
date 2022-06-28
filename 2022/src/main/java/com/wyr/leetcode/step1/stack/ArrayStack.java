package com.wyr.leetcode.step1.stack;


public class ArrayStack {
    public int top;//栈顶指针
    public  int [] arr;

    public ArrayStack(int size) {
        top=-1;
        this.arr = new int[size];
    }

    //压栈的方法
    public boolean push(int val){
        if(top==arr.length-1){
            return false;
        }
        arr[++top]=val;
        return true;
    }

    //弹栈的方法
    public void pop(){
        if(top==-1){
            System.out.println("当前栈为空");
            return;
        }
        System.out.println(arr[top--]);
    }

    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

    }
}
