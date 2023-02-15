package com.wyr.bigFactory.date8_13;

import java.util.Arrays;
import java.util.Scanner;

public class WaiMaiTest {

    /**
     * 有一名会魔法的外卖派送员
     * 该外卖派送员派送单子时，可以消耗时间t来正常派送单子（一次只能派送一个单子，不能多个同时派送）
     * 也可以使用魔法不耗费时间地隔地瞬间投送
     * 现在店里在0时刻接收到了若干订单，每一个订单都有他的截止送达时间。外卖派送员需要保证送达时间
     * 小于等于这个截止时间。
     * 现在询问外卖员最少需要使用几次魔法来保证没有外卖超时
     *
     * 输入描述：
     *    第一行两个正整数n，t 以空格分隔开表示当前接收到n个订单，外卖员在不使用魔法的情况下正常派送所需要耗费的时间
     *    第二行n个正整数，每一个正整数表示一个订单的截止送达时间
     * 输出描述
     *    外卖员最少用几次魔法
     *
     *
     * 样例输入：
     *    6 5
     *    5 6 7 8 9 10
     * 样例输出：
     *    4
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int m= sc.nextInt();
        int [] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        //贪心就是涉及到排序
        //按截止时间从小到大进行排序，紧着送截止时间近的
        Arrays.sort(arr);
        int res=0;
        int curTime=0;
        for(int i=0;i<n;i++){
            if(curTime+m>arr[i]){//正常派送，送不了
                //使用魔法，当前时间不变
                res++;
            }else{//正常派送，可以送
                curTime+=m;
            }
        }
        System.out.println(res);
        sc.close();
    }
}
