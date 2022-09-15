package com.wyr.leetcode.step2.array;

import java.util.HashSet;
import java.util.Set;

public class NumDistinctIslandsTest {
    /**
     * 题目还是输入一个二维矩阵，0 表示海水，1 表示陆地，这次让你计算 不同的 (distinct) 岛屿数量，
     * 函数签名如下：int numDistinctIslands(int[][] grid)
     */

    public static void main(String[] args) {
        int [][] grid=new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(numDistinctIslands(grid));
    }

    public static int numDistinctIslands(int [][] grid){
        Set<String> helpSet=new HashSet<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    StringBuilder sb=new StringBuilder();
                    process(grid,i,j,sb,0);
                    helpSet.add(sb.toString());
                }
            }
        }
        return helpSet.size();
    }

    //淹没的过程
    public static void process(int [][] grid, int curX, int curY, StringBuilder sb, int curDirection){
        if(curX<0||curX>=grid.length||curY<0||curY>=grid[0].length||grid[curX][curY]!=1){
            return;
        }
        // 前序遍历位置：进入 (i, j)
        sb.append(curDirection).append(',');
        grid[curX][curY]=0;
        process(grid,curX-1,curY,sb,1);//上
        process(grid,curX+1,curY,sb,2);//下
        process(grid,curX,curY-1,sb,3);//左
        process(grid,curX,curY+1,sb,4);//右
        // 后序遍历位置：离开 (i, j)
        sb.append(-curDirection).append(',');
    }
}
