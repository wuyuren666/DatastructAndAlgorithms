package com.wyr.zuoshen.zuoshen1.p7;

import java.util.*;

public class FindShortestPath {

    /**
     * 在一个有 n 个点， m 个边的有向图中，已知每条边长，求出 1 到 n 的最短路径，返回 1 到 n 的最短路径值。
     * 如果 1 无法到 n ，输出 -1
     *
     * 图中可能有重边，无自环。
     *
     * 输入：
     * 5,5,[[1,2,2],[1,4,5],[2,3,3],[3,5,4],[4,5,5]]
     * 返回值：9
     *
     * https://www.nowcoder.com/practice/9f15b34a2a944a7798a5340ff0dba8b7?tpId=196&tqId=37573&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26pageSize%3D50%26search%3D%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D196&difficulty=undefined&judgeStatus=undefined&tags=584&title=
     */


    //采用Dijskra迪杰斯特拉算法
    //注意如果是有重边的话，那么Dijskra需要改变图的生成策略
    //这个题目也可以使用动态规格
    public static int findShortestPath (int n, int m, int[][] dp) {
        Graph graph=createGraph(n,m,dp);
        //Node和距离的映射表
        //key:从head出发到达的节点
        //value:从head出发到达的节点的最小距离
        //如果在表中，没有某个节点的记录，含义代表从head到他的距离为+00
        HashMap<Node,Integer> distanceMap=new HashMap<>();
        //存放上锁节点的Set
        HashSet<Node> selectedNodes=new HashSet<>();

        Node head=graph.nodes.get(1);
        distanceMap.put(head,0);
        Node minNode=getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);

        while (minNode!=null){
            int distance=distanceMap.get(minNode);
            for(Edge edge: minNode.edges){
                Node toNode=edge.to;
                //距离表中没有
                if(!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance+edge.weight);
                }else {//距离表中有
                    distanceMap.put(toNode,Math.min(distanceMap.get(toNode),distance+ edge.weight));
                }
            }
            selectedNodes.add(minNode);//标识为已选中
            minNode=getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        }


        if(distanceMap.containsKey(graph.nodes.get(n))){
            return distanceMap.get(graph.nodes.get(n));
        }else {
            return -1;
        }

    }

    //获取distanceMap中距离最小且没有被选中的节点
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node,Integer> distanceMap,HashSet<Node> selectedNodes){
        Node minNode=null;
        int minDistance=Integer.MAX_VALUE;

        for(Map.Entry<Node,Integer> entry: distanceMap.entrySet()){
             Node node=entry.getKey();
             int distance=entry.getValue();
             if(!selectedNodes.contains(node) && distance<minDistance){
                 minNode=node;
                 minDistance=distance;
             }
        }
        return minNode;
    }



    public static Graph createGraph(int n, int m, int [][] dp){
        Graph graph=new Graph();
        for(int i=1;i<=n;i++){
            graph.nodes.put(i,new Node(i));
        }

        for(int i=0;i<m;i++){
            Node fromNode=graph.nodes.get(dp[i][0]);
            Node toNode=graph.nodes.get(dp[i][1]);
            int weight=dp[i][2];
            Edge edge1=new Edge(weight,fromNode,toNode);
            Edge edge2=new Edge(weight,toNode,fromNode);
            fromNode.edges.add(edge1);
            toNode.edges.add(edge2);

            graph.edges.add(edge1);
            graph.edges.add(edge2);
        }
        return graph;
    }

    /*public static Graph createGraph2(int n, int m, int [][] dp){
        Graph graph=new Graph();
        for(int i=1;i<=n;i++){
            graph.nodes.put(i,new Node(i));
        }

        for(int i=0;i<m;i++){
            Node fromNode=graph.nodes.get(dp[i][0]);
            Node toNode=graph.nodes.get(dp[i][1]);
            int weight=dp[i][2];
            Edge edge1=new Edge(weight,fromNode,toNode);
            Edge edge2=new Edge(weight,toNode,fromNode);
            if(fromNode.edges.size()==0){
                fromNode.edges.add(edge1);
            }else{
                boolean flag=true;
                for(Edge edge: fromNode.edges){
                    if(edge.to==toNode&& weight< edge.weight){
                        edge.weight=weight;
                        flag=false;
                    }
                }
                if(flag){
                    fromNode.edges.add(edge1);
                }
            }
            if(toNode.edges.size()==0){
                toNode.edges.add(edge2);
            }else {
                boolean flag=true;
                for(Edge edge: toNode.edges){
                    if(edge.to==fromNode&& weight< edge.weight){
                        edge.weight=weight;
                        flag=false;
                    }
                }
                if(flag){
                    toNode.edges.add(edge2);
                }
            }

            graph.edges.add(edge1);
            graph.edges.add(edge2);
        }
        return graph;
    }*/


    public static class Graph{
        public HashMap<Integer,Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            this.nodes=new HashMap<>();
            this.edges=new HashSet<>();
        }
    }


    public static class Node{
        public int value;
        public List<Edge> edges;

        public Node(int value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }
    }

    public static class Edge{
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) {
        int[][] dp={{1,2,2},{1,4,5},{2,3,3},{3,5,4},{4,5,5}};
        System.out.println(findShortestPath(5, 5, dp));
    }
}
