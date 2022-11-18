package com.wyr.dp.zuoshen.p15_16;

import java.util.*;

public class Code1 {
    /**
     *
     * @param w 货物重量的数组，数组元素约定>=0
     * @param v 货物价值的数组，数组元素预定>=0
     * @param bag 背包容量，不能超过这个容量
     * @return 最大价值，即我背包能装下的最大价值
     */

    public static int maxValue1(int [] w, int [] v, int bag){
        if(w==null||v==null||w.length!=v.length||w.length==0)
            return 0;
        return process1(w,v,0,bag);
    }



    public static int process11_18(int [] w, int [] v, int index, int rest){
        if(index==w.length){ //没有货物可以选择
            return 0;
        }
        //当前位置选择不装
        int p1= process11_18(w,v,index+1,rest);
        //当前位置选择装
        int p2=0;
        if(rest-w[index]>=0){
            p2= v[index]+process11_18(w,v,index+1,rest-w[index]);
        }
        return Math.max(p1,p2);
    }



    //当前考虑到了index号货物，从index货物出发可以自由选择，所做的选择不能超过背包容量，返回最大价值
    //rest代表背包剩余容量
    public static int process1(int [] w, int [] v,int index, int rest){
         if(rest<0) //背包剩余容量小于0
             return -1; //看作有效标志，如果下一次返回-1，代表无效操作 ,比如w=[10],v=[5],rest=9，执行到下面p2的时候，会将v[index]加上，显然我们应该不能加
         if(index==w.length) //没有货物可以选
             return 0;

         //不选择当前货物
         int p1= process1(w,v,index+1,rest);

         //选择当前货物
         int p2=0;
         if(process1(w,v,index+1,rest-w[index])!=-1){
             p2=v[index]+process1(w,v,index+1,rest-w[index]);
         }

         return Math.max(p1,p2);
    }


    public static void main(String[] args) {
        int [] weights={8,11,14,5,9,10};
        int [] values={20,15,40,10,25,30};
        int bag=30;
        System.out.println(maxValue1(weights, values, bag));
        /*Map<String,Integer> map = new HashMap<>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for(Map.Entry<String, Integer> entry: entries){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }*/

        //System.out.println("abac10".matches("(ab)*c+[^09]]"));


    }

}


