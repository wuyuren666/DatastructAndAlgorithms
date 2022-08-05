package com.wyr.leetcode.step1.dp;

import java.util.ArrayList;
import java.util.List;

public class GenerateTest {
    /**
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     *
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     *
     * https://leetcode.cn/problems/pascals-triangle/
     */

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> temp=new ArrayList<>();
        temp.add(1);
        res.add(temp);
        if(numRows==1){ //长度为1的情况下
            return res;
        }
        temp=new ArrayList<>();
        temp.add(1);
        temp.add(1);
        res.add(temp);
        if(numRows==2){ //长度为2的情况下
            return res;
        }

        int [][] dp=new int [numRows][numRows];
        //左边界全填1
        for(int i=0;i<numRows;i++){
            dp[i][0]=1;
        }
        //对角线全填1
        for(int i=1;i<numRows;i++){
            dp[i][i]=1;
        }
        //填剩下的格子
        for(int i=2;i<numRows;i++){
            temp=new ArrayList<>();
            temp.add(1);
            for(int j=1;j<i;j++){
                dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                temp.add(dp[i][j]);
            }
            temp.add(1);
            res.add(temp);
        }
        return res;

    }
}
