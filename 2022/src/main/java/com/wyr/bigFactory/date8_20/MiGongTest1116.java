package com.wyr.bigFactory.date8_20;

import java.util.ArrayList;
import java.util.List;

public class MiGongTest1116 {
    /**
     *  给一个二维数组，代表一个迷宫
     *  由多个0、多个1、一个8组成
     *  1代表墙，0代表路，8代表目的地
     *  每一次可以选择向上、下、左、右走一步
     *  只能由四条边上的入口进去
     *  0,1,1,1
     *  0,0,0,1
     *  1,0,8,1
     *  1,0,1,1
     */
    static int xOfEight=-1;
    static int yOfEight=-1;
    static List<Point> minPath;
    static int minStep=Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[][] maze = new int[][]{{0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 1, 0, 1}, {0, 0, 0, 8, 0, 0}, {1, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 1}};
        int M=maze.length;
        int N=maze[0].length;
        int [][] visited= new int[M][N];//用来记录是否已经访问过

        //首先应该先检查四条边上是否有8
        for(int i=0;i<M;i++){
            if(maze[i][0]==8){
                xOfEight=i;
                yOfEight=0;
                break;
            }
            if(maze[i][N-1]==8){
                xOfEight=i;
                yOfEight=N-1;
                break;
            }
        }

        for(int j=1;j<N-1;j++){
            if(maze[0][j]==8){
                xOfEight=0;
                yOfEight=8;
                break;
            }
            if(maze[M-1][j]==8){
                xOfEight=M-1;
                yOfEight=j;
                break;
            }
        }

        if(xOfEight!=-1&&yOfEight!=-1){//在边界上找到8了
            minPath.add(new Point(xOfEight,yOfEight));
            minPath.forEach(System.out::print);
        }else { //没有在边界上找到8，需要在边界上找到入口处，然后进去DFS
            for(int i=0;i<M;i++){
                if(maze[i][0]==0){
                    process(maze,i,0,0,visited,new ArrayList<>());
                }
                if(maze[i][N-1]==0){
                    process(maze,i,N-1,0,visited,new ArrayList<>());
                }
            }

            for(int j=1;j<N-1;j++){
                if(maze[0][j]==0){
                    process(maze,0,j,0,visited,new ArrayList<>());
                }
                if(maze[M-1][j]==0){
                    process(maze,M-1,j,0,visited,new ArrayList<>());
                }
            }
        }
        minPath.add(new Point(xOfEight,yOfEight));
        minPath.forEach(System.out::print);
    }

    public static void process(int [][] maze, int curX, int curY, int count, int [][] visited, List<Point> tempStep){
        //越界或者碰到了墙
        if(curX<0||curX>=maze.length||curY<0||curY>=maze[0].length||maze[curX][curY]==1||visited[curX][curY]==1){
            return;
        }
        //到达最终的8
        if(maze[curX][curY]==8){
            if(xOfEight==-1&&yOfEight==-1){
                xOfEight=curX;
                yOfEight=curY;
            }
            if(count<minStep){
                minPath=new ArrayList<>(tempStep);
                minStep=count;
            }
            return;
        }
        visited[curX][curY]=1;
        tempStep.add(new Point(curX,curY));

        process(maze,curX-1,curY,count+1,visited,tempStep);
        process(maze,curX+1,curY,count+1,visited,tempStep);
        process(maze,curX,curY-1,count+1,visited,tempStep);
        process(maze,curX,curY+1,count+1,visited,tempStep);
        visited[curX][curY]=0;
        tempStep.remove(tempStep.size()-1);
    }

}

