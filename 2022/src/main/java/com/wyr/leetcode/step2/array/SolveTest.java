package com.wyr.leetcode.step2.array;

public class SolveTest {

    /**
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
     * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。
     * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。
     * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/surrounded-regions
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void solve(char[][] board) {

        //将四个边上的O使用DFS同时将所能延伸至的O标记出来，我们标记为'#'
        int M=board.length;
        int N=board[0].length;
        for(int i=0;i<M;i++){
            if(board[i][0]=='O'){
                dfs(board,i,0);
            }
            if(board[i][N-1]=='O'){
                dfs(board,i,N-1);
            }
        }
        for(int j=1;j<N-1;j++){
            if(board[0][j]=='O'){
                dfs(board,0,j);
            }
            if(board[M-1][j]=='O'){
                dfs(board,M-1,j);
            }
        }
        //将剩下的O全部换为X，同时将'#'恢复成'O'
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(board[i][j]=='#'){
                    board[i][j]='O';
                }else if(board[i][j]=='O'){
                    board[i][j]='X';
                }

            }
        }
    }

    public void dfs(char [][] board, int curX, int curY){
        if(curX<0||curX>=board.length||curY<0||curY>=board[0].length||board[curX][curY]!='O'){
            return;
        }
        board[curX][curY]='#';
        dfs(board,curX-1,curY);
        dfs(board,curX+1,curY);
        dfs(board,curX,curY-1);
        dfs(board,curX,curY+1);
    }
}
