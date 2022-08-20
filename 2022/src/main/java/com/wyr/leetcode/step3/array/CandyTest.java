package com.wyr.leetcode.step3.array;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.TreeSet;

public class CandyTest {
    /**
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     *
     * 你需要按照以下要求，给这些孩子分发糖果：
     *
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     *
     * 输入：ratings = [1,0,2]
     * 输出：5
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
     *
     * 输入：ratings = [1,2,2]
     * 输出：4
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
     *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/candy
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int candy(int[] ratings) {


        //两个预处理数组
        int [] left=new int [ratings.length];
        int [] right=new int[ratings.length];
        //左边开始填left，第一个孩子为1，后面的分数比前一个分数高就比前一个多加一个，否则就发1
        //比左边大就++
        //比左边小或者相等就归1
        for(int i=0;i<left.length;i++){
            if(i==0){
                left[i]=1;
            }else{
                if(ratings[i]>ratings[i-1]){
                    left[i]=left[i-1]+1;
                }else{
                    left[i]=1;
                }
            }
        }
        //右边开始填right，最后一个孩子为1，前面的比后一个分数高就比后一个多加一个，否则就发1
        for(int i=right.length-1;i>=0;i--){
            if(i==right.length-1){
                right[i]=1;
            }else{
                if(ratings[i]>ratings[i+1]){
                    right[i]=right[i+1]+1;
                }else{
                    right[i]=1;
                }
            }
        }
        //最终结果以max(left[i],right[i])为准
        int res=0;
        for(int i=0;i<right.length;i++){
            res+=left[i]>right[i]?left[i]:right[i];
        }
        return res;
    }


    /**
     * 现在多加一个要求：相邻的孩子如果分数一样，那必须一样多的糖
     */
    public int candy2(int[] ratings) {
        //两个预处理数组
        int [] left=new int [ratings.length];
        int [] right=new int[ratings.length];
        //这两个left和right生成策略比之前多一个分支
        //比左边大就++
        //比左边小就归1
        //和左边相等就一样
        for(int i=0;i<left.length;i++){
            if(i==0){
                left[i]=1;
            }else{
                if(ratings[i]>ratings[i-1]){ //比左边大
                    left[i]=left[i-1]+1;
                }else if(ratings[i]==ratings[i-1]){ //和左边相等
                    left[i]=ratings[i-1];
                }else{ //比左边小
                    left[i]=1;
                }
            }
        }
        //right的生成也差不多，就是多一个分支
        for(int i=right.length-1;i>=0;i--){
            if(i==right.length-1){
                right[i]=1;
            }else{
                if(ratings[i]>ratings[i+1]){ //比右边大
                    right[i]=right[i+1]+1;
                }else if(ratings[i]==ratings[i+1]){ //和右边相等
                    right[i]=right[i+1];
                }else{ //比右边小
                    right[i]=1;
                }
            }
        }

        //最终结果以max(left[i],right[i])为准
        int res=0;
        for(int i=0;i<right.length;i++){
            res+=left[i]>right[i]?left[i]:right[i];
        }
        return res;
    }

}
