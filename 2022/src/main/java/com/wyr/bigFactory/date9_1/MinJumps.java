package com.wyr.bigFactory.date9_1;

public class MinJumps {

    /**
     * 给你一个整数数组，你一开始在数组的第一个元素处（下标为0）
     * 每一步，你可以从下标i跳到i+1，或者i-1或者j
     *
     * i+1需要满足：i+1<arr.length
     * i-1需要满足i-1>=0
     *
     * j需要满足arr[i]==arr[j]，且i！=j
     */
    static int minCount=Integer.MAX_VALUE;
    public static void main(String[] args) {
        int [] arr= {100,-23,-23,404,100,23,23,23,3,404};
        minJumps(arr);
        System.out.println(minCount);
    }
    public static int minJumps (int[] nums) {
        process(nums,0,0);
        return minCount;
    }

    public static void process(int []arr, int curIndex, int cost){
        if(curIndex==arr.length-1){
            minCount=Math.min(minCount,cost);
            return;
        }
        //贪心的思想，每一次都先尝试走j，不能走j再往右移
        boolean flag=true;
        if(flag){
            for(int i=curIndex+1;i<=arr.length-1;i++){
                if(arr[i]==arr[curIndex]){
                    flag=false;
                    process(arr,i,cost+1);
                }
            }
        }
        if(flag){
            process(arr,curIndex+1,cost+1);
        }
    }
}
