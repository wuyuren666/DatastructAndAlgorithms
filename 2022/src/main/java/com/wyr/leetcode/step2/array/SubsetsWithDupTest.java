package com.wyr.leetcode.step2.array;

import java.util.*;

public class SubsetsWithDupTest {
    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     *
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/subsets-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> ans=new ArrayList<>();
    Set<List<Integer>> set=new HashSet<>(); //保存的list，如果元素值相同且顺序相同，可以认为是同一个list
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);//先排序
        process(nums,new ArrayList<>(),0);
        ans=new ArrayList<>(set);
        return ans;
    }

    public void process(int [] nums, List<Integer> tempList, int index){
        if(index==nums.length){
            set.add(new ArrayList<>(tempList));
            return;
        }
        //当前节点要
        tempList.add(nums[index]);
        process(nums,tempList,index+1);
        tempList.remove(tempList.size()-1);
        process(nums,tempList,index+1);
    }


    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        Arrays.sort(nums);//先排序
        dfs(0,nums,new ArrayList<>());
        return ans;
    }

    public void dfs(int index , int[] nums , List<Integer> tempList){
        //前序位置
        ans.add(new ArrayList<>(tempList));
        for(int i=index; i<nums.length; i++){
            if(i>index&&nums[i]==nums[i-1]){
                continue;
            }
            tempList.add(nums[i]);
            dfs(i+1,nums,tempList);
            tempList.remove(tempList.size()-1);
        }
    }
}
