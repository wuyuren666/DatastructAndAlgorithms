package com.wyr.zuoshen.zuoshen1.p10;

import com.wyr.leetcode.step1.graph.ValidPath2;

import java.util.*;

/**
 * 并查集结构，这样写是最佳的
 */
public class UnionFind {

    /**
     * 包装类，样本进来会包一层，叫做元素
     */
    public static class Element<V>{
        public V value;

        public Element(V v){
            this.value=v;
        }
    }

    /**
     * 三个map集合
     * elementMap：提供V-->Element的映射关系
     * fatherMap：提供Element -->Element的映射关系，value就是当前key的父
     * sizeMap：提供代表Element-->其集合大小的映射关系
     * 三个成员方法：
     * isSameSet(V v1, V v2)：查询是否在同一个集合，也就是说他们的头即代表Element是否是同一个
     * union(V v1, V v2)：将两个元素合并到同一个集合中，思想就是将数量少的挂在数量多的下面
     * findHead(Element<V> element)：找到这个包装元素的代表元素
     */
    public static class UnionFindSet<V> {
        public HashMap<V, Element<V>> elementMap;
        //key 某个元素  value 该元素的父
        public HashMap<Element<V>, Element<V>> fatherMap;
        //key  某个集合的代表元素  value  该集合的大小
        public HashMap<Element<V>, Integer> sizeMap;

        //使用构造函数对并查集结构初始化
        public UnionFindSet(List<V> list) {
            this.elementMap = new HashMap<>();
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();

            for (V value : list) {
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        //给定一个element，往上一直找，把代表元素返回
        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> stack=new Stack<>();
            //最终element指向的就是代表元素，只有代表元素它的父才是它自己
            while (element!=fatherMap.get(element)){
                stack.push(element);
                element=fatherMap.get(element);
            }
            //优化！ 扁平化，将刚刚沿途碰到的元素它的父类都可以改为element
            while (stack.size()!=0){
                fatherMap.put(stack.pop(),element);
            }
            return element;
        }

        //向外部暴露的接口，查看是否是同一集合
        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                return findHead(elementMap.get(a))==findHead(elementMap.get(b));
            }
            return false;
        }

        //向外部暴露的接口，合并
        public void union (V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                Element<V> headA = findHead(elementMap.get(a));
                Element<V> headB = findHead(elementMap.get(b));
                //这两个节点的代表元素不是同一个
                if(headA!=headB){
                    Element<V> big=sizeMap.get(headA)>sizeMap.get(headB)?headA:headB;
                    Element<V> small=big==headA?headB:headA;
                    //将元素个数少的挂到元素个数多的上面
                    fatherMap.put(small,big);
                    sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
                    sizeMap.remove(small);
                }
            }
        }


        public static void main(String[] args) {
            Map<List<String>,Integer> map=new HashMap<>();
            List<String> a=new ArrayList<>();
            a.add("abc");
            List<String> b=new ArrayList<>();
            b.add("abc");
            map.put(a,0);
            map.put(b,1);
            System.out.println("aaa");
        }





    }





























    /**
     * 之前的写法,不可以不用看了,效率不高
     */
    public static class MySet{
        //Node 属于哪一个集合的Map
        public Map<ValidPath2.Node, List<ValidPath2.Node>> nodeSetMap;

        //初始化,将每一个单独的节点先独自放入一个集合中
        public MySet(ValidPath2.Graph graph){
            this.nodeSetMap=new HashMap<>();
            for(ValidPath2.Node curNode: graph.nodes.values()){
                List<ValidPath2.Node> curNodeSet=new ArrayList<>();
                curNodeSet.add(curNode);//将当前节点先独自加入集合
                nodeSetMap.put(curNode,curNodeSet);//将当前节点和其所在节点进行关联
            }
        }
        //提供接口，可以查询两个节点是否在同一集合中
        public boolean isSameSet(ValidPath2.Node from, ValidPath2.Node to){
            return nodeSetMap.get(from)==nodeSetMap.get(to);
        }
        //提供接口，将一条边中的from和to两个节点合并到同一个集合中
        public void mergeNodeToSameSet(ValidPath2.Node from, ValidPath2.Node to){
            List<ValidPath2.Node> fromSet=nodeSetMap.get(from);//先从nodeSetMap中取出from节点对应的集合
            //遍历to节点所在的集合，
            //因为现在要将to和from放入同一个集合中，在无向图中同一个集合中的意思就是可达
            //所以from能到达to，那肯定也能到达to能到达的节点
            for(ValidPath2.Node curNode: nodeSetMap.get(to)){
                fromSet.add(curNode);
                nodeSetMap.put(curNode,fromSet); //在nodeSetMap中修改原先to节点所在的集合
            }
        }

    }



}
