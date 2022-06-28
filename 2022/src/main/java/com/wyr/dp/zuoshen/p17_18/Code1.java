package com.wyr.dp.zuoshen.p17_18;

/**
 * 对p15_16的优化
 */
public class Code1 {

    /**
     * @param w 货物重量的数组，数组元素约定>=0
     * @param v 货物价值的数组，数组元素预定>=0
     * @param bag 背包容量，不能超过这个容量
     * @return 最大价值，即我背包能装下的最大价值
     */
    public static int maxValue2(int [] w, int [] v, int bag){
        if(w==null||v==null||w.length!=v.length||w.length==0)
            return 0;
        int N=w.length;
        //index:0~N 指的是下标
        //rest:0~bag
        int [][] dp=new int[N+1][bag+1];
        for(int index=N-1;index>=0;index--){
            for (int rest=0;rest<=bag;rest++){
                //不选择当前货物
                int p1= dp[index+1][rest];
                //选择当前货物
                int p2=0;
                if(rest-w[index]>=0){
                    p2=v[index]+dp[index+1][rest-w[index]];
                }
                dp[index][rest]=Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int [] weights={2,3,1,10,5};
        int [] values={4,2,6,3,0};

        int bag=7;
        System.out.println(maxValue2(weights, values, bag));
    }
}
