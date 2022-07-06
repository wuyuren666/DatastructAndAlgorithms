package com.wyr.zuoshen.zuoshen1.p8;

import java.util.*;

public class Dijkskra2 {
    /**
     * 单源最短路径，同时记录下路径
     */

    public static void main(String[] args) {
        int [][]edges={{1,2,5},{1,3,2},{2,4,1},{2,5,6},{3,4,6},{3,6,8},{4,5,1},{4,6,2},{5,7,7},{6,7,3}};
        System.out.println(getMinPath(1, 7, edges));
    }
    public static List<Node> getMinPath(int from, int to, int[][] edges){
        //得到图
        Graph graph=new Graph(edges);
        int nodeNum=graph.nodes.size();
        //创建距离表，开始距离表中没有代表从from到各个节点的距离为无穷
        Map<Node,Integer> distanceMap=new HashMap<>();
        //用来记录路径的前置顶点表，为了保存路径，我们加一个这个前置顶点表就好
        //现在有7个节点我们只是用i=1~7的这几个下标的位置
        int [] path=new int[nodeNum+1];
        //初始化，from节点到自己的距离为0，其他节点的距离均为+00
        for(int i=1;i<=nodeNum;i++){
            if(i==from){
                distanceMap.put(graph.nodes.get(i),0);
            }else{
                distanceMap.put(graph.nodes.get(i),Integer.MAX_VALUE);
            }
        }
        //创建用于过滤的set集合，将已经选过的节点放入set集合中
        Set<Node> selectedNodes=new HashSet<>();
        while(selectedNodes.size()!=nodeNum){
            Node curNode= getMinDistanceAndUnSelectedNode(selectedNodes,distanceMap);
            int myDistance=distanceMap.get(curNode);
            for(Edge edge: curNode.edges){
                 int edgeDistance=edge.weight;
                 Node toNode=edge.to;
                 if(myDistance+edgeDistance<distanceMap.get(toNode)){
                     //刷新距离表
                     distanceMap.put(toNode,myDistance+edgeDistance);
                     //同时刷新前置顶点表
                     path[toNode.value]=curNode.value;
                 }
            }
            selectedNodes.add(curNode);
        }
        List<Node> pathList=new ArrayList<>();
        //根据path前置顶点表，返回路径上的所有Node
        Node curNode=graph.nodes.get(to);
        pathList.add(curNode);
        while(curNode!=graph.nodes.get(from)){
            curNode=graph.nodes.get(path[curNode.value]);
            pathList.add(curNode);
        }

        return pathList;
    }
    //获得没有被锁定节点中的距离最小值
    public static Node getMinDistanceAndUnSelectedNode(Set<Node> selectedNodes,Map<Node,Integer> distanceMap){
         int distance=Integer.MAX_VALUE;
         Node res=null;
         for(Map.Entry<Node,Integer> entry: distanceMap.entrySet()){
              int curDistance=entry.getValue();
              Node curNode=entry.getKey();
              if(!selectedNodes.contains(curNode)&&curDistance<distance){ //距离小，并且没有被锁定
                   distance=curDistance;
                   res=curNode;
              }
         }
         return res;
    }

    public static class Graph{
        public Map<Integer,Node> nodes;

        public Graph(int [][] edges) {
            this.nodes=new HashMap<>();
            for(int i=0;i<edges.length;i++){
                int from=edges[i][0];
                int to=edges[i][1];
                int weight=edges[i][2];
                Node fromNode=null;
                Node toNode=null;
                if(!nodes.containsKey(from)){
                    fromNode=new Node(from);
                }else{
                    fromNode=nodes.get(from);
                }
                if(!nodes.containsKey(to)){
                    toNode=new Node(to);
                }else{
                    toNode=nodes.get(to);
                }
                Edge edge1=new Edge(weight,fromNode,toNode);
                Edge edge2=new Edge(weight,toNode,fromNode);
                fromNode.edges.add(edge1);
                toNode.edges.add(edge2);
                nodes.put(from,fromNode);
                nodes.put(to,toNode);
            }
        }
    }



    public static class Node{
        public int value;
        //从自己出发延伸出去的边
        public List<Edge> edges;

        public Node(int value) {
            this.value = value;
            edges=new ArrayList<>();
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

}
