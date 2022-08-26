package com.wyr.bigFactory.date8_20;

import java.util.ArrayList;


public class MiGongTest {

    /**
     *  给一个二维数组，代表一个迷宫
     *  由多个0、多个1、一个8组成
     *  1代表墙，0代表路，8代表目的地
     *  每一次可以选择向上、下、左、右走一步
     *  0,1,1,1
     *  0,0,0,1
     *  1,0,8,1
     *  1,0,1,1
     */
    static ArrayList<Point> res = new ArrayList<>();
    static ArrayList<Point> temp = new ArrayList<>();
    public static ArrayList<Point> winMazeGift (int[][] maze) {
        // write code here
        int[][] visited =  new int[maze.length][maze[0].length];
        Point aim=null;
        boolean flag=false;
        for(int x = 0; x < maze.length; x++) {
            for(int y = 0; y < maze[0].length; y++) {
                if((x==0||y==0||x==maze.length-1||y==maze[0].length-1)&&maze[x][y]==0){
                    dfs(x, y, maze,visited);
                }
                if(maze[x][y]==8){
                    aim=new Point(x,y);
                }
                if(maze[x][y]==8&&x==0||y==0||x==maze.length-1||y==maze[0].length-1) {
                    flag = true;
                }
            }
        }
        if(flag){
            ArrayList<Point> ans =new ArrayList<>();
            ans.add(aim);
            return ans;
        }
        res.add(aim);
        return res;
    }
    private static void dfs(int x, int y, int[][] maze, int [][] visited) {
        // 非法
        if(x < 0 || x >= maze.length) return;
        if(y < 0 || y >= maze[0].length) return;
        if(maze[x][y] == 1) return;
        if(visited[x][y] == 1) return;
        // 成功
        if(maze[x][y] == 8) {
            //最后的这个节点不能加入，否则往回返，删除节点会有问题
            /*Point p = new Point(x, y);
            temp.add(p);*/
            if(res.size() == 0 || temp.size() < res.size()) {
                res = new ArrayList<Point>(temp);
            }
            return;
        }
        // spread
        temp.add(new Point(x, y));
        visited[x][y] = 1;
        dfs(x-1, y, maze,visited);
        dfs(x+1, y, maze,visited);
        dfs(x, y-1, maze,visited);
        dfs(x, y+1, maze,visited);
        temp.remove(temp.size()-1);
        visited[x][y] = 0;
    }

    public static void main(String[] args) {
        int [][] maze=new int [][]{{0,1,1,1},{0,0,0,1},{1,0,0,1},{1,1,8,1}};
        ArrayList<Point> res = winMazeGift(maze);
        res.stream().forEach(System.out::print);
    }

}

class Point{
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
