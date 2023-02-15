package com.wyr.leetcode.step2.number;


import java.util.Arrays;


public class DeleteKNumTest {

    /**
     * 给一个数字，要求我们求出删去k个数之后的最小值
     *
     * 比如：
     * 3549  删去1个数之后的最小值为：349
     *
     * 贪心-->局部最优到全局最优
     * 从左往右，每一次删除一个，遇到第一个左边大于右边的数就删除
     *
     * 思想：将数字从左往右遍历，发现第一个大于下一个数的数字就将它删除，就得到删除1个数字后得到的最小值
     * 假如，我们有8为数，无论删掉哪一位都是7位数，我们能做的就是尽量使高位的数字降低，这样对新整数的值的影响最大
     */

    //贪心策略，我就每次删除一个数，每次得到局部最优，最后变为全局最优
    public static int [] getMInNum(int [] nums, int k){
        for(int i=0;i<k;i++){
           nums= process(nums);
        }
        return nums ;
    }

    public static int[] process(int [] nums){
        int length=nums.length;
        int [] result=new int[length-1];
        int index=-1;//记录需要删除的下标
        for(int i=0;i<length-1;i++){
            if(nums[i]>nums[i+1]){
                index=i;
                break;
            }
        }
        index=index==-1?length-1:index;

        if(index==length-1){ //需要删除最后一个数字
            return Arrays.copyOf(nums,length-1);
        }

        int i=0;
        for(;i<length;i++){
            if(index==i){
                result[i]=nums[++i];
                break;
            }
            result[i]=nums[i];
        }
        for(;i<length-1;i++){
            result[i]=nums[i+1];
        }
        return result;
    }


    public static void main(String[] args) {

    }
}
