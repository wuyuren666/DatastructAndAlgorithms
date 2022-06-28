package com.wyr.bytedance.date5_6;

import java.util.Scanner;

/**
 * 有效集合
 * 一个数字集合A中包含很多数字，数字集合A中
 * 如果有任何一个数字是另外一个数字的前缀，那么数字集合A就是有效集合
 * 否则就是无效集合
 *
 * 输入的第一行代表有多少组数字集合
 * 输入的第二行代表第一组数字集合中有多少数字
 * 剩下的是数字
 * 重复刚才的
 */
public class ValidCollectTest {


    public static void main(String[] args) {
            validCollect();
    }


    public static void validCollect(){
        Scanner sc=new Scanner(System.in);
        int countZu=sc.nextInt();//获取组数
        String[] result=new String[countZu];
        sc.nextLine();//消除换行符
        for (int i = 0; i < countZu; i++) { //组
            boolean flag=false;
            int num=Integer.parseInt(sc.nextLine());
            String [] s=new String[num];
            for(int j=0;j<num;j++){
                s[j]=sc.nextLine();
            }
            for(int k=0;k<num;k++){
                for(int l=0;l<num;l++){
                    if(k!=l){
                       if( s[k].length()<=s[l].length()&& s[l].substring(0,s[k].length()).equals(s[k])){
                                    flag=true;
                       }
                    }
                }
            }
            if(flag){
               result[i]="Yes";
            }else {
                result[i]="No";
            }
        }
        sc.close();
        for (String s : result) {
            System.out.println(s);
        }
    }


}
