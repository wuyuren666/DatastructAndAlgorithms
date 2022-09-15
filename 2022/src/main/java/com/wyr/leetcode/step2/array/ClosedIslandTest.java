package com.wyr.leetcode.step2.array;

public class ClosedIslandTest {
    /**
     * 二维矩阵 grid由 0（土地）和 1（水）组成。岛是由最大的4个方向连通的 0组成的群，
     * 封闭岛是一个完全 由1包围（左、上、右、下）的岛。
     *
     * 请返回 封闭岛屿 的数目。
     *
     * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     * 输出：2
     * 解释：
     * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-closed-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int closedIsland(int[][] grid) {
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
                if(grid[i][j]==0){
                    ans++;
                    process(grid,i,j);
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
        if(grid[curX][curY]==1){
            return;
        }
        //陆地淹没
        grid[curX][curY]=1;
        //递归
        process(grid,curX-1,curY);//上
        process(grid,curX+1,curY);//下
        process(grid,curX,curY-1);//左
        process(grid,curX,curY+1);//右
    }
}
