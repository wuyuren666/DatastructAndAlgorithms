package com.wyr.zuoshen.zuoshen1.p7;

import java.util.*;

public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes=new HashMap<>();
        this.edges=new HashSet<>();
    }

    public Graph(HashMap<Integer, Node> nodes, HashSet<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    //matrix:N*3的矩阵
    //[from节点上面的值, to节点上面的值, weight]
    public static Graph createGraph(Integer[][] matrix){
        Graph graph=new Graph();
        TreeMap<Integer,Integer> tmap=new TreeMap<>();
        for(int i=0; i< matrix.length;i++){
            Integer fromValue=matrix[i][0];
            Integer toValue=matrix[i][1];
            Integer weight=matrix[i][2];
            if(!graph.nodes.containsKey(fromValue)){
                graph.nodes.put(toValue,new Node(fromValue));
            }
            if(!graph.nodes.containsKey(toValue)) {
                graph.nodes.put(toValue, new Node(toValue));
            }
            Node fromNode = graph.nodes.get(fromValue);
            Node toNode = graph.nodes.get(toValue);

            Edge newEdge=new Edge(weight,fromNode,toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
