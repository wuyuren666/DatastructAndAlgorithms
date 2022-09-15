package com.wyr.leetcode.step2.huisu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum4Test {

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
