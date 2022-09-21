package com.wyr.leetcode.step1.number;

public class AddTest {
    /**
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     *
     * 输入: a = 1, b = 1
     * 输出: 2
     *
     *
     * https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
     */

    public int add(int a, int b) {
        //两个数异或的结果其实就是无进位相加
        //两个数相与<<1就是进位的信息
        //a+b的结果==info1+info2的结果，最终只要info2==0，也就是进位信息位0，那么info1就是最终的结果
        int info1=a^b;
        int info2=(a&b)<<1;
        while(info2!=0){//当进位信息为0结束循环
            int temp=info2;
            info2=(info1&info2)<<1;
            info1=temp^info1;
        }
        return info1;
    }

    public static void test(){
        try {
            int a=10/0;
            System.out.println("aaaa");
        }catch (Exception e){
            throw new RuntimeException();
        }finally {
            System.out.println("finally");
        }
        System.out.println("bbb");
    }

    public static void main(String[] args) {
        test();
    }


}
