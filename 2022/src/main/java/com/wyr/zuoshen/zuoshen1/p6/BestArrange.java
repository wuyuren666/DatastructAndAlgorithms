package com.wyr.zuoshen.zuoshen1.p6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 贪心算法在笔试时的解题套路
 * 1；实现一个不依靠贪心策略的解法X，可以使用最暴力的尝试
 * 2：脑补出贪心策略A，贪心策略B，贪心策略C。。
 * 3：用解法X和对数器，去验证每一个贪心策略。用实验的方式得知哪个贪心策略正确
 * 4：不要去纠结贪心策略的证明
 */
public class BestArrange {
    /**
     * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
     * 给你一个项目的开始时间和结束时间（给你一个数组，里面是一个个具体的项目）
     * 你来安排宣讲的日程，要求会议室进行的宣讲场次最多
     * 返回这个最多的场次
     */
    public static class Program{
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //贪心策略：谁结束的早谁排在前面
   public static int bestArrange(Program[] programs,int timePoint){
        //按照会议的结束时间谁早谁在前即结束时间由小到大排序
       Arrays.sort(programs, new Comparator<Program>() {
           @Override
           public int compare(Program o1, Program o2) {
               return o1.end-o2.end;
           }
       });

       int result=0;
       for(int i=0;i<programs.length;i++){
           //当前时间点小于等于会议的开始时间
           if(timePoint<=programs[i].start){
               result++;
               timePoint=programs[i].end; //开完会之后，当前时间点为会议的结束时间
           }
       }
       return result;
   }
}
