package com.wyr.leetcode.step2.unionFindSet;

import java.util.*;

public class FindRedundantConnectionTest {
    /**
     * 树可以看成是一个连通且 无环的无向图。
     *
     * 给定往一棵n 个节点 (节点值1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n中间，
     * 且这条附加的边不属于树中已存在的边。
     * 图的信息记录于长度为 n 的二维数组 edges，edges[i] = [ai, bi]表示图中在 ai 和 bi 之间存在一条边。
     *
     * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组edges中最后出现的边。
     *
     * 输入: edges = [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/redundant-connection
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //执行用时：7 ms, 在所有 Java 提交中击败了5.02%的用户
    //内存消耗：41.7 MB, 在所有 Java 提交中击败了35.50%的用户
    public int[] findRedundantConnection(int[][] edges) {
        int M=edges.length;//M也代表节点的个数
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<=M;i++){
            list.add(i);
        }
        UnionfindSet<Integer> ufs=new UnionfindSet<>(list);
        int countRow=-1;

        for(int i=0;i<M;i++){
            Integer n1=edges[i][0];
            Integer n2=edges[i][1];
            if(!ufs.isSameSet(n1,n2)){ //不是多余的边才会进
                ufs.union(n1,n2);
            }else{
                countRow=i;
            }
        }
        return edges[countRow];
    }

    //执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：41.3 MB, 在所有 Java 提交中击败了86.33%的用户
    public  int[] findRedundantConnectionBest(int[][] edges) {
        int M=edges.length;
        int [] father=new int[M+1];
        //自己是自己的代表元素
        for(int i=1;i<=M;i++){
            father[i]=i;
        }
        int countRow=-1;
        for (int i = 0; i < M; i++) {
            int node1 = edges[i][0], node2 = edges[i][1];
            //这两个节点的代表元素是否相同
            if (findFather(father, node1) != findFather(father, node2)) {
                union(father, node1, node2);
            } else {
                countRow=i;
            }
        }
        return edges[countRow];

    }
    public void union(int [] father, int n1, int n2){
        //findFather(father,n2)找到n2的代表节点
        //findFather(father, n1)找到n1的代表节点
        father[findFather(father, n1)] = findFather(father, n2);
    }
    //查询父代表元素，会有扁平化的过程
    public int findFather(int[] father, int index){
        if(father[index]!=index){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }








    public static class Element<V>{
        public V value;
        public Element(V value){
            this.value=value;
        }
    }

    public static class UnionfindSet<V>{
        public Map<V,Element<V>> elementMap;
        public Map<Element<V>,Element<V>> fatherMap;
        public Map<Element<V>,Integer> sizeMap;

        public UnionfindSet(List<V> list){
            this.elementMap=new HashMap<>();
            this.fatherMap=new HashMap<>();
            this.sizeMap=new HashMap<>();
            for(V value:list){
                Element<V> element=new Element<>(value);
                elementMap.put(value,element);
                fatherMap.put(element,element);
                sizeMap.put(element,0);
            }
        }

        private Element<V> findHead(Element<V> a){
            Stack<Element<V>> stack=new Stack<>();
            while(a!=fatherMap.get(a)){
                stack.push(a);
                a=fatherMap.get(a);
            }
            //扁平化
            while(!stack.isEmpty()){
                fatherMap.put(stack.pop(),a);
            }
            return a;
        }

        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                return findHead(elementMap.get(a))==findHead(elementMap.get(b));
            }
            return false;
        }
        public void union(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                Element<V> fatherA=findHead(elementMap.get(a));
                Element<V> fatherB=findHead(elementMap.get(b));
                if(fatherA!=fatherB){
                    Element<V> big= sizeMap.get(fatherA)>=sizeMap.get(fatherB)?fatherA:fatherB;
                    Element<V> small= big==fatherA?fatherB:fatherA;
                    //将size较小的代表元素加入到size较大的代表元素中
                    fatherMap.put(small,big);
                    //将较大的代表元素的size更新
                    sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
                    sizeMap.remove(small);
                }
            }
        }
    }
}
