package com.wyr.leetcode.step2.array;


/**
 * 给定一个由0 和 1 组成的非空二维数组grid，用来表示海洋岛屿地图。
 *
 * 一个岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
 * 你可以假设grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ZL6zAn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxAreaOfIslandTest {
    public int maxAreaOfIsland(int[][] grid) {
        int M=grid.length;
        int N=grid[0].length;
        int res=Integer.MIN_VALUE;
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(grid[i][j]==1){
                    res=Math.max(process(grid,i,j),res);
                }
            }
        }
        if(res==Integer.MIN_VALUE){
            return 0;
        }
        return res;
    }


    //感染过程
    public int process(int[][] grid,int X, int Y){
        if( X<0 || X>grid.length-1 || Y<0|| Y> grid[0].length-1 || grid[X][Y]!=1){
            return 0;
        }
        //先感染自己
        grid[X][Y]=2;
        int res=1;
        res+=process(grid,X-1,Y);//向上
        res+=process(grid,X+1,Y);//向下
        res+=process(grid,X,Y-1);//向左
        res+=process(grid,X,Y+1);//向右
        return res;
    }
}
