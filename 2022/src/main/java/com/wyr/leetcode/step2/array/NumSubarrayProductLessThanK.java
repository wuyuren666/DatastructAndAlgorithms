package com.wyr.leetcode.step2.array;

public class NumSubarrayProductLessThanK {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
     *
     * 输入：nums = [10,5,2,6], k = 100
     * 输出：8
     * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/subarray-product-less-than-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //4ms
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k==0){
            return 0;
        }
        int L=0;
        int tempMul=1;
        int res=0;
        for(int R=0;R<nums.length;R++){
            tempMul*=nums[R];
            while(L<=R&&tempMul>=k){
                tempMul/=nums[L++];
            }
            //这个比较难想
            res+=R-L+1;
        }
        return res;
    }

    //800ms
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if(k==0){
            return 0;
        }
        int L=0;
        int R=0;
        int tempMul=1;
        int res=0;
        while(R<nums.length){
            tempMul*=nums[R];
            if(tempMul>=k){
                R=++L;
                tempMul=1;
                continue;
            }else{
                res++;
            }
            R++;
            if(R==nums.length){
                R=++L;
                tempMul=1;
            }
        }
        return res;
    }

}
