package com.wyr.leetcode.step1.dp;

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

 */
public class MaxProfitTest {

    //动态规划版本，在leetCode上很奇怪，内存超出限制
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
        int [] prices={7,6,4,3,1};
        System.out.println(maxProfit1(prices));
        System.out.println(maxProfit2(prices));
    }
}
