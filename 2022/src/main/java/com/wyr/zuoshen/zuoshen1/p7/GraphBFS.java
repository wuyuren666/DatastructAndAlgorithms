package com.wyr.zuoshen.zuoshen1.p7;

import java.util.*;


//图的广度优先遍历
//使用队列和Set集合
//每次将一个顶点的所有连接点进行访问
public class GraphBFS {


    public static void bfs_627(Node node){
        Queue<Node> queue=new LinkedList<>();
        Set<Node> set=new HashSet<>();
        set.add(node);
        queue.add(node);
        while (!queue.isEmpty()){
            //出队
            Node cur=queue.poll();
            System.out.println(cur.value);
            for(Node nextNode: cur.nexts){
                if(!set.contains(nextNode)){
                    queue.add(nextNode);
                    set.add(nextNode);
                }
            }
        }
    }




    public static void bfs(Node node){
        if(node==null){
            return;
        }
        Queue<Node> queue=new LinkedList<>();
        HashSet<Node> set=new HashSet<>();
        queue.add(node);
        set.add(node);
        while (queue.size()!=0) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
