package com.wyr.dp.zuoshen.p3;

/**
 * 对p2的暴力递归的优化
 * 使用缓存，空间换时间
 * 也叫：记忆化搜索，动态规划
 */
public class Code1 {

    //每一次递归的过程中，N和aim是不变的，只有cur，rest在变
    public static int way2(int N,int aim,int cur,int K){
        //cur:1~N
        //rest:0~K
        //这个dp就是我们用于递归的一个缓存表，大小：N+1*rest+1 这个大小肯定能保存所有的递归情况
        int [][] dp=new int[N+1][K+1];

        //dp就是缓存表，初始化dp,
        //dp[cur][rest]==-1  -->  process2（N，aim，cur,rest,dp）这个递归过程没有算过
        //dp[cur][rest]！=-1  -->  process2（N，aim，cur,rest,dp）这个递归过程之前算过，返回值放在了dp[cur][rest]中
        for (int i=0;i<=N;i++)
            for(int j=0;j<=K;j++){
                dp[i][j]=-1;
            }

        return process2(N,aim,cur,K,dp);
    }

    public static int process2(int N,int aim,int cur,int rest,int [][] dp){
        //缓存表中有之前递归的结果
        if(dp[cur][rest]!=-1){
            return dp[cur][rest];
        }
        //之前没有算过
        int result=0;

        if(rest==0){//步数走完了
            result=cur==aim?1:0;
        }else if (cur==1){//假如当前cur在1位置，他只能向右走
            result= process2(N,aim,2,rest-1,dp);
        }else if(cur==N){ //假如当前cur在N位置，他只能向左走
            result= process2(N,aim,N-1,rest-1,dp);
        }else {//假如在中间，他既可以向左走，也可以向右走
            result=process2(N,aim,cur-1,rest-1,dp)+process2(N,aim,cur+1,rest-1,dp);
        }
        dp[cur][rest]=result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(way2(6,3,5,8));
    }
}
