package com.wyr.leetcode.step2.graph;

import java.util.*;

/**
 * 最小生成树问题
 *
 * 描述
 * 一个有 n 户人家的村庄，有 m 条路相互连接着。
 * 村里现在要修路，每条路都有一个成本价格，现在请你帮忙计算下，最少需要花费多少钱，就能让这 n 户人家连接起来。
 *
 * costcost 为一个二维数组，每个元素是一个长度为 3 的一维数组 aa
 * a[0][0] 和 a[1][1] 表示村庄 a[0][0] 和村庄 a[1][1] 有一条路，修这条路的成本价格为 a[2][2] .
 *
 * 每户之间可能有多条道路连接，但不可能自己与自己相连
 *
 *
 * https://www.nowcoder.com/practice/735a34ff4672498b95660f43b7fcd628?tpId=196&tqId=37574&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26pageSize%3D50%26search%3D%25E6%259C%2580%25E5%25B0%258F%25E7%2594%259F%25E6%2588%2590%25E6%25A0%2591%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D196&difficulty=undefined&judgeStatus=undefined&tags=&title=%E6%9C%80%E5%B0%8F%E7%94%9F%E6%88%90%E6%A0%91
 */
public class MiniSpanningTree {
    /**
     *  这其实就是最小生成树问题
     *  能跑但是会超时，不推荐这种并查集结构!
     */
    public int miniSpanningTree (int n, int m, int[][] cost) {
        PriorityQueue<Edge> queue=getAllEdges(cost);
        Myset myset=new Myset(n);
        //每次弹出一条价值最小的边
        //查看这个边的from和to是否在同一集合中
        //不在同一集合中就将他们放于同一集合中
        //直到所有的村庄都在同一集合就结束

        int minValue=0;
        HashSet<List<Integer>> functionSet; //用来判断是否所有的村庄对应的集合为同一集合
        while(queue.size()!=0){
            functionSet=new HashSet<>();
            Edge curEdge=queue.poll();//取出当前花费最少的路
            int c1=curEdge.from; //村1
            int c2=curEdge.to; //村2
            if(!myset.isTheSameSet(c1,c2)){ //村1 和村2 还不在同一集合中：就是村1和村2还不通
                myset.merge(c1,c2);   //村1，村2放入同一集合，意思就是两个村庄通路了
                minValue+=curEdge.value; //累加费用
            }
            for(Integer i: myset.nodeSetMap.keySet()){
                functionSet.add(myset.nodeSetMap.get(i));
            }
            if(functionSet.size()==1){
                break;
            }
        }
        return minValue;
    }


    public PriorityQueue<Edge> getAllEdges(int[][] cost){
        //按照路的价值，从小到大进行排序
        PriorityQueue<Edge> result=new PriorityQueue<>((o1,o2)->o1.value-o2.value);
        for(int i=0; i<cost.length;i++){
            int c1=cost[i][0];
            int c2=cost[i][1];
            int value=cost[i][2];
            Edge e1=new Edge(value,c1,c2);
            Edge e2=new Edge(value,c2,c1);
            result.add(e1);
            result.add(e2);
        }
        return result;
    }

    //并查集结构
    public class Myset{
        public HashMap<Integer, List<Integer>> nodeSetMap;
        public Myset(int n){
            nodeSetMap=new HashMap<>();
            for(int i=1;i<=n;i++){
                List<Integer> l=new ArrayList<>();
                l.add(i);
                nodeSetMap.put(i,l);
            }
        }

        public boolean isTheSameSet(int c1, int c2){
            return nodeSetMap.get(c1)==nodeSetMap.get(c2);
        }

        public void merge(int c1, int c2){
            List<Integer> s1=nodeSetMap.get(c1);
            List<Integer> s2=nodeSetMap.get(c2);

            for(Integer i: s2){
                s1.add(i);
                nodeSetMap.put(i,s1);
            }
        }
    }



    public class Edge{
        public int value;
        public int from;
        public int to;
        public Edge(int value, int from,int to){
            this.value=value;
            this.from=from;
            this.to=to;
        }
    }

    public static void main(String[] args) {

    }


}
