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
        int[] n=new int[32]; //0代表低位，这个n数组描述的是每一位上的状态，n[0]=3说明nums数组中有3个数的最低位是1
        int sum=0;
        for(int i=0;i<nums.length;i++){
            int curNum=nums[i];
            for(int j=0;j<32;j++){
                if(((1<<j)&curNum)!=0){ //说明这位是1，然后就累加
                    n[j]++;
                }
            }
        }
        int ans=0;
        for(int i=0;i<32;i++){
            int bit=n[i]%3;
            if(bit==1){
                ans+=Math.pow(2,i);
            }
        }
        return ans;
    }
}
