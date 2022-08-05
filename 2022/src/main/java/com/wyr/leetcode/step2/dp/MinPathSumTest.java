package com.wyr.leetcode.step2.dp;

/**
 * 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * https://leetcode.cn/problems/minimum-path-sum/
 */
public class MinPathSumTest {

    //暴力递归版本
    public static int minPathSum1(int[][] grid) {
        int m=grid.length; //获取行的长度
        int n=grid[0].length; //获取列的长度
        return process(grid,0,0,m-1,n-1);
    }

    public static int process81(int[][] grid, int curX, int curY, int aimX, int aimY){
        if(curX==aimX&&curY==aimY){
            return grid[curX][curY];
        }
        //越界
        if(curX<0||curX>=grid.length||curY<0||curY>=grid[0].length){
            return Integer.MAX_VALUE;
        }
        int res=grid[curX][curY];
        if(curX==grid.length-1){ //只能向右走
            return res+process(grid,curX,curY+1,aimX,aimY);
        }

        if(curY==grid[0].length-1){//只能向下走
            return res+process(grid,curX+1,curY,aimX,aimY);
        }
        int p1=res+process(grid,curX+1,curY,aimX,aimY);
        int p2=res+process(grid,curX,curY+1,aimX,aimY);
        return Math.min(p1,p2);
    }

    public  static int process(int[][] grid, int curX, int curY, int aimX, int aimY){
        //baseCase
        if(curX==aimX&&curY==aimY){
            return grid[curX][curY];
        }
        //baseCase
        if(curX>aimX||curY>aimY){
            return Integer.MAX_VALUE;
        }

        int curValue=grid[curX][curY];
        if(curX==aimX){//只能向右走
            return curValue+process(grid,curX,curY+1,aimX,aimY);
        }
        if(curY==aimY){//只能向下走
            return curValue+process(grid,curX+1,curY,aimX,aimY);
        }
        //既可以向右走，也可以向下走
        int p1=curValue+process(grid,curX,curY+1,aimX,aimY);
        int p2=curValue+process(grid,curX+1,curY,aimX,aimY);
        return Math.min(p1,p2);
    }

    //动态规划版本
    public static int minPathSum2(int[][] grid) {
        int m=grid.length; //获取行的长度
        int n=grid[0].length; //获取列的长度
        int [][] dp=new int [m][n];
        dp[m-1][n-1]=grid[m-1][n-1];

        //先将两边填起来，最下边一行和最右边一行
        for(int j=n-2;j>=0;j--){
            dp[m-1][j]=grid[m-1][j]+dp[m-1][j+1];
        }
        for(int i=m-2;i>=0;i--){
            dp[i][n-1]=grid[i][n-1]+dp[i+1][n-1];
        }
        //填其他的位置
        for(int j=n-2;j>=0;j--){
            for(int i=m-2;i>=0;i--){
                int p1=grid[i][j]+dp[i+1][j];
                int p2=grid[i][j]+dp[i][j+1];
                dp[i][j]=Math.min(p1,p2);
            }
        }
        return dp[0][0];
    }

}
