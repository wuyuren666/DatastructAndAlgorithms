package com.wyr.leetcode.step2.tanXin;

import java.util.Arrays;

/**
 *
 *
 *
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * https://leetcode.cn/problems/jump-game/description/
 */
public class CanJumpTest {

    public boolean canJump(int[] nums) {
        //数组长度为1，可以直接返回true
        if(nums.length==1){
            return true;
        }
        //走到这意味着数组长度至少为2
        int cover=nums[0];//代表可以覆盖的最大范围，初始化为num[0]
        /**
         * 从0下标开始，去看当前下标能够覆盖的最大一个范围，即0下标时，[0,cover]处都是可以到达的。
         * 比如[2,3,1,1,4]这个例子，初始化cover是2，即0位置可以覆盖的最大范围是[0,2]，然后继续
         * 更新cover，i是0的时候cover不会变，i是1的时候cover就会更新为4，就是到达终点了，返回true
         *
         * 比如[3,2,1,0,4]这个例子，cover初始化是3，即0位置可以覆盖的最大范围是[0,3]，然后继续
         * 更新cover，i是0的时候cover不会变，i是1的时候cover不变也是3，i是2的时候cover也不变是3
         * i是3的时候cover不变也是3，此时for循环结束，自始自终也不会到达终点，返回false
         *
         *
         * 刚看到本题一开始可能想：当前位置元素如果是 3，我究竟是跳一步呢，还是两步呢，还是三步呢，究竟跳几步才是最优呢？
         *
         * 其实跳几步无所谓，关键在于可跳的覆盖范围！
         *
         * 不一定非要明确一次究竟跳几步，每次取最大的跳跃步数，这个就是可以跳跃的覆盖范围。
         *
         * 这个范围内，别管是怎么跳的，反正一定可以跳过来。
         *
         * 那么这个问题就转化为跳跃覆盖范围究竟可不可以覆盖到终点！
         *
         * 每次移动取最大跳跃步数（得到最大的覆盖范围），每移动一个单位，就更新最大覆盖范围。
         *
         * 贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），整体最优解：最后得到整体最大覆盖范围，看是否能到终点。
         *
         * 局部最优推出全局最优，找不出反例，试试贪心！
         *
         */
        for(int i=0;i<=cover;i++){//这里i<=cover
            cover=Math.max(cover,i+nums[i]);
            if(cover>=nums.length-1){//只要能到达终点，就返回true
                return true;
            }
        }
        return false;
    }
}
