package com.wyr.zuoshen.zuoshen1.p14;

import java.util.*;

public class MonotonicStack {

    /**
     * 单调栈结构
     *
     * 给你一个数组，找出数组中每一个元素，其左边大于他的最近的元素，和其右边大于他的最近的元素
     */

    //数组中没有重复值的情况
    public static Map<Integer, int[]> process1(int [] arr){
        //存放下标，每次弹出数据的时候，就保存记录，下标对应的元素值严格 栈底->栈顶：大->小
        Stack<Integer> stack=new Stack<>();
        Map<Integer,int []> res=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            while (!stack.isEmpty()&&arr[i]>arr[stack.peek()]){
               int curIndex=stack.pop(); //当前要记录数的下标
               int [] temp=new int[2];
               //temp[0]，左边大于本身的最大数的最近的下标
                // temp[1]，右边大于本身的最大数的最近的下标
               if(stack.isEmpty()){ //下面没有压着的数了
                   temp[0]=-1;
               }else{
                   temp[0]=stack.peek();
               }
               temp[1]=i;
               res.put(curIndex,temp);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){//栈非空
            int curIndex=stack.pop();
            int[] temp=new int[2];
            if(stack.isEmpty()){
                temp[0]=-1;
            }else {
                temp[0]=stack.peek();
            }
            temp[1]=-1;
            res.put(curIndex,temp);
        }
        return res;
    }

    //数组中有重复值的情况
    public static Map<Integer, int[]> process2(int [] arr){
        //存放下标，每次弹出数据的时候，就保存记录，下标对应的元素值严格 栈底->栈顶：大->小
        Stack<LinkedList<Integer>> stack=new Stack<>();
        Map<Integer, int[]> res=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            while (!stack.isEmpty()&&arr[i]>arr[stack.peek().peekLast()]){
                LinkedList<Integer> tempList=stack.pop();
                for(Integer curIndex: tempList){
                    int [] tempArr=new int[2];
                    //temp[0]，左边大于本身的最大数的最近的下标
                    // temp[1]，右边大于本身的最大数的最近的下标
                    if(stack.isEmpty()){ //下面没有压这的数了
                        tempArr[0]=-1;
                    }else{
                        tempArr[0]=stack.peek().peekLast();
                    }
                    tempArr[1]=i;
                    res.put(curIndex,tempArr);
                }
            }
            if(stack.isEmpty()||arr[stack.peek().peekLast()]!=arr[i]){
                LinkedList<Integer> temp=new LinkedList<>();
                temp.add(i);
                stack.push(temp);
            }else {
                stack.peek().addLast(i);
            }

        }
        while (!stack.isEmpty()){//栈非空
            LinkedList<Integer> tempList=stack.pop();
            for(Integer curIndex: tempList){
                int[] temp=new int[2];
                if(stack.isEmpty()){
                    temp[0]=-1;
                }else {
                    temp[0]=stack.peek().peekLast();
                }
                temp[1]=-1;
                res.put(curIndex,temp);
            }
        }
        return res;
    }

    /**
     * 定义：数组中累积和和最小值的乘积，假设叫做指标A
     * 给定一个数组，请返回子数组中，指标A最大的值
     */
    public static int getMaxA(int [] arr){
        Map<Integer, int[]> map=process2(arr);
        int maxRes=Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++){
            int[] tempArr = map.get(i);
            int beginIndex,endIndex,tempSum=0;
            if(tempArr[0]==-1){
                beginIndex=i;
            }else {
                beginIndex=tempArr[0];
            }
            if(tempArr[1]==-1){
                endIndex=i;
            }else {
                endIndex=tempArr[1];
            }
            for(int j=beginIndex;j<=endIndex;j++){
                tempSum+=arr[j];
            }
            maxRes=Math.max(tempSum*arr[i],maxRes);
        }
        return maxRes;
    }

    public static void main(String[] args) {
        //int [] arr={3,0,1,2,6,7,5};
        int [] arr={3,0,2,1,4};
        System.out.println(getMaxA(arr));
    }
}
