package com.wyr.leetcode.step2.graph;


import java.util.*;

/**
 * 使用并查集
 * <p>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * 执行用时：5 ms, 在所有 Java 提交中击败了17.70%的用户
 * 内存消耗：42 MB, 在所有 Java 提交中击败了77.63%的用户
 * 通过测试用例：113 / 113
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindCircleNum {

    public static int findCircleNum(int[][] isConnected) {

        int N = isConnected.length;
        int[] father = new int[N];
        // 初始化
        for (int i = 0; i < N; i++) {
            father[i] = i;
        }
        // 遍历数组，进行并查集的合并操作
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < isConnected[0].length; j++) {
                if (isConnected[i][j] == 1) {
                    if (findFather(father, i) != findFather(father, j)) {
                        union(father, i, j);
                    }
                }
            }
        }
        return getNum(father);
    }

    /**
     * 并查集：逻辑上的数据结构
     */
    public static int findFather(int[] father, int index) {
        if (index != father[index]) {
            // 并进行路径压缩，即将当前节点直接连接到根节点上，以提高后续查找效率。
            father[index] = findFather(father, father[index]);
        }
        return father[index];
    }
    /**
     * 合并两个并查集
     *
     * @param father  表示并查集的父节点数组
     * @param index1  第一个元素的索引
     * @param index2  第二个元素的索引
     *
     * 通过将两个元素的根节点之一指向另一个元素的根节点，实现两个并查集的合并
     */
    public static void union(int[] father, int index1, int index2) {
        // 找到index1和index2所在并查集的根节点，并将index1的根节点指向index2的根节点
        father[findFather(father, index1)] = findFather(father, index2);
    }


    /**
     * 统计数组中自身索引与值相同的元素数量
     *
     * @param father 整数数组，假设其中的值非负且小于数组长度
     * @return 返回满足条件的元素数量
     */
    public static int getNum(int[] father) {
        // 初始化结果变量为0，用于累计满足条件的元素数量
        int res = 0;
        // 遍历数组中的每个元素
        for (int i = 0; i < father.length; i++) {
            // 判断当前元素的索引是否与其值相等
            res = i == father[i] ? res + 1 : res;
        }
        // 返回满足条件的元素数量
        return res;
    }



    public static void main(String[] args) {
        int[][] a = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(a));
    }
}
