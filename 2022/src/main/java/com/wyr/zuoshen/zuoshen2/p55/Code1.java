package com.wyr.zuoshen.zuoshen2.p55;

import java.util.Arrays;

public class Code1 {
    /**
     * 给定一个数组arr，代表每一个人的能力值，再给定一个非负数k
     * 如果两个人能力值差值正好为k，那么可以凑在一起比赛
     * 一局比赛只有两个人
     * 返回最多可以同时有多少场比赛
     */
    public static int maxPairNum(int [] arr,int k){
        if(k<0||arr==null||arr.length<2){
            return 0;
        }
        Arrays.sort(arr);//先排序 ,贪心的体现，因为我们是让能力值较小的人，先完成匹配
        int ans=0;//最终返回的结果
        int N=arr.length;
        int L=0,R=0;
        boolean [] usedR=new boolean[N];

        while(L<N&&R<N){
            if(usedR[L]){ //当前L来到的位置被用过
                L++;
            }else if(L>=R){ //因为只有两个人才能进行比赛，所以这样的窗口的情况是不可以进行比赛的
                R++;
            }else {
                int distance=arr[R]-arr[L];
                if(distance==k){ //L，R同时移动，并且标注没移动前的R位置已经被用过了
                    ans++; //答案加1
                    usedR[R]=true;
                    R++;
                    L++;
                }else if(distance<k){
                    R++;
                }else{
                    L++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] arr={3,1,5,7};
        System.out.println(maxPairNum(arr,2));
    }
}
