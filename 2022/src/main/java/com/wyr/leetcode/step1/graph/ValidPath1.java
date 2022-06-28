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
 * 执行用时：105 ms, 在所有 Java 提交中击败了24.01%的用户
 * 内存消耗：146.5 MB, 在所有 Java 提交中击败了19.42%的用户
 */
public class ValidPath1 {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Graph graph=createGraph(n,edges); //得到图
        Node sourceNode=graph.nodes.get(source);
        Node aimNode=graph.nodes.get(destination);

        //使用广度优先遍历
        boolean result=false;
        HashSet<Node> set=new HashSet<>();
        Queue<Node> queue=new LinkedList<>();

        queue.add(sourceNode);
        set.add(sourceNode);
        while(queue.size()!=0){
            Node cur=queue.poll();
            if(cur==aimNode){
                result=true;
                break;
            }
            for(Node next: cur.nexts){
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return result;
    }
    //创建图
    public Graph createGraph(int n, int[][] edges){
        Graph graph=new Graph();
        for(int i=0;i<n;i++){ //先将所有的节点加入到nodes中
            Node newNode=new Node(i);
            graph.nodes.put(i,newNode);
        }
        for(int i=0;i<edges.length;i++){
            int x1=edges[i][0];
            int x2=edges[i][1];

            graph.nodes.get(x1).nexts.add(graph.nodes.get(x2));
            graph.nodes.get(x2).nexts.add(graph.nodes.get(x1));
        }
        return graph;
    }
    //图
    public class Graph{
        public HashMap<Integer,Node> nodes;
        public Graph(){
            this.nodes=new HashMap<>();
        }
    }
    //顶点
    public class Node{
        public int value;
        public ArrayList<Node> nexts;
        public Node(int value){
            this.value=value;
            nexts=new ArrayList<>();
        }
    }
}
