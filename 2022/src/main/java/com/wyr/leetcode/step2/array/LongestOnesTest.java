package com.wyr.leetcode.step2.array;

public class LongestOnesTest {
    /**
     * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
     *
     * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：[1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     *
     * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * 输出：10
     * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/max-consecutive-ones-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */



    //使用滑动窗口最长的模版
    public int longestOnes(int[] nums, int k) {
        int left=0;
        int right=0;
        int ans=Integer.MIN_VALUE;
        int tempSumOfZero=0;
        while(right<nums.length){
            if(nums[right]==0){
                tempSumOfZero++;
            }
            while(tempSumOfZero>k){
                if(nums[left++]==0){
                    tempSumOfZero--;
                }
            }
            ans=Math.max(ans,right-left+1);
            right++;
        }
        return ans;
    }
}
