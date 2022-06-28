package com.wyr.leetcode.step1.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 。如果任一值在数组中出现至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 * 输入：nums = [1,2,3,1]
 * 输出：true
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 *
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 */
public class ContainsDuplicateTest {

    public static boolean containsDuplicate(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                //哈希表中没有这个数的key
                map.put(nums[i],1);
            }else{
                //哈希表中有这个数的key，重复！
                return true;
            }
        }
        return false;
    }
}
