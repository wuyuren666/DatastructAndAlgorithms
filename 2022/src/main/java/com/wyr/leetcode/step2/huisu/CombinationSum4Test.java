package com.wyr.leetcode.step2.huisu;

import java.util.*;

public class CombinationSum4Test {

    /**
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
     * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 题目数据保证答案符合 32 位整数范围。
     *
     *输入：nums = [1,2,3], target = 4
     * 输出：7
     * 解释：
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * 请注意，顺序不同的序列被视作不同的组合。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    static List<List<Integer>> res=new ArrayList<>();
    static Set<List<Integer>> helpSet=new HashSet<>();
    public static int combinationSum4(int[] nums, int target) {
        process1(nums,target,0,new ArrayList<>());
        int ans=0;
        for(List<Integer> list: res){
            Set<List<Integer>> helpSet=new HashSet<>();
            int[] used=new int[list.size()];
            process2(list.toArray(new Integer[0]),used,new ArrayList<Integer>(),helpSet);
            ans+=helpSet.size();
        }
        return ans;
    }

    public static void process1(int[] nums, int rest, int index, List<Integer> tempList){
        //没有数可以选了
        if(index==nums.length){
            if(rest==0){
                res.add(new ArrayList<>(tempList));
            }
            return;
        }
        for(int i=0;i<=rest/nums[index];i++){
            for(int j=0;j<i;j++){
                tempList.add(nums[index]);
            }
            process1(nums,rest-nums[index]*i,index+1,tempList);
            for(int j=0;j<i;j++){
                tempList.remove(tempList.size()-1);
            }
        }
    }

    public static void process2(Integer [] nums,int [] used, List<Integer> tempList, Set<List<Integer>> helpSet) {
        if (tempList.size() == nums.length) {
            helpSet.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //没有被使用过
            if (used[i] == 0) {
                used[i] = 1;
                tempList.add(nums[i]);
                process2(nums, used, tempList, helpSet);
                tempList.remove(tempList.size() - 1);
                used[i] = 0;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums={1,2,3};
        combinationSum4(nums,4);
    }

}
