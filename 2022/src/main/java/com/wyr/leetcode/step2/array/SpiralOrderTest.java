package com.wyr.leetcode.step2.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTest {
    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * https://leetcode.cn/problems/spiral-matrix/
     */

    //这个挺难想的
    public static List<Integer> spiralOrder1(int[][] matrix) {
        int M= matrix.length;//矩阵的行数
        int N=matrix[0].length;//矩阵的列数
        List<Integer> list=new ArrayList<>();
        int count = (Math.min(M,N)+1)/2;//按照"上边"->"右边"->"下边"->"左边" 遍历多少轮
        //总共遍历多少轮
        for(int i=0;i<count;i++){
            //遍历"上边"
            for(int j=i;j<=N-i-1;j++){
                list.add(matrix[i][j]);
            }
            //遍历"右边"
            for(int j=i+1;j<=M-i-1;j++){
                list.add(matrix[j][N-i-1]);
            }
            //遍历下边
            for(int j=i+1;j<N-i&&(M-1)-i>i;j++){
                list.add(matrix[(M-1)-i][(N-1)-j]);
            }
            //遍历左边
            for(int j=i+1;j<M-i-i&&i<(N-1)-i;j++){
                list.add(matrix[(M-1)-j][i]);
            }
        }
        return list;
    }


    //这方式比较好理解
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        int rows = matrix.length, columns = matrix[0].length;

        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        while (left <= right && top <= bottom) {
            //填上边
            for (int coloum = left; coloum <= right; coloum++) {
                list.add(matrix[top][coloum]);
            }
            //填右边
            for (int row = top + 1; row <= bottom; row++) {
                list.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                //填下边
                for (int coloum = right - 1; coloum >= left; coloum--) {
                    list.add(matrix[bottom][coloum]);
                }
                //填左边
                for (int row = bottom - 1; row > top; row--) {
                    list.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] array={{1,2,3,4},{5,6,7,8},{9,10,11,12}};

    }
}
