package com.wyr.leetcode.step1.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinCostClimbingStairsTest {
    /**
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
     * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     *
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     *
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15 。
     *
     * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
     * 输出：6
     * 解释：你将从下标为 0 的台阶开始。
     * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
     * 总花费为 6 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/min-cost-climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //动态规划版本
    public int minCostClimbingStairs1(int[] cost) {
        int [] dp=new int[cost.length];

        for(int i=cost.length-1;i>=0;i--){
            //选择走两步
            int p1=cost[i]+(i+2>=cost.length?0:dp[i+2]);
            //选择走一步
            int p2=cost[i]+(i+1>=cost.length?0:dp[i+1]);
            dp[i]=Math.min(p1,p2);
        }
        return Math.min(dp[0],dp[1]);
    }

    //暴力递归版本
    public int minCostClimbingStairs2(int[] cost) {
        return Math.min(process(cost,0),process(cost,1));
    }

    public int process(int [] cost, int index){
        if(index>=cost.length){
            return 0;
        }
        //我选择走一级台阶
        int p1= cost[index]+process(cost,index+1);
        //我选择走二级台阶
        int p2=cost [index]+process(cost,index+2);

        return Math.min(p1,p2);
    }

    public static void main(String[] args) {
        List<String> l1=new ArrayList<>();
        Map<List<String> ,Integer> map=new HashMap<>();
        l1.add("a");l1.add("b");
        List<String> l2=new ArrayList<>();
        l2.add("a");l2.add("b");
        map.put(l1,1);
        map.put(l2,2); //相当于更新操作
        System.out.println(map.size());//1
        System.out.println(map.get(l1));//2
        System.out.println(map.get(l2));//2
    }


}
