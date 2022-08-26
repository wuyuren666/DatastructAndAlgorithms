package com.wyr.leetcode.step1.graph;

import java.util.*;
/**
 * 有一个具有 n个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。
 * 图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。
 * 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
 *
 * 请你确定是否存在从顶点 start 开始，到顶点 end 结束的 有效路径 。
 *
 * 给你数组 edges 和整数 n、start和end，如果从 start 到 end 存在 有效路径 ，则返回 true，否则返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-if-path-exists-in-graph
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
 * 输出：true
 * 解释：存在由顶点 0 到顶点 2 的路径:
 * - 0 → 1 → 2
 * - 0 → 2
 *
 * 执行用时：
 * 616 ms, 在所有 Java 提交中击败了5.05%的用户
 * 内存消耗：140.8 MB, 在所有 Java 提交中击败了27.32%的用户
 */
public class ValidPath2 {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        //1 [] 0 0
        if(n==1){
            return true;
        }

        int [] father =new int [n];
        for(int i=0;i<n;i++){
            father[i]=i;
        }

        for(int i=0;i<edges.length;i++){
            //这条边连接的两个节点不在同一个集合中
            if(findFather(father,edges[i][0])!=findFather(father,edges[i][1])){
                union(father,edges[i][0],edges[i][1]);
            }
            if(findFather(father,source)==findFather(father,destination)){
                return true;
            }
        }
        return false;
    }

    //找到父元素
    public int findFather(int [] father, int index){
        if(father[index]!=index){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }

    //合并
    public void union(int [] father, int index1, int index2){
        father[findFather(father,index1)]=findFather(father,index2);
    }









    public class Graph{
        public HashMap<Integer,Node> nodes;
        public HashSet<Edge> edges;
        public Graph(){
            this.nodes=new HashMap<>();
            this.edges=new HashSet<>();
        }
    }

    public class Edge{
        public Node from;
        public Node to;

        public Edge(Node from, Node to){
            this.from=from;
            this.to=to;
        }
    }

    public class Node{
        public int value;
        public Node(int value){
            this.value=value;
        }
    }
}
