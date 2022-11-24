package com.wyr.leetcode.step2.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangeTest {

    /**
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     *
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     *
     * 假设每一种面额的硬币有无限个。
     *
     * 题目数据保证结果符合 32 位带符号整数。
     *
     *
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/coin-change-ii/
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) throws InterruptedException {


        for(int i=0;i<2;i++){
            System.out.print(i+'b'+1);
        }


       /* int []a=new int[]{1,3};
        System.out.println(change(30,a));*/
    }

    //暴力递归的方法
    public static int change(int amount, int[] coins) {

        return process(coins,0,amount);
    }

    public static int process(int [] coins, int index, int rest){
        if(index==coins.length){ //当此时都没有钱可以选择了，你是否已经凑成功了？
            return rest==0?1:0;
        }
        int ways=0;
        for(int zhang=0;zhang<=rest/coins[index];zhang++){//这里可以完全保证rest不会小于0
            ways+=process(coins,index+1,rest-zhang*coins[index]);
        }
        return ways;
    }



    //动态规划版本
    public int changeDP(int amount, int[] coins) {
        int M=coins.length+1;
        int N=amount+1;
        int[][] dp=new int[M][N];

        for(int j=0;j<N;j++){
            dp[M-1][j]= j==0?1:0;
        }

        for(int index=M-2;index>=0;index--){
            for(int rest=0;rest<N;rest++){
                int ways=0;
                //这个枚举行为可以优化！！！
                for(int zhang=0;zhang<=rest/coins[index];zhang++){
                     ways+=dp[index+1][rest-zhang*coins[index]];
                }
                dp[index][rest]=ways;
            }
        }
        return dp[0][amount];
    }

    //动态规划版本（最优），优化了很多
    public int changeDPBest(int amount, int[] coins) {
        int M=coins.length+1;
        int N=amount+1;
        int[][] dp=new int[M][N];

        for(int j=0;j<N;j++){
            dp[M-1][j]= j==0?1:0;
        }
        for(int index=M-2;index>=0;index--){
            for(int rest=0;rest<N;rest++){
                //我当前这个 dp[index][rest]就依赖我同一行的和我隔着coins[index]位置的那个格子
                //加上我同一行，下一列的格子
                dp[index][rest]=dp[index+1][rest];
                if(rest-coins[index]>=0){ //列没越界
                    dp[index][rest]+=dp[index][rest-coins[index]];
                }
            }
        }
        return dp[0][amount];
    }
}
