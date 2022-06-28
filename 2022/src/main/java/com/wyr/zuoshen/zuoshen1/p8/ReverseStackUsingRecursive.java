package com.wyr.zuoshen.zuoshen1.p8;

import java.util.Stack;

public class ReverseStackUsingRecursive {
    /**
     * 使用递归将栈逆序
     */

    //逆序的逻辑
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i=f(stack);
        reverse(stack);
        stack.push(i);
    }


    //就是将栈底元素拿出来，并保持原先的顺序不变
    public static int f(Stack<Integer> stack){
         int result=stack.pop();
         if(stack.isEmpty()){
             return result;
         }
         int last=f(stack);
         stack.push(result);
         return last;
    }
}
