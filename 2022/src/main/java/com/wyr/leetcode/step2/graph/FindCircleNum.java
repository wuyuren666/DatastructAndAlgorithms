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

    public static int findCircleNum(int[][] isConnected) {
        //获取所有节点个数
        int nodeCount=isConnected.length;
        //并查集结构
        MySet set=new MySet(isConnected);


        for(int i=0;i<nodeCount;i++){
            for(int j=0;j<nodeCount;j++){
                if(i==j){
                    continue;
                }
                if(isConnected[i][j]==1){
                    if(set.isTheSameSet(i+1,j+1)){ //先查看两个节点是否在同一集合中
                        continue;
                    }
                    set.merge(i+1,j+1); //不在同一集合中就合并
                }
            }
        }

        Set<List<Integer>> functionSet=new HashSet<>();
        //注意这里会遍历所有的values即使他们有的是同一对象
        //所以我们使用Set进行去重
        for(List<Integer> l: set.nodeSet.values()){
            functionSet.add(l);
        }
        return functionSet.size();
    }



    //使用并查集结构
    public static class MySet{
        //nodeSet:维护节点和节点所在集合的关系
        public HashMap<Integer, List<Integer>> nodeSet;

        //通过构造方法初始化好nodeSet
        public MySet(int [][] isConnected){
            nodeSet=new HashMap<>();
            for(int i=1;i<=isConnected.length;i++){
                List<Integer> set=new ArrayList<>();
                set.add(i);
                nodeSet.put(i,set);
            }
        }

        //提供接口，查询两个节点是否在同一集合中
        public boolean isTheSameSet(Integer n1,Integer n2){
            return nodeSet.get(n1)==nodeSet.get(n2);
        }

        //提供接口，将两个节点合并到同一个集合中
        public void merge(Integer n1, Integer n2){
            List<Integer> set1=nodeSet.get(n1);
            List<Integer> set2=nodeSet.get(n2);
            for(Integer i :set2){ //将set2中的所有节点放入set1中
                set1.add(i);
                //同时修改节点集合关系表中的关系，将set2中的节点所对应的集合改为set1
                nodeSet.put(i,set1);
            }

        }
    }

    public static void main(String[] args) {
        int[][] a={{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(a));
    }
}
