package com.wyr.zuoshen.zuoshen1.p8;

import java.util.ArrayList;
import java.util.List;

public class SubSets {
    /**
     * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * https://leetcode.cn/problems/TVdhkn/
     */

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        process(0, result, nums, new ArrayList<Integer>());
        return result;
    }

    public static void process(int index, List<List<Integer>> result, int[] nums, List<Integer> list) {
        if (index == nums.length) { //baseCase
            result.add(list);//将当前结果保存到最终结果的list
            return;
        }
        List<Integer> cpList1 = copyList(list);//拷贝一份当前的结果
        //要当前的数
        cpList1.add(nums[index]);
        process(index + 1, result, nums, cpList1);
        List<Integer> cpList2 = copyList(list);//拷贝一份当前的结果
        //不要当前的数
        process(index + 1, result, nums, cpList2);
    }


    //拷贝一份list
    public static List<Integer> copyList(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a={1,2};
        subsets(a);
    }
}
