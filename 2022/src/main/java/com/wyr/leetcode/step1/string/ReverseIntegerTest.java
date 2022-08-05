package com.wyr.leetcode.step1.string;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围[-2^31,2^31-1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 输入：x = 123
 * 输出：321
 *
 * 输入：x = -123
 * 输出：-321
 *
 * 输入：x = 120
 * 输出：21
 *
 * https://leetcode.cn/problems/reverse-integer/
 */




public class ReverseIntegerTest {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("00123"));
        System.out.println(reverse(1534236469));
        System.out.println(reverse(10010));
    }

    public static int reverse(int x) {
        if(x==0) //如果为0直接返回0
            return 0;

        int result;
        boolean flag=true; //true代表是正数
        if(x<0){
            x=-x;
            flag=false;
        }
        StringBuilder sb=new StringBuilder();
        while(x!=0){
            sb.append(x%10);
            x=x/10;
        }
        try{
            result=Integer.parseInt(sb.toString()); //这里可能会出现异常，需要捕捉异常
        }catch(Exception e){
            return 0;
        }
        if(!flag){
            return -result;
        }else{
            return result;
        }
    }
}
