package com.wyr.zuoshen.zuoshen2.p2;

/**
 * b站链接：https://www.bilibili.com/video/BV1y34y1v78b?p=2
 *
 * 打印出一个数的二进制信息(假设数字为32位的int类型，并且忽略符号位)
 */
public class PrintBinary {

    public static void print(int N){
        for(int i=31;i>=0;i--){
            System.out.print(((1<<i)&N)==0?0:1);
        }
    }


    public static void main(String[] args) {
        //java中没有无符号数，C或者C++中有无符号数
        //所以有符号数的int类型占32位，其中的最高位拿来做符号为，所以最多能表示到21亿多
        //有符号位的32整型的表达范围：-2^31~2^31-1
        //因为将0归属在非负区，所以正数表示的范围要比负数表示的范围少一个
        // print(-1); //计算机中的负数，使用负数的补码进行表示


        print(Integer.MIN_VALUE);
        System.out.println();
        print(Integer.MIN_VALUE>>1); //算术移位，最高位拿符号位来补
        System.out.println();
        print(Integer.MIN_VALUE>>>1);//逻辑移位，最高位拿0来补
        System.out.println();


        //有意思的想象，非负数取相反数肯定都能得到
        //但是有符号数的系统最小取相反数确是他自己
        System.out.println(Integer.MIN_VALUE);
        System.out.println(-Integer.MIN_VALUE);
    }
}
