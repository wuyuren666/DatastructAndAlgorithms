package com.wyr.leetcode.step1.array;

import java.util.HashMap;
import java.util.Map;

public class ContainsNearbyDuplicateTest {
    /**
     * 给你一个整数数组nums 和一个整数k ，判断数组中是否存在两个 不同的索引i和j ，
     * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     *
     * 输入：nums = [1,2,3,1], k = 3
     * 输出：true
     *
     * 输入：nums = [1,0,1,1], k = 1
     * 输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/contains-duplicate-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //key代表数组中的数，value代表其下标
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){//数之前存在过
                if(Math.abs(map.get(nums[i])-i)<=k){ //又满足条件 abs(i - j) <= k
                    return true;
                }
            }
            //[1,0,1,1] k:1
            //没存在过，就加入；如果存在过，但是没满足 abs(i - j) <= k，也需要更新为最大的下标
            map.put(nums[i],i);
        }
        return false;
    }

}
