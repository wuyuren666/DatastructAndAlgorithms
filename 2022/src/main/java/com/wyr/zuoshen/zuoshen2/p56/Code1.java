package com.wyr.zuoshen.zuoshen2.p56;

import java.util.Arrays;

public class Code1 {
    /**
     * 最多装两个人的船同时过河问题
     * 给你一个正整数数组arr，代表若干个人的体重
     * 再给定一个正数limit，表示所有船公共拥有的载重量
     * 每艘船最多坐两个人，且不能超过载重
     * 想让所有的人同时过河，并且用最好的分配方法，让船尽量少
     * 返回最少的船数
     */

    public static int minBoat(int [] arr, int limit){
        //先排序
        Arrays.sort(arr);
        int doubleCount=0; //记录成对的对号
        int leftFloatCount=0; //记录左边的叉号
        int rightFloatCount=0; //记录右边的叉号

        int mid=limit/2;//分割线
        int p2=0; //p2指向分割线的右边，往右滑

        for(;p2<arr.length;p2++){
            if(arr[p2]<=mid){
                continue;
            }
            break;
        }
        int p1=p2-1;//p1指向分割线的左边，往左滑


        while(p1>=0&&arr[p1]+arr[p2]>limit){
            p1--;
            leftFloatCount++;
        }

        while(p1>=0&&p2<arr.length&&arr[p1]+arr[p2]<=limit){
            p1--;
            p2++;
            doubleCount++;
        }

        while(p2<arr.length){ //说明，右边还没消化完
            p2++;
            rightFloatCount++;
        }

        while(p1>=0){ //说明，左边还没消化完
            p1--;
            leftFloatCount++;
        }
        //记录成对的对号+左边的叉号/2向上取整+右边的叉号
        return doubleCount+(leftFloatCount==0?0:(int)(Math.ceil(leftFloatCount/2.0)))+rightFloatCount;
    }

    public static void main(String[] args) {
        int [] weights={1,1,1,1,1,2,3,5,5,5,5,6,6,8,8,9,10};
        System.out.println(minBoat(weights,10));
    }


}

