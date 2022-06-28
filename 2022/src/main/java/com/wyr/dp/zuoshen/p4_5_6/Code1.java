package com.wyr.dp.zuoshen.p4_5_6;

/**
 * 我们在p3的基础上，可以再次进行优化，当我们将dp这个二维数组画出来时
 * 我们可以得到规律
 * 1：最上面的一行依赖于左下角的值
 * 2：最下面的一行依赖于左上角的值
 * 3：中间的值，依赖于左上角+左下角的值
 */
public class Code1 {
    public static int way3(int N, int aim,int cur,int K){
        if(N<2||cur<1||cur>N||aim<1||aim>N||K<1){ //检查参数
            return -1;
        }
        int [][] dp=new int[N+1][K+1];
        dp[aim][0]=1;
        for(int i=1;i<=K;i++){//i代表列
            dp[1][i]=dp[2][i-1];//第一行的赋值
            for(int j=2;j<=N-1;j++){//j代表行
                dp[j][i]=dp[j-1][i-1]+dp[j+1][i-1];
            }
            dp[N][i]=dp[N-1][i-1];//最后一行的赋值
        }
        return dp[cur][K];
    }

    public static void main(String[] args) {
        System.out.println(way3(6, 5, 3, 8));
    }
}
