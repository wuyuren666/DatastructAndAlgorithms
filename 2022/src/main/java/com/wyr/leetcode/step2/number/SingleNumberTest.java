package com.wyr.leetcode.step2.number;

public class SingleNumberTest {
    /**
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     *
     * 输入：nums = [3,4,3,3]
     * 输出：4
     *
     * 输入：nums = [9,1,7,9,7,9,7]
     * 输出：1
     *
     * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
     */

    public int singleNumber(int[] nums) {
        int [] bitInfo=new int[32];
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<32;j++){
                bitInfo[j]+=((1<<j)&nums[i])==0?0:1;
            }
        }
        int res=0;
        for(int i=0;i<32;i++){
            if(bitInfo[i]%3!=0){
                res+=Math.pow(2,i);
            }
        }
        return res;

    }
}
