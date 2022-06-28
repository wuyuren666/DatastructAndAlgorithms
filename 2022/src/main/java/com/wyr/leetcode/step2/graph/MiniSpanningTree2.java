package com.wyr.leetcode.step2.graph;

import java.util.*;

public class MiniSpanningTree2 {
    /**
     *  最有效率的做法，使用具有三个HashMap集合和三个方法的并查集结构
     */
    public static int miniSpanningTree (int n, int m, int[][] cost) {
        PriorityQueue<Edge> queue=getAllEdges(m,cost);
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<=n;i++){
            list.add(i);
        }
        UnionFindSet<Integer> ufs=new UnionFindSet<>(list);
        //每次弹出一条价值最小的边
        //查看这个边的from和to是否在同一集合中
        //不在同一集合中就将他们放于同一集合中

        int minValue=0;
        while(queue.size()!=0){
            Edge curEdge=queue.poll();
            int c1=curEdge.from;
            int c2=curEdge.to;
            //是否在同一集合
            if(!ufs.isSameSet(c1,c2)){
                ufs.union(c1,c2);
                minValue+=curEdge.value;
            }
            //只需要看sizeMap的大小就行，应为sizeMap放的就是代表元素下的元素个数，
            // 当代表元素只有一个时，那么肯定所有元素都在同一集合中了
            if(ufs.sizeMap.size()==1){
                break;
            }
        }
        return minValue;

    }

    //初始化优先级队列
    public static PriorityQueue<Edge> getAllEdges(int m,int[][] cost){
        //按照路的价值，从小到大进行排序
        PriorityQueue<Edge> result=new PriorityQueue<>((o1,o2)->o1.value-o2.value);
        for(int i=0; i<m;i++){
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


    //元素的包装类
    public static class Element<V>{
        public V value;
        public Element(V value){
            this.value=value;
        }
    }

    //并查集结构
    public static class UnionFindSet<V>{
        public HashMap<V,Element<V>> elementMap;
        public HashMap<Element<V>,Element<V>> fatherMap;
        public HashMap<Element<V>,Integer> sizeMap;

        public UnionFindSet(List<V> list){
            elementMap=new HashMap<>();
            fatherMap=new HashMap<>();
            sizeMap=new HashMap<>();
            for(V value: list){
                Element<V> element=new Element<>(value);
                elementMap.put(value,element);
                fatherMap.put(element,element);
                sizeMap.put(element,1);
            }
        }
        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> stack=new Stack<>();
            while(element!=fatherMap.get(element)){
                stack.push(element);
                element=fatherMap.get(element);
            }
            //扁平化
            while(stack.size()!=0){
                fatherMap.put(stack.pop(),element);
            }
            return element;
        }

        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                return findHead(elementMap.get(a))==findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                Element<V> headA=fatherMap.get(elementMap.get(a));
                Element<V> headB=fatherMap.get(elementMap.get(b));
                if(headA!=headB){
                    Element<V> big=sizeMap.get(headA)>sizeMap.get(headB)?headA:headB;
                    Element<V> small=big==headA?headB:headA;
                    fatherMap.put(small,big);
                    sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
                    sizeMap.remove(small);
                }
            }
        }
    }


    public static class Edge{
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
        int[][] array={{5,3,8},{1,3,6},{2,5,4},{2,3,5},{4,5,6},{3,4,3},{2,4,8},{1,2,2},{1,4,5},{5,6,2}};
        int i = miniSpanningTree(6, 10, array);
        System.out.println(i);
    }
}
