package com.wyr.zuoshen.zuoshen1.p7;

import java.util.ArrayList;
import java.util.Arrays;

public class Node{
    public int value;
    public int in; //入度，就是有几条边指向我
    public int out; //出度，就是我有几条边指向别人
    public ArrayList<Node> nexts; //通过一条边可以达到的节点的集合
    public ArrayList<Edge> edges; //从当前节点出发的边

    public Node(int value) {
        this.value = value;
        in=0;
        out=0;
        nexts=new ArrayList<>();
        edges=new ArrayList<>();
    }

    public static void main(String[] args) {
        int a=1,b=2;

    }

}
