package com.wyr.zuoshen.zuoshen1.p7;

public class Edge {
    public int weight;//边的权重
    public Node from; //这条边出发的节点
    public Node to; //这条边到达的节点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
