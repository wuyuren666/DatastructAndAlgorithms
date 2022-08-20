package com.wyr.zuoshen.zuoshen1.p7;

import java.util.*;

/**
 * 图的拓扑排序
 * 啥意思呢，就是依赖问题，很多项目之间可能有依赖，我们应该先按照怎么样的顺序来编译项目而使得依赖不出错
 *
 * 其实就是我们先找到不依赖于任何其他项目的项目，先进行编译 (先找到入度为0的节点)
 * 然后将依赖于它的项目改为不依赖于它 （将当前节点的后继节点的入度--）
 */
public class GraphTopuSort {

    public static List<Node> sortedTopology(Graph graph){
        //key：某一个node
        //value：剩余的入度
        HashMap<Node,Integer> inMap=new HashMap<>();
        //入度为0的点，才能进这个队列
        Queue<Node> zeroInQueue=new LinkedList<>();
        //先将所有节点和对应的入度存到inMap中
        //同时将入度为0的点入队
        for(Node node: graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in==0){ //将入度为0的节点入队
                zeroInQueue.add(node);
            }
        }

        //拓扑排序的结果，依次加入到result中
        List<Node> result=new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next,inMap.get(next)-1);//更新inMap中的临接节点的入度
                if(inMap.get(next)==0){ //入度为0
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
