package com.wyr.leetcode.step2.array;

public class TwoSumTest {
    /**
     * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列 ，
     * 请你从数组中找出满足相加之和等于目标数target 的两个数。
     * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     *
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     *
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     *
     * 你所设计的解决方案必须只使用常量级的额外空间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int[] twoSum(int[] numbers, int target) {
        // 一左一右两个指针相向而行
        int left=0;
        int right=numbers.length-1;
        while(left<right){
            if(numbers[left]+numbers[right]==target){
                // 题目要求的索引是从 1 开始的
                return new int[]{left+1,right+1};
            }else if(numbers[left]+numbers[right]<target){
                left++; // 让 sum 大一点
            }else{
                right--; // 让 sum 小一点
            }
        }
        return new int[]{-1,-1};
    }
}
