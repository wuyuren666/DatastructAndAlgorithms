package com.wyr.leetcode.step2.array;

import java.net.HttpCookie;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.LockSupport;

public class RotateTest {

    /**
     * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/rotate-image
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public void rotate(int[][] matrix) {
        //先转置
        for(int i=0;i<matrix.length;i++){
            for(int j=i+1;j<matrix[0].length;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
        //再镜像反转
        for(int clo=0;clo<matrix[0].length/2;clo++){
            for(int row=0;row<matrix.length;row++){
                int temp=matrix[row][clo];
                matrix[row][clo]=matrix[row][matrix[0].length-clo-1];
                matrix[row][matrix[0].length-clo-1]=temp;
            }
        }
    }

    public static void main(String[] args) {

    }
}

