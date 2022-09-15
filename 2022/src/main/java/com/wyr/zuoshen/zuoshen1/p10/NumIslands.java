package com.wyr.zuoshen.zuoshen1.p10;

public class NumIslands {
    /**
     * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     *
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     *
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numIslands(char[][] grid) {
        int ans=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    ans++;
                    process(grid,i,j);
                }
            }
        }
        return ans;
    }

    //淹没岛屿的过程
    public void process(char[][] grid, int curX, int curY){
        //越界情况
        if(curX<0||curX>=grid.length||curY<0||curY>=grid[0].length){
            return;
        }
        //陆地已经被淹没了
        if(grid[curX][curY]=='0'){
            return;
        }
        //陆地淹没
        grid[curX][curY]='0';
        //递归
        process(grid,curX-1,curY);//上
        process(grid,curX+1,curY);//下
        process(grid,curX,curY-1);//左
        process(grid,curX,curY+1);//右
    }
}
