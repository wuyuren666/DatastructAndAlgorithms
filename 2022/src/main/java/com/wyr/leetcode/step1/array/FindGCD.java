package com.wyr.leetcode.step1.array;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，返回数组中最大数和最小数的 最大公约数 。
 *
 * 两个数的 最大公约数 是能够被两个数整除的最大正整数。
 *
 * https://leetcode.cn/problems/find-greatest-common-divisor-of-array/submissions/
 */
public class FindGCD {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        return gcd1(nums[0],nums[nums.length-1]);
    }

    public int gcd1(int a, int b){
        if(a==b){
            return a;
        }
        if((a&1)==0 && (b&1)==0){ //两个数都是偶数
            return gcd1(a>>1,b>>1)<<1;
        }else if((a&1)==0 && (a&1)!=0){//a是偶数，b是奇数
            return gcd1(a>>1,b);
        }else if((a&1)!=0 && (b&1)==0){//a是奇数，b是偶数
            return gcd1(a,b>>1);
        }else{//a，b都是奇数，使用更相减损术
            int big=a>b?a:b;
            int small=a<b?a:b;
            return gcd1(big-small,small);
        }
    }

    public int gcd2(int a, int b){
        int big=a>b?a:b;
        int small=a<b?a:b;
        if(big%small==0){
            return small;
        }
        return gcd2(big%small,small);
    }
}
