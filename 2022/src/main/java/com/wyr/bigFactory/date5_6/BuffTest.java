package com.wyr.bigFactory.date5_6;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 小明在玩一款游戏，每次击杀怪物之后都能获得一个增益buff，buff可以持续duration秒。
 * 假设小明在第n秒击杀怪物，那么在时间区间[n,n+duration-1]处于buff状态。{包含n和duration-1，第n秒代表buff开始时间，第n+duration-1秒表示buff结束时间}
 * 特别的，如果buff结束前再次击杀怪物，buff计时将会重置（新的buff会替换掉之前buff）
 * 在新击杀之后，buff将会在duration秒之后结束
 * 问题：求小明可以获得的buff的总时间
 * 输入一共三行
 * 第一行代表，数组timeSeries和durations的长度
 * 第二行代表，在哪个时间节点击杀了buff，是递增的
 * 第三行表示，对应时间节点所击杀的buff的持续时间
 */
public class BuffTest {
        public static void main(String[] args){
            System.out.println(getAllTime());
        }



        public static int getAllTime(){

            System.out.println("aaaaa");
            Scanner sc=new Scanner(System.in);
            int result=0;//代表最终返回的总持续时间
            int count=sc.nextInt();//获取第一行的数据，也就是时间递增数组和持续时间数组的长度
            sc.nextLine();//消化第一行的换行
            String s1=sc.nextLine();//获取第二行的数据(不需要消化换行)
            String s2=sc.nextLine();//获取第三行的数据(不需要消化换行)
            sc.close();//关闭
            int [] timeSeries=new int[count];
            int[] durations=new int[count];
            String[] s1_ = s1.split(" ");
            String[] s2_ = s2.split(" ");
            for (int i = 0; i < count; i++) {
                timeSeries[i]=Integer.parseInt(s1_[i]);
                durations[i]=Integer.parseInt(s2_[i]);
            }
            //分段处理，假如两个数组长度为4，那么我就求0～1，1～2，2～3这之间buff的持续时长，最后再加上最后一次获取buff的持续时间
            for (int i=0;i<count-1;i++){
                if(timeSeries[i]+durations[i]<=timeSeries[i+1]){ //当前获取的buff的持续时间<=拿到下一个buff的时间
                    result+=durations[i];
                }else if(timeSeries[i]+durations[i]>timeSeries[i+1]){ //当前获取buff的持续时间>拿到下一个buff的时间
                    result+=timeSeries[i+1]-timeSeries[i];
                }
            }
            //加上最后一次获取的buff的持续时间
            result+=durations[count-1];
            return result;
        }
}
