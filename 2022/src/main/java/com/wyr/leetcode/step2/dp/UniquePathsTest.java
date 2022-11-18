package com.wyr.leetcode.step2.dp;

/**
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 输入：m = 3, n = 7
 * 输出：28
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniquePathsTest {
    //暴力递归版本
    static int count=0;
    public int uniquePaths1(int m, int n) {
        process(m,n,0,0);
        return count;
    }

    public static void process(int m, int n, int curX, int curY){

        if(curX==m-1&&curY==n-1){
            count++;
            return;
        }
        //只能向右走
        if(curX==m-1){
            process(m,n,curX,curY+1);
            return;
        }
        //只能向下走
        if(curY==n-1){
            process(m,n,curX+1,curY);
            return;
        }
        process(m,n,curX,curY+1);
        process(m,n,curX+1,curY);
    }


    //动态规划版本
    public int uniquePaths2(int m, int n) {
        if(m==1&&n==1){
            return 1;
        }
        int[][] dp=new int[m][n];
        dp[m-1][n-1]=1;
        //先将两边填起来，最下边一行和最右边一行
        for(int j=n-2;j>=0;j--){
            dp[m-1][j]=dp[m-1][j+1];
        }
        for(int i=m-2;i>=0;i--){
            dp[i][n-1]=dp[i+1][n-1];
        }
        //填其他的位置
        for(int j=n-2;j>=0;j--){
            for(int i=m-2;i>=0;i--){
                dp[i][j]=dp[i+1][j]+dp[i][j+1];
            }
        }
        return dp[0][0];
    }
}
