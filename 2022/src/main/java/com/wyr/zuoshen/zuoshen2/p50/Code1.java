package com.wyr.zuoshen.zuoshen2.p50;

public class Code1 {
    /**
     * 一个数组中只有两种字符‘G’和‘B’，想让所有的G都放在左侧，所有的B都放在右侧，
     * 但是只能在相邻的字符之间进行交换的操作，最少要交换多少次
     */
    public static int minStep(String s){
        int strLen=s.length();
        int p1=0,p2=0;//双指针
        int minRes=0;
        //p2如果指向g，就累加结果，p1，p2同时向后移
        //p2如果不指向g，p2向后移动，p1不动
        while(p2<=strLen-1){
           if(s.charAt(p2)=='G'){ //p2指向的是G
               minRes+=p2++-p1++;
           }else{
               p2++;
           }
        }
        return minRes;
    }

    public static void main(String[] args) {
        System.out.println(minStep("GGGGBBBGG"));
    }
}
