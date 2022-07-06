package com.wyr.zuoshen.zuoshen1.p8;


import java.util.ArrayList;
import java.util.HashMap;

public class Dijkskra {
    /**
     * Dijskra使用堆的加速写法
     * 有点复杂，需要自己手动撸一个堆
     */
    public static HashMap<Node,Integer> dijskra(Node head, int size){
        NodeHeap nodeHeap=new NodeHeap(size);
        nodeHeap.addUpdateOrIgnore(head,0);
        HashMap<Node,Integer> result=new HashMap<>();
        while(!nodeHeap.isEmpty()){
            NodeRecord record=nodeHeap.pop();
            Node cur=record.node;
            int distance= record.distance;
            for(Edge edge: cur.edges){
                nodeHeap.addUpdateOrIgnore(edge.to, edge.weight+distance);
            }
            result.put(cur,distance);
        }
        return  result;
    }

    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap{
        private Node[] nodes;
        private HashMap<Node,Integer> heapIndexMap;
        private HashMap<Node,Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes=new Node[size];
            heapIndexMap=new HashMap<>();
            distanceMap=new HashMap<>();
            this.size = size;
        }

        public void addUpdateOrIgnore(Node node, int distance){
            if(inHeap(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node,heapIndexMap.get(node));
            }
            if(!isEntered(node)){
                nodes[size]=node;
                heapIndexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size++);
            }
        }

        public NodeRecord pop(){
            NodeRecord nodeRecord=new NodeRecord(nodes[0],distanceMap.get(nodes[0]));
            swap(0,size-1);
            heapIndexMap.put(nodes[size-1],-1);
            distanceMap.remove(nodes[size-1]);
            nodes[size-1]=null;
            heapify(0,--size);
            return nodeRecord;
        }

        private void insertHeapify(Node node,int index){
            while (distanceMap.get(nodes[index])<distanceMap.get(nodes[(index-1)/2])){
                swap(index,(index-1)/2);
                index=(index-1)/2;
            }
        }

        private void heapify(int index, int size){
            int left=index*2+1;
            while(left<size){
                int smallest=left+1<size&&distanceMap.get(nodes[left+1])<distanceMap.get(nodes[left])?left+1:left;
                smallest=distanceMap.get(nodes[smallest])<distanceMap.get(nodes[index])?smallest:index;

                if(smallest==index){
                    break;
                }
                swap(smallest,index);
                index=smallest;
                left=index*2+1;
            }
        }

        public boolean isEmpty(){
            return size==0;
        }

        private boolean isEntered(Node node){
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node){
            return isEntered(node)&&heapIndexMap.get(node)!=-1;
        }
        private void swap(int index1,int index2){
            heapIndexMap.put(nodes[index1],index2);
            heapIndexMap.put(nodes[index2],index1);
            Node temp=nodes[index1];
            nodes[index1]=nodes[index2];
            nodes[index2]=temp;
        }

    }


    public static class Node{
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
    }
    public static class Edge {
        public int weight;//边的权重
        public Node from; //这条边出发的节点
        public Node to; //这条边到达的节点

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
}
