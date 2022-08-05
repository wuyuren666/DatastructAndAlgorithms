package com.wyr.leetcode.step2.dp;

public class MaxProfitTest {
    /**
     * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * 返回 你能获得的 最大 利润。也就是买卖次数不限制
     *
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7 。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 既然买卖次数不限制，那么我们只要在所有相对低点买入，在所有的相对高点卖出，累加起来的收益就是最大的收益
     * 我们遍历整个数组，但凡后一个元素大于前一个元素，就代表股票处于上涨的阶段
     */
    public int maxProfit(int[] prices) {
        int maxMoney=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                maxMoney+=prices[i]-prices[i-1];
            }
        }
        return maxMoney;
    }
}
