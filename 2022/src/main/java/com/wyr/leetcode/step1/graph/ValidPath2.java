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
        Graph graph=createGraph(n,edges); //得到图
        //使用并查集结构
        MySet mySet=new MySet(graph);
        //拿出所有的边
        HashSet<Edge> set=graph.edges;
        for(Edge curEdge: set){
            Node fromNode=curEdge.from;
            Node toNode=curEdge.to;
            if(mySet.isSameSet(fromNode,toNode)==false){
                mySet.mergeNodeToSameSet(fromNode,toNode);
            }
        }
        Node sourceNode=graph.nodes.get(source);
        Node aimNode=graph.nodes.get(destination);
        return mySet.isSameSet(sourceNode,aimNode);
    }


    //并查集结构
    public class MySet{
        //Node 属于哪一个集合的Map
        public Map<Node,List<Node>> nodeSetMap;

        //初始化,将每一个单独的节点先独自放入一个集合中
        public MySet(Graph graph){
            this.nodeSetMap=new HashMap<>();
            for(Node curNode: graph.nodes.values()){
                List<Node> curNodeSet=new ArrayList<>();
                curNodeSet.add(curNode);//将当前节点先独自加入集合
                nodeSetMap.put(curNode,curNodeSet);//将当前节点和其所在节点进行关联
            }
        }
        //提供接口，可以查询两个节点是否在同一集合中
        public boolean isSameSet(Node from, Node to){
            return nodeSetMap.get(from)==nodeSetMap.get(to);
        }
        //提供接口，将一条边中的from和to两个节点合并到同一个集合中
        public void mergeNodeToSameSet(Node from, Node to){
            List<Node> fromSet=nodeSetMap.get(from);//先从nodeSetMap中取出from节点对应的集合
            //遍历to节点所在的集合，
            //因为现在要将to和from放入同一个集合中，在无向图中同一个集合中的意思就是可达
            //所以from能到达to，那肯定也能到达to能到达的节点
            for(Node curNode: nodeSetMap.get(to)){
                fromSet.add(curNode);
                nodeSetMap.put(curNode,fromSet); //在nodeSetMap中修改原先to节点所在的集合
            }
        }

    }




    public Graph createGraph(int n, int[][] edges){
        Graph graph=new Graph();
        for(int i=0;i<n;i++){ //先将所有的节点加入到nodes中
            Node newNode=new Node(i);
            graph.nodes.put(i,newNode);
        }

        for(int i=0;i<edges.length;i++){
            int x1=edges[i][0];
            int x2=edges[i][1];
            Edge edge1=new Edge(graph.nodes.get(x1),graph.nodes.get(x2));
            Edge edge2=new Edge(graph.nodes.get(x2),graph.nodes.get(x1));
            graph.edges.add(edge1);
            graph.edges.add(edge2);
        }
        return graph;
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
