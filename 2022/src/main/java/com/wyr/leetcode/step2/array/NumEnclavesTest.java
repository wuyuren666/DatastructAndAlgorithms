package com.wyr.leetcode.step2.array;

public class NumEnclavesTest {
    /**
     * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
     *
     * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
     *
     * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
     *
     * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
     * 输出：3
     * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-enclaves
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        //先将边界上的陆地全都淹没
        for (int j = 0; j < n; j++) {
            // 把靠上边的岛屿淹掉
            process(grid, 0, j);
            // 把靠下边的岛屿淹掉
            process(grid, m - 1, j);
        }
        for (int i = 0; i < m; i++) {
            // 把靠左边的岛屿淹掉
            process(grid, i, 0);
            // 把靠右边的岛屿淹掉
            process(grid, i, n - 1);
        }
        int ans=0;
        for(int i=1;i<grid.length-1;i++){
            for(int j=1;j<grid[0].length-1;j++){
                if(grid[i][j]==1){
                    ans+=process(grid,i,j);
                }
            }
        }
        return ans;
    }

    //淹没岛屿的过程,并可统计数量
    public int process(int[][] grid, int curX, int curY){
        //越界情况
        if(curX<0||curX>=grid.length||curY<0||curY>=grid[0].length){
            return 0;
        }
        //陆地已经被淹没了
        if(grid[curX][curY]==0){
            return 0;
        }
        int res=1; //统计陆地数量
        //陆地淹没
        grid[curX][curY]=0;
        //递归
        res+=process(grid,curX-1,curY);//上
        res+=process(grid,curX+1,curY);//下
        res+=process(grid,curX,curY-1);//左
        res+=process(grid,curX,curY+1);//右
        return res;
    }

}
