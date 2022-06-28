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
        int xLength=grid.length;
        int yLength=grid[0].length;
        int result=0;
        for(int x=0;x<xLength;x++){
            for(int y=0;y<yLength;y++){
                if(grid[x][y]=='1'){
                    result++;
                    process(grid,x,y,xLength-1,yLength-1);
                }
            }
        }
        return result;

    }

    //感染过程
    //curX，curY:当前来到的位置
    public void process(char[][] grid, int curX, int curY, int x, int y){
        if(curX<0||curX>x||curY<0||curY>y||grid[curX][curY]!='1'){ //baseCase：越界，或者当前字符不是'1'字符
            return;
        }
        grid[curX][curY]='2';
        process(grid,curX-1,curY,x,y);//上
        process(grid,curX+1,curY,x,y);//下
        process(grid,curX,curY-1,x,y);//左
        process(grid,curX,curY+1,x,y);//右
    }
}
