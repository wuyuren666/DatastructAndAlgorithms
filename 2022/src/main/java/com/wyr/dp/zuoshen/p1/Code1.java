package com.wyr.dp.zuoshen.p1;

import java.util.HashSet;

/**
 * b站链接：https://www.bilibili.com/video/BV1ET4y1U7T6?p=1
 *
 * 斐波那契数列
 * 1,1,2,3,5,8,13...
 *
 * 假设我们需要找第5个数，即f(5)
 * 此时我们需要f(4)+f(3)
 * 左边
 * f(4)-->f(3)+f(2)-->(f(1)+f(2))+f(2)
 * 右边
 * f(3)-->f(1)+f(2)
 *
 * 我们发现，左边做完后，就知道了f(3)；
 * 但是右边还需要进行展开，其实我们右边根本就不需要在进行展开了
 * 在左边算的过程中，f(3)已经被我们算出来了
 */

public class Code1 {

    //我要求斐波那契数列中的第N个数
    public static int f1(int N){
        if(N==1)
            return 1;
        if(N==2)
            return 1;
        return f1(N-2)+f1(N-1);
    }


    public static int way1(int N){
        return f1(N);
    }




    public static int way2(int N){
        int [] dp=new int[N+1];
        for (int i=0;i<=N;i++){
            dp[i]=-1;
        }
        return f2(N,dp);
    }


    private static int f2(int N, int[] dp) {
        if(dp[N]!=-1){
            return dp[N];
        }
        int result;

        if(N==1||N==2){
            result= 1;
        }else {
            result=f2(N-2,dp)+f2(N-1,dp);
        }
        dp[N]=result;
        return result;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(way1(50));
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);


        long l2 = System.currentTimeMillis();
        System.out.println(way2(50));
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
    }
}
