package com.wyr.leetcode.step1.array;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 * 输入：digits = [0]
 * 输出：[1]
 */
public class PlusOneTest {

    public static int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]==9){ //从最高位往低位走，如果是9先变成0
                digits[i]=0;
            }else{
                //不是9的话，就加1,返回
                digits[i]+=1;
                return digits;
            }
        }
        //进入到这，说明全都是9
        int N=digits.length;
        int [] result=new int[N+1];
        result[0]=1;
        return result;
    }
}
