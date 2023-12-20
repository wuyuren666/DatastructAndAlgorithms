package com.wyr.dp.zuoshen.p15_16;

import java.util.*;

public class Code1 {





    /**
     * 背包问题
     *
     * @param w 货物重量的数组，数组元素约定>=0
     * @param v 货物价值的数组，数组元素预定>=0
     * @param bag 背包容量，不能超过这个容量
     * @return 最大价值，即我背包能装下的最大价值
     */




    public static int maxValue1(int [] w, int [] v, int bag){
        if(w==null||v==null||w.length!=v.length||w.length==0)
            return 0;
        //limit=4:只能放四个物品 ；count=0：刚开始一个没放肯定是0
        return process11_18(w,v,0,bag,4,0);
    }

    //暴力递归
    public static int process11_18(int [] w, int [] v, int index, int rest, int limit, int count){
        //base Case
        if(index==w.length){ //没有货物可以选择
            return 0;
        }
        //当前位置选择不装，count不需要变
        int p1= process11_18(w,v,index+1,rest,limit,count);
        //当前位置选择装
        int p2=0;
        if(count+1<=limit&&rest-w[index]>=0){ //物品不超限，且背包中能装下当前物品，才进这个if
            p2= v[index]+process11_18(w,v,index+1,rest-w[index],limit,count+1);
        }
        return Math.max(p1,p2);
    }



    //优化1：傻缓存法
    //index:0~N 指的是下标
    //rest:0~bag
    public static int maxValue2(int [] w, int [] v, int bag){
        if(w==null||v==null||w.length!=v.length||w.length==0)
            return 0;
        int [][] dp=new int[w.length+1][bag+1];//dp[i][j]代表：s(w,v,i,j)
        for(int i=0;i<w.length+1;i++){
            for(int j=0;j<bag+1;j++){
                dp[i][j]=-1;
            }
        }
        return shaHuanCun(w,v,0,bag,dp);
    }
    public static int shaHuanCun(int [] w, int [] v, int index, int rest, int[][] store){
        if(store[index][rest]!=-1){
            return store[index][rest];
        }
        int res;
        if(index==w.length){ //没有货物可以选择
            res=0;
        }else{
            //当前位置选择不装
            int p1= shaHuanCun(w,v,index+1,rest,store);
            //当前位置选择装
            int p2=0;
            if(rest-w[index]>=0){
                p2= v[index]+shaHuanCun(w,v,index+1,rest-w[index],store);
            }
            res=Math.max(p1,p2);
        }
        store[index][rest]=res;//加缓存
        return res;
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
        int [] weights={2,3,1,10,5};
        int [] values={4,2,6,3,0};
        int bag=10;
        System.out.println(maxValue1(weights, values, bag));
        //System.out.println(maxValue2(weights, values, bag));
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


