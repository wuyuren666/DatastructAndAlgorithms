package com.wyr.bigFactory.date8_20;

import java.util.ArrayList;


public class  MiGongTest {

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
    static ArrayList<Point> minStep = new ArrayList<>();
    static int minCount = Integer.MAX_VALUE;
    static int theXOfEight;
    static int theyOfEight;
    public static void main(String[] args) {
        int[][] maze = new int[][]{{0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 1, 0, 1}, {0, 0, 0, 8, 0, 0}, {1, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 1}};
        int[][] visited = new int[maze.length][maze[0].length];
        //先在边界上是否有8？
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == 8) {
                minStep.add(new Point(i, 0));
                break;
            }
            if (maze[i][maze[0].length - 1] == 8) {
                minStep.add(new Point(i, maze[0].length - 1));
                break;
            }
        }
        //先在边界上是否有8？
        for (int j = 0; j < maze[0].length; j++) {
            if (maze[0][j] == 8) {
                minStep.add(new Point(0, j));
                break;
            }
            if (maze[maze.length - 1][j] == 8) {
                minStep.add(new Point(maze.length - 1, j));
                break;
            }
        }

        if (minStep.size() != 0) {//边界上找到8了
            minStep.stream().forEach(System.out::print);
        } else {//边界上没有找到8
            ArrayList<Point> tempList = new ArrayList<>();
            for (int i = 0; i < maze.length; i++) {
                if (maze[i][0] == 0) {
                    winMazeGift(maze, visited, i, 0, 0, tempList);
                }
                if (maze[i][maze[0].length - 1] == 0) {
                    winMazeGift(maze, visited, i, maze[0].length - 1, 0, tempList);
                }
            }
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[0][j] == 0) {
                    winMazeGift(maze, visited, 0, j, 0, tempList);
                }
                if (maze[maze.length - 1][j] == 0) {
                    winMazeGift(maze, visited, maze.length - 1, 0, j, tempList);
                }
            }
            minStep.add(new Point(theXOfEight, theyOfEight));//最后加入8的Point
            minStep.stream().forEach(System.out::print);
        }


    }

    public static void winMazeGift(int[][] maze, int[][] visited, int curX, int curY, int count, ArrayList<Point> tempSteps) {
        //越界或者碰到墙
        if (curX < 0 || curX >= maze.length || curY < 0 || curY >= maze[0].length || visited[curX][curY] == 1 || maze[curX][curY] == 1) {
            return;
        }
        if (maze[curX][curY] == 8) { //达到目标
            theXOfEight = curX;
            theyOfEight = curY;
            if (count < minCount) {
                minCount = count;
                minStep = new ArrayList<>(tempSteps); //注意这里要new一个，因为回溯的时候会将tempSteps清空
            }
            return;
        }
        //没有达到目标
        visited[curX][curY] = 1;//当前位置标记为已经访问过
        tempSteps.add(new Point(curX, curY));
        winMazeGift(maze, visited, curX - 1, curY, count + 1, tempSteps);//上
        winMazeGift(maze, visited, curX + 1, curY, count + 1, tempSteps);//下
        winMazeGift(maze, visited, curX, curY - 1, count + 1, tempSteps);//左
        winMazeGift(maze, visited, curX, curY + 1, count + 1, tempSteps);//右
        visited[curX][curY] = 0;//当前位置取消标记
        tempSteps.remove(tempSteps.size() - 1);
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
