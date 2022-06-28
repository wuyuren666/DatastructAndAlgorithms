package com.wyr.dp.zuoshen.p15_16;

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
        int [] weights={3,2,4,7,3,1,7};
        int [] values={5,6,3,19,12,4,2};
        int bag=15;
        System.out.println(maxValue1(weights, values, bag));
    }
}
