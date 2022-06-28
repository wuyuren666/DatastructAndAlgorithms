package com.wyr.leetcode.step1.stack;


import java.util.Stack;

/**
 *设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinStack {
    public static Stack<Integer> mainStack; //主栈
    public static Stack<Integer> minStack; //从栈，且其栈顶元素是最小值

    public MinStack() {
        mainStack=new Stack<>();
        minStack=new Stack<>();
    }

    //将元素压入堆栈
    public static void push(int val) {
        //如果从栈为空，或者新元素比从栈栈顶元素小
        if(minStack.isEmpty()||val<=minStack.peek()){
            minStack.push(val);
        }
        mainStack.push(val);
    }
    //删除栈顶的元素
    public static void pop() {
        //如果主栈的栈顶元素==从栈的栈顶元素
        if(mainStack.peek().equals(minStack.peek())){ //注意这里应该使用equals方法而不是==，因为这是引用类型的比较
            minStack.pop();//从栈栈顶元素弹出
        }
        mainStack.pop();//主栈栈顶元素弹出
    }
    //获取堆栈顶部的元素
    public static int top() {
        return mainStack.peek();
    }

    public static int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack stack=new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }
}
