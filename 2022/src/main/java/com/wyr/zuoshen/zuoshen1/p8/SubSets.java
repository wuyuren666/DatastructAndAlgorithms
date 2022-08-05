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
        List<List<Integer>> result=new ArrayList<>();
        process(result,new ArrayList<Integer>(),nums,0);
        return result;
    }

    public static void process(List<List<Integer>> result,List<Integer> tempList,int [] nums, int index){
        if(index==nums.length){
            result.add(tempList);
            return;
        }
        //复制一份
        List<Integer> list1=copyList(tempList);
        list1.add(nums[index]);
        //加入当前数据
        process(result,list1,nums,index+1);
        //不加入当前数据
        process(result,tempList,nums,index+1);
    }

    public static List<Integer> copyList(List<Integer> list){
        List<Integer> res=new ArrayList<>(list.size());
        for(int i=0;i<list.size();i++){
            res.add(list.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a={1,2,3};
        List<List<Integer>> subsets = subsets(a);
        subsets.forEach(integers -> {integers.forEach(System.out::print);
            System.out.println();
        });
    }
}
