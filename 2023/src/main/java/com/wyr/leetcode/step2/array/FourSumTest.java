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
        //三数之和套一层
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) {
            return res;
        }
        // 排序，然后双指针的做法
        Arrays.sort(nums);

        int a = 0;
        while (a < nums.length - 3) { // 固定a
            //处理 a 重复
            if (a > 0 && nums[a - 1] == nums[a]) {
                a++;
                continue;
            }
            // 改进1
            if ((long)nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                break;
            }

            int b = a + 1;
            while (b < nums.length - 2) {// 固定b
                //处理b重复
                if (b > a + 1 && nums[b - 1] == nums[b]) {
                    b++;
                    continue;
                }
                // 改进1
                if ((long)nums[a]+ nums[b] + nums[b + 1] + nums[b + 2] > target) {
                    break;
                }
                int c = b + 1;
                int d = nums.length - 1;
                while (c < d) { // c,d不相遇
                    // 改进2:
                    if ((long)nums[a]+nums[b]+nums[c]+nums[c + 1] > target) {
                        break;
                    }
                    if ((long)nums[a]+nums[b]+nums[c] + nums[d] == target) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[a]);
                        list.add(nums[b]);
                        list.add(nums[c]);
                        list.add(nums[d]);
                        res.add(list);
                        while (c + 1 < d - 1 && nums[c + 1] == nums[c] && nums[d - 1] == nums[d]) {
                            c++;
                            d--;
                        }
                        c++;
                        d--;
                    } else if ((long)nums[a]+nums[b]+nums[c] + nums[d] < target) {
                        c++;
                    } else {
                        d--;
                    }
                }
                b++;
            }
            a++;// a别忘了自增
        }
        return res;
    }
}
