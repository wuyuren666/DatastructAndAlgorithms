package com.wyr.bigFactory.date9_1;

import sun.jvm.hotspot.runtime.Thread;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ZhengFangXingTest{

    /**
     * 一日小A走在路上时看到路边摆着一面大镜子，他对着这面镜子注视了半天，突然发现自己穿越到了另一个世界！
     * 这个世界很奇怪：所有的地方可以视为一个n行m列的矩阵，每一个位置上都有一个非负整数或者-1。这时他的
     * 耳边想起一个很空灵的声音："如果你想要回到原来的世界，你需要解决下面的问题：你需要在整个矩阵上选择
     * 一个正方形的区域，使得该区域不包含任何负数，并且该区域的数字之和最大。"
     */

    static int max=Integer.MIN_VALUE;
    public static void main(String[] args) {
        int [][] nums={{3,-1,5,6},{0,3,1,6},{-1,8,0,1},{4,0,0,0}};
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums[0].length;j++){
                if(nums[i][j]>=0){
                    max=Math.max(process11_16(nums,i,j,i,j),max);
                }
            }

        }
        System.out.println(max);
    }


    public static int process(int [][] nums, int curX, int curY,int preX, int preY){
        //越界或者当前数字是负数
        if(curX<0||curX>=nums.length||curY<0||curY>=nums[0].length||nums[curX][curY]<0){
            return -1; //标志位
        }

        int tempSum=nums[curX][curY];
        //累加当前点上面的那些点的值
        for(int row=curX-1;row>=preX;row--){
            if(nums[row][curY]<0){
                return -1; //标志位
            }
            tempSum+=nums[row][curY];
        }
        //累加当前点左边的那些点的值
        for(int col=curY-1;col>=preY;col--){
            if(nums[curX][col]<0){
                return -1; //标志位
            }
            tempSum+=nums[curX][col];
        }

        if(process(nums,curX+1,curY+1,preX,preY)!=-1){
            tempSum+=process(nums,curX+1,curY+1,preX,preY);
        }
        return tempSum;
    }

    public static int process11_16(int [][] nums, int curX, int curY, int beginX,int beginY){
        //越界的情况
        if(curX<0||curX>=nums.length||curY<0||curY>=nums[0].length){
            return -1; //-1代表标识位
        }
        //当前数字就是负数
        if(nums[curX][curY]<0){
            return -1; //-1代表标识位
        }
        //走到下面的流程说明当前数字不是负数，但是仍然需要看左边和上边是否出现负数
        int tempSum = nums[curX][curY];

        //累加当前点左边的值
        for(int j=curY-1;j>=beginY;j--){
            if(nums[curX][j]<0){
                return -1;
            }
            tempSum+=nums[curX][j];
        }
        //累加当前点上边的值
        for(int i=curX-1;i>=beginX;i--){
            if(nums[i][curY]<0){
                return -1;
            }
            tempSum+=nums[i][curY];
        }
        if(process11_16(nums,curX+1,curY+1,beginX,beginY)!=-1){
            tempSum+=process11_16(nums,curX+1,curY+1,beginX,beginY);
        }
        return tempSum;
    }
}
