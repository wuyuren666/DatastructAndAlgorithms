package com.wyr.leetcode.step1.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/submissions/

 */


public class MaxProfitTest {



    //可以不新开数组，直接记录minPrice
    public int maxProfitBest(int[] prices) {
        int N=prices.length;
        if(N==1)
            return 0;
        //minPrice记录[0,i]天中的最小价格
        int minPrice=prices[0];
        int maxSal=0;

        for(int i=1;i<N;i++){
            if(prices[i]<minPrice){
                minPrice=prices[i];
            }
            maxSal=Math.max(prices[i]-minPrice,maxSal);
        }
        return maxSal;
    }

    //用数组
    public int maxProfit3(int[] prices) {
        int N=prices.length;
        //dp1[i]代表我在第i天卖出所能获得的最大利润
        int dp1[]=new int [N];
        dp1[0]=0;
        //dp2[i]代表第0～i天中的最小价格
        int dp2[]=new int[N];
        dp2[0]=prices[0];
        //先填dp2的表
        for(int i=1;i<N;i++){
            if(prices[i]<dp2[i-1]){
                //当天价格如果低于前0～i-1中的最小价格
                dp2[i]=prices[i];
            }else{
                //当天价格如果高于前0～i-1中的最小价格
                dp2[i]=dp2[i-1];
            }
        }
        int maxSal=dp1[0];
        //填dp1这张表
        for(int i=1;i<N;i++){
            //拿当天价格-0~i天中最小的价格
            dp1[i]=prices[i]-dp2[i];
            if(dp1[i]>maxSal){
                maxSal=dp1[i];
            }
        }
        return maxSal;
    }









    //动态规划版本（最后一个例子过不了）
    public int maxProfit(int[] prices) {
        int N=prices.length;
        int [][] dp=new int [N][N];//行下标和列下标为0的我们不用

        //填对角线元素，代表当天买当天卖，根本不赚钱
        for(int i=1;i<N;i++){
            dp[i][i]=0;
        }
        int maxSal=0;
        //接下来就一行一行的去填
        for(int i=1;i<N;i++){ //第i天买
            for(int j=i+1;j<N;j++){ //第j天卖
                dp[i][j]=prices[j-1]-prices[i-1];
                maxSal=maxSal<dp[i][j]?dp[i][j]:maxSal;
            }
        }
        return maxSal;
    }





    //动态规划版本（比较次），在leetCode上很奇怪，内存超出限制
    public static int maxProfit2(int [] prices){
        if(prices==null||prices.length==0||prices.length==1)
            return 0;

        int N=prices.length;
        int[][] dp=new int[N+1][N+1];
        int[] b=new int[N+1];

        for(int sIndex=N-1;sIndex>0;sIndex--){
            for(int bIndex=0;bIndex<=sIndex-1;bIndex++){
                //卖
                int p1= prices[sIndex]-prices[bIndex];
                //不卖
                int p2= dp[bIndex][sIndex+1];
                dp[bIndex][sIndex]=Math.max(p1,p2);
            }
        }

        for(int index=N-2;index>=0;index--){
            //可能买
            int p1= dp[index][index+1];
            //可能不买
            int p2=b[index+1];
            b[index]=Math.max(p1,p2);
        }
        return b[0];
    }


    public static int maxProfit1(int [] prices){
        if(prices==null||prices.length==0||prices.length==1)
            return 0;
        return buy(prices,0);
    }


    //[7,1,5,3,6,4]
    public static int buy(int[] prices, int index) {
        if (index == prices.length||index==prices.length-1) //在第index==6或者index==5时买入，肯定不会赚钱
            return 0;
        //不在最后一天，或者最后一天的后面一天买
        //在第index天可能买
        int p1 = sall(prices, index, index + 1);
        //在第index天可能不买
        int p2 = buy(prices, index + 1);
        return Math.max(p1, p2);
    }

    //bIndex:买入的日期，sIndex卖的日期
    public static int sall(int[] prices, int bIndex, int sIndex) {
        if (sIndex == prices.length)
            return 0;
        //卖
        int p1 = prices[sIndex] - prices[bIndex];
        //不卖
        int p2 = sall(prices, bIndex, sIndex + 1);
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int [] prices={6,7,7,8,1,3,2,2,5};
       /* System.out.println(maxProfit1(prices));
        System.out.println(maxProfit2(prices));*/
        ArrayList<Integer> res=(ArrayList<Integer>) process(prices);
        for(Integer i:res){
            System.out.println(i);
        }
    }

    public static List<Integer> process(int []num){
        List<Integer> res=new ArrayList<Integer>();
        for(int i: num){
            res.add(i);
        }
        return res.stream().distinct().sorted().collect(Collectors.toList());
    }
}
