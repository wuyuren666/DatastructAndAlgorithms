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
     * https://leetcode.cn/problems/subsets/
     */
    static List<List<Integer>> result=new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        process(new ArrayList<Integer>(),nums,0);
        return result;
    }

    public static void process(List<Integer> tempList,int [] nums, int index){
        if(index==nums.length){
            result.add(tempList);
            return;
        }
        //复制一份
        List<Integer> list1=new ArrayList<>(tempList);
        list1.add(nums[index]);
        //加入当前数据
        process(list1,nums,index+1);
        //不加入当前数据
        process(tempList,nums,index+1);
    }

    //也可以这样写，不复制，直接先加入后删除
    public void process(int [] nums,int index,List<Integer> tempList){
        if(index==nums.length){
            result.add(new ArrayList<>(tempList));
            return;
        }
        tempList.add(nums[index]);
        //要当前字符
        process(nums,index+1,tempList);
        //删除当前字符
        tempList.remove(tempList.size()-1);
        //不要当前字符
        process(nums,index+1,tempList);
    }



    public static void main(String[] args) {
        int[] a={1,2,3};
        List<List<Integer>> subsets = subsets(a);
        subsets.stream().forEach(System.out::println);
    }
}
