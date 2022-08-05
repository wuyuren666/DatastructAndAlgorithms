package com.wyr.leetcode.step1.stack;

import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CQueue {
    public Stack<Integer> mainStack;
    public Stack<Integer> subStack;
    public CQueue() {
        mainStack=new Stack<>();
        subStack=new Stack<>();
    }

    //入队的操作
    public void appendTail(int value) {
        mainStack.push(value);
    }

    //出队的操作
    public int deleteHead() {
        //如果从栈为空
        if(subStack.isEmpty()){
            //如果主栈不为空
            if(!mainStack.isEmpty()){
                process();
            }else{
                return -1;
            }
        }
        return subStack.pop();
    }

    public int deleteHead2(){
        if(mainStack.isEmpty()&&subStack.isEmpty()){ //主栈且从栈为空，表示没有元素
            return -1;
        }else {
            if(subStack.isEmpty()){
                process();
            }
            return subStack.pop();
        }
    }

    public void process(){
        while(!mainStack.isEmpty()){
            subStack.push(mainStack.pop());
        }
    }
}
