package com.wyr.leetcode.step2.stack;

import java.util.Stack;

/**
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-of-stacks-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedStack {
    //使用递归
    public Stack<Integer> stack;

    public SortedStack() {
        this.stack=new Stack<>();
    }

    public void push(int val) {
        sort(val);
    }

    public void sort(int val){
        if(stack.isEmpty()||val<stack.peek()){
            stack.push(val);
        }else{
            int temp=stack.pop();
            sort(val);
            stack.push(temp);
        }
    }

    public void pop() {
        if(stack.isEmpty()){
            return;
        }
        stack.pop();
    }

    public int peek() {
        if(isEmpty()){
            return -1;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
