package com.wyr.zuoshen.zuoshen1.p14;

import java.util.Arrays;
import java.util.LinkedList;

public class SlidingWindow {
    /**
     * 给你一个数组，规定一个k值，代表子数组的长度
     * 请你算得到每次大小为k的窗口中的最大值和最小值
     * 要求，平均时间复杂度为O(1)
     *
     * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
     */
    public static int[] getMaxWindow(int []arr, int k){
        if(arr==null||k<=0||arr.length<k){
            return new int[0];
        }
        //下标  值 大->小 ，注意需要是严格的从大到小
        //思想，使用双端队列，每次都向队尾加入元素，但是要满足我们大->小的规定，有比当前数小的就弹出
        //最终，队头位置放置的就是整个滑动窗口中的最大值
        LinkedList<Integer> qmax=new LinkedList<>();
        int [] res=new int[arr.length-k+1]; //返回值存放的是所有窗口中的最大值
        int L=0;
        for(int R=0;R<arr.length;R++){
            //R->arr[R]
            while (!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[R]){
                qmax.pollLast();
            }
            qmax.addLast(R);
            if(qmax.peekFirst()<L){  //下标过期了
                qmax.pollFirst();
            }
            //窗口形成了
            if(R-L==k-1){
                res[L++]=arr[qmax.peekFirst()];
            }
        }
        return res;
    }
//https://leetcode.cn/problems/YaVDxD/
    public static void main(String[] args) {
        int [] arr={0,3,6,1,7,3,0,5};
        int[] maxWindow = getMaxWindow(arr, 3);
        Arrays.stream(maxWindow).forEach(System.out::println);
    }
}
