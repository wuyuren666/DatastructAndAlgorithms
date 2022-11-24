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

    static List<List<Integer>> originList=new ArrayList<>();
    public static int combinationSum4(int[] nums, int target) {
        process(nums,0,target,new LinkedList<Integer>());
        int res=0;
        //接下来就是看全排列的问题
        for(List<Integer> list : originList){
            Set<List<Integer>> helpSet = new HashSet<>();
            Collections.sort(list,(o1,o2)->o1-o2);
            boolean []used=new boolean[list.size()];
            process2(list.toArray(new Integer[0]),used,helpSet,new ArrayList<>());
            res+=helpSet.size();
        }
        return res;
    }

    public static void process2(Integer[] nums, boolean [] used, Set<List<Integer>> set, List<Integer> tempList){
        if(tempList.size()==nums.length){
            set.add(new ArrayList<>(tempList));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(!used[i]){
                used[i]=true;
                tempList.add(nums[i]);
                process2(nums,used,set,tempList);
                tempList.remove(tempList.size()-1);
                used[i]=false;
            }
        }
    }

    public static void process(int [] nums, int curIndex, int rest, LinkedList<Integer> tempList){
        if(curIndex==nums.length){
            if(rest==0){
                originList.add(new ArrayList<>(tempList));
            }
            return;
        }

        for(int i=0;i<=rest/nums[curIndex];i++){
            for(int j=0;j<i;j++){
                tempList.add(nums[curIndex]);
            }

            process(nums,curIndex+1,rest-i*nums[curIndex],tempList);

            for(int j=0;j<i;j++){
                tempList.removeLast();
            }
        }
    }


    public static void main(String[] args) {
        int[] nums={5,1,8,2};
        System.out.println(combinationSum4(nums,10));
    }

}
