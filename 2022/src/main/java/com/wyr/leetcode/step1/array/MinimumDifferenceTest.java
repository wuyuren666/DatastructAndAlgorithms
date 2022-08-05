package com.wyr.leetcode.step1.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumDifferenceTest {
    /**
     * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
     *
     * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
     *
     * 返回可能的 最小差值 。
     *
     * 输入：nums = [90], k = 1
     * 输出：0
     * 解释：选出 1 名学生的分数，仅有 1 种方法：
     * - [90] 最高分和最低分之间的差值是 90 - 90 = 0
     * 可能的最小差值是 0
     *
     * 输入：nums = [9,4,1,7], k = 2
     * 输出：2
     * 解释：选出 2 名学生的分数，有 6 种方法：
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 4 = 5
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 1 = 8
     * - [9,4,1,7] 最高分和最低分之间的差值是 9 - 7 = 2
     * - [9,4,1,7] 最高分和最低分之间的差值是 4 - 1 = 3
     * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 4 = 3
     * - [9,4,1,7] 最高分和最低分之间的差值是 7 - 1 = 6
     * 可能的最小差值是 2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /*
        排序
        排序后逆序遍历，取每一个下标为i的数与它之前的下标为i-k+1的数进行相减
        从所有相减后的数中获取最小值即为答案

        举例 9 4 1 7 ， k = 2
        排序后为 1 4 7 9
        当i=3时，i-k+1=2，即取下标为3的数减去下标为2的数 = 2
        当i=2时，i-k+1=1, 取下标2的数减下标1的数 = 3
        当i=1时，i-l+1=0, 取下标1的数减下标0的数 = 3
        答案为所有相减之后得数的最小值 2

        其解题逻辑是，取k个数，要求最高和最低分差值最小化
        当数组进行排序后，在含有k个数的区间中
        相距k-1的两个数一定是这个有k个数的区间中的最大和最小值
        且它们在满足条件的情况下，数值大小最为接近
        则取所有区间的最大最小值进行相减获取其中的最小值即为答案

        执行用时：4 ms, 在所有 Java 提交中击败了100.00%的用户
        内存消耗：41.6 MB, 在所有 Java 提交中击败了5.13%的用户
    */

    public int minimumDifference(int[] nums, int k) {
        if(k==1){
            return 0;
        }
        //先排序
        Arrays.sort(nums);
        int ans=Integer.MAX_VALUE;
        for(int i=nums.length-1;i>=k-1;i--){
            ans=Math.min(ans,nums[i]-nums[i-k+1]);
        }
        return ans;
    }

}
