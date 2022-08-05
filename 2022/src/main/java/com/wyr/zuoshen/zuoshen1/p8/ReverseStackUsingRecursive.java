package com.wyr.zuoshen.zuoshen1.p8;

import java.util.Stack;

public class ReverseStackUsingRecursive {
    /**
     * 使用递归将栈逆序
     */
    //8.3逆序的逻辑
    public static void reverse83(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int temp=getBottom(stack);
        reverse83(stack);
        stack.push(temp);
    }

    //8.3练习，将栈底元素拿出，并且保持其他元素的相对顺序不变
    public static int getBottom(Stack<Integer> stack){
        int temp=stack.pop();
        if(stack.isEmpty()){
            return temp;
        }
        int a=getBottom(stack);
        stack.push(temp);
        return a;
    }

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



    public static void main(String[] args) {
            Stack<Integer> stack=new Stack<>();
            stack.push(1);
            stack.push(3);
            stack.push(5);
            stack.push(7);
            reverse83(stack);
            System.out.println("aaa");
    }
}
