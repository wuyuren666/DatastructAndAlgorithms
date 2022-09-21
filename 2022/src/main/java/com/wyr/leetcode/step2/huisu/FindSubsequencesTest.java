package com.wyr.leetcode.step2.huisu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindSubsequencesTest {
    /**
     * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。
     * 你可以按 任意顺序 返回答案。
     *
     * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
     *
     * 输入：nums = [4,6,7,7]
     * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/increasing-subsequences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    Set<List<Integer>> set=new HashSet<>();//去重
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans;
        process(nums,new ArrayList<>(),0);
        ans=new ArrayList<>(set);
        return ans;
    }

    public void process(int [] nums, List<Integer> tempList, int index){
        if(index==nums.length){
            if(tempList.size()>=2){
                set.add(new ArrayList<>(tempList));
            }
            return;
        }
        //当前数字选择加入，tempList为空可以加入或者前一个数字小等于我小，也可以加入
        if(tempList.size()==0||tempList.get(tempList.size()-1)<=nums[index]){
            tempList.add(nums[index]);
            process(nums,tempList,index+1);
            tempList.remove(tempList.size()-1);
        }
        //当前数字不选择加入
        process(nums,tempList,index+1);
    }
}
