package com.wyr.leetcode.step2.array;

public class CountSubIslandsTest {
    /**
     * 给你两个m x n的二进制矩阵grid1 和grid2，它们只包含0（表示水域）和 1（表示陆地）。
     * 一个 岛屿是由 四个方向（水平或者竖直）上相邻的1组成的区域。任何矩阵以外的区域都视为水域。
     *
     * 如果 grid2的一个岛屿，被 grid1的一个岛屿完全 包含，
     * 也就是说 grid2中该岛屿的每一个格子都被 grid1中同一个岛屿完全包含，那么我们称 grid2中的这个岛屿为子岛屿。
     *
     * 请你返回 grid2中 子岛屿的 数目。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-sub-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        //如果说grid2中在grid1中存在子岛屿，那么grid1[i][j]==grid2[i][j]
        //反过来说，如果岛屿grid2中存在一片陆地，在岛屿grid1的对应位置是海水，那么岛屿B就不是岛屿A的子岛。
        //先将grid2中肯定不是grid1中的子岛屿的陆地给淹没掉
        int m=grid1.length,n=grid1[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid2[i][j]==1&&grid1[i][j]==0){ //grid2中这块陆地连接的陆地全部都淹没掉
                    process(grid2,i,j);
                }
            }
        }
        //统计子岛屿
        int ans=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid2[i][j]==1){
                    ans++;
                    process(grid2,i,j);
                }
            }
        }
        return ans;
    }
    //淹没岛屿的过程
    public void process(int[][] grid, int curX, int curY){
        //越界情况
        if(curX<0||curX>=grid.length||curY<0||curY>=grid[0].length){
            return;
        }
        //陆地已经被淹没了
        if(grid[curX][curY]==0){
            return;
        }
        //陆地淹没
        grid[curX][curY]=0;
        //递归
        process(grid,curX-1,curY);//上
        process(grid,curX+1,curY);//下
        process(grid,curX,curY-1);//左
        process(grid,curX,curY+1);//右
    }
}
