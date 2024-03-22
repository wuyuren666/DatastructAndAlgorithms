package com.wyr.leetcode.step2.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]
 * （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * https://leetcode.cn/problems/4sum/description/
 */
public class FourSumTest {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 先排序
        Arrays.sort(nums);
        int a = 0;
        while (a < nums.length - 3) {
            // 处理 a 重复
            if (a > 0 && nums[a] == nums[a - 1]) {
                a++;
                continue;
            }

            int b = a + 1;
            while (b < nums.length - 2) {
                // 处理 b 重复
                if (b > a+1 && nums[b] == nums[b - 1]) {
                    b++;
                    continue;
                }

                int c = b + 1;
                int d = nums.length - 1;
                // [-3,-2,-1,0,0,1,2,3]
                while (c < d) {
                    long sum =(long)nums[a]+nums[b]+nums[c]+nums[d];
                    if (sum==target) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[a]);
                        tmp.add(nums[b]);
                        tmp.add(nums[c]);
                        tmp.add(nums[d]);
                        res.add(tmp);
                        // 处理 c 和 d 重复
                        while(c+1<d-1&&nums[c+1] == nums[c]&&nums[d-1]==nums[d]){
                            c++;
                            d--;
                        }
                        c++;
                        d--;
                    } else if (sum<target) {
                        c++;
                    } else {
                        d--;
                    }
                }
                b++;
            }
            a++;
        }
        return res;
    }
}
