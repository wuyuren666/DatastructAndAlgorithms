package com.wyr.leetcode.step2.graph;


import java.util.*;

/**
 * 使用并查集
 *
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 *
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 * 执行用时：5 ms, 在所有 Java 提交中击败了17.70%的用户
 * 内存消耗：42 MB, 在所有 Java 提交中击败了77.63%的用户
 * 通过测试用例：113 / 113
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindCircleNum {

    public  static int findCircleNum(int[][] isConnected) {

        int N=isConnected.length;
        int [] father= new int[N];
        for(int i=0;i<N;i++){
            father[i]=i;
        }

        for(int i=0;i<N;i++){
            for(int j=i+1;j<isConnected[0].length;j++){
                if(isConnected[i][j]==1){
                    if(findFather(father,i)!=findFather(father,j)){
                        union(father,i,j);
                    }
                }
            }
        }
        return getNum(father);

    }

    public static int findFather(int[] father, int index){
        if(index!=father[index]){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }

    public static void union(int [] father ,int index1, int index2){
        father[findFather(father,index1)]=findFather(father,index2);
    }

    public  static int getNum(int [] father){
        int res=0;
        for(int i=0;i<father.length;i++){
            res = (i==father[i])?res+1:res;
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] a={{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(a));
    }
}
