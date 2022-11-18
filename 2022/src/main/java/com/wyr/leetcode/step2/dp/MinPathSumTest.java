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

    public static void main(String[] args) {
        int [][] array=new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum1(array));
    }

    //暴力递归版本
    static int minSum=Integer.MAX_VALUE;
    public static int minPathSum1(int[][] grid) {
        int m=grid.length; //获取行的长度
        int n=grid[0].length; //获取列的长度
        process(grid,0,0,m-1,n-1,0);
        return minSum;
    }

    public static void process(int [][] grid, int curX, int curY,int aimX, int aimY,int tempSum){
        //越界
        if(curX<0||curX>=grid.length||curY<0||curY>=grid[0].length){
            return;
        }
        tempSum+=grid[curX][curY];
        //来到目标值
        if(curX==aimX&&curY==aimY){
            minSum=Math.min(minSum,tempSum);
            return;
        }
        if(curX==aimX){//只能向右
            process(grid,curX,curY+1,aimX,aimY,tempSum);
            return;
        }

        if(curY==aimY){//只能向下
            process(grid,curX+1,curY,aimX,aimY,tempSum);
            return;
        }
        //既可以向下，也可以向右
        process(grid,curX+1,curY,aimX,aimY,tempSum);
        process(grid,curX,curY+1,aimX,aimY,tempSum);
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
