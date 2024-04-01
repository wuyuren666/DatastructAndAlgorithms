package com.wyr.leetcode.step2.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 * https://leetcode.cn/problems/3sum/description/
 */
public class ThreeSumTest {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 排序，然后双指针的做法
        Arrays.sort(nums);
        // 保持k固定不动，然后i和j初始化为k+1和nums.length-1
        // 每次确定一个k的位置，然后利用nums[i]+nums[j]==-nums[k]的关系
        // 去不断往中间逼近i和j，直到i和j相遇
        int k=0;
        while(k<nums.length-2){
            if(k>0&&nums[k-1]==nums[k]){
                k++;
                continue;
            }
            //改进1
            if(nums[k]+nums[k+1]+nums[k+2]>0){
                break;
            }
            int target = -nums[k];
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) { //i，j不相遇
                //改进2:
                if(nums[i]+nums[i+1]>0-nums[k]){
                    break;
                }
                if (nums[i] + nums[j] == target) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[k]);
                    list.add(nums[i]);
                    list.add(nums[j]);
                    res.add(list);
                    while(i+1<j-1&&nums[i+1]==nums[i]&&nums[j-1]==nums[j]){
                        i++;
                        j--;
                    }
                    i++;
                    j--;
                } else if (nums[i] + nums[j] > target) {
                    j--;
                } else {
                    i++;
                }
            }
            k++;//k别忘了自增
        }
        return res;
    }
}
