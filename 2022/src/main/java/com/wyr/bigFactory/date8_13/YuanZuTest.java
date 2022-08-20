package com.wyr.bigFactory.date8_13;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class YuanZuTest {
    /**
     * 给你一个长度为n的序列a[1],a[2],...,a[n],请问有多少个三元组(i,j,k)
     * 满足i<j<K且a[i]-a[j]=2a[j]-a[k]
     *
     * 输入描述：
     *  第一行是一个整数n，表示序列的长度为n
     *  接下来一行n个用空格隔开的整数，a[i]表示序列的第i个数
     *
     * 输出描述：
     *  输出符合要求的三元组的数量
     *
     *
     * 样例输入：
     *    4
     *    4 2 2 2
     * 样例输出
     *    3
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [] arr=new int [n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int count=0;

        //a[i]+a[k]=3a[j]
        //i<j<k
        for(int i=0;i<arr.length-2;i++){
            for(int k=i+2;k<arr.length;k++){
                for(int j=i+1;j<k;j++){
                    if(arr[i]+arr[k]==3*arr[j]){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
        sc.close();
    }
}
