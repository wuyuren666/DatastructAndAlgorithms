package com.wyr.leetcode.step2.unionFindSet;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class MinCostConnectPointsTest {
    /**
     * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
     *
     * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
     *
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
     *
     *
     * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
     * 输出：20
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/min-cost-to-connect-all-points
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int minCostConnectPoints(int[][] points) {
        //等差数列前n项和公式a1*n+((n*(n-1))/2)*d
        int pointNum=points.length;
        //理论上所有的边的个数
        int edgeNum=(pointNum*(pointNum-1))/2;
        PriorityQueue<Edge> edges= new PriorityQueue<>((o1,o2)->o1.weight-o2.weight);
        createEdges(edges,points);

        UnionFindSet ufs=new UnionFindSet(pointNum);

        int res=0;
        while(!edges.isEmpty()){
            //每次拿出一条价值最小的边
            Edge edge= edges.poll();
            if(!ufs.isSameSet(edge.from,edge.to)){
                ufs.union(edge.from,edge.to);
                res+=edge.weight;
            }
            if(ufs.sizeMap.size()==1){
                break;
            }
        }
        return res;
    }

    //这种并查集的写法和上面的时间复杂度差不多
    //最小生成树问题
    public int minCostConnectPoints2(int[][] points) {
        //等差数列前n项和公式a1*n+((n*(n-1))/2)*d
        int pointNum=points.length;
        //理论上所有的边的个数
        int edgeNum=(pointNum*(pointNum-1))/2;
        PriorityQueue<Edge> edges= new PriorityQueue<>((o1,o2)->o1.weight-o2.weight);
        createEdges(edges,points);

        int [] father= new int[pointNum];
        for(int i=0;i<pointNum;i++){
            father[i]=i;
        }
        int res=0;
        while(!edges.isEmpty()){
            //每次拿出一条价值最小的边
            Edge edge= edges.poll();
            if(findFather(father,edge.from)!=findFather(father,edge.to)){
                union(father,edge.from,edge.to);
                res+=edge.weight;
            }
            if(sizeOfSet(father)==1){
                break;
            }
        }
        return res;
    }

    //得到并查集的大小
    public int sizeOfSet(int [] father){
        int res=0;
        for(int i=0;i<father.length;i++){
            if(father[i]==i){
                res++;
            }
        }
        return res;
    }
    //合并
    public void union(int [] father, int index1, int index2){
        father[findFather(father,index1)]=findFather(father,index2);
    }

    public int findFather(int [] father, int index){
        if(father[index]!=index){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }





    public class UnionFindSet{
        public Map<Integer,Integer> fatherMap;
        public Map<Integer,Integer> sizeMap;

        public UnionFindSet(int pointNum){
            this.fatherMap=new HashMap<>();
            this.sizeMap=new HashMap<>();

            for(int i=0;i<pointNum;i++){
                fatherMap.put(i,i);
                sizeMap.put(i,0);
            }
        }
        private Integer findHead(Integer i){
            //这题扁平化反而更慢!!!!
            //Stack<Integer> stack=new Stack<>();
            while(i!=fatherMap.get(i)){
                //stack.push(i);
                i=fatherMap.get(i);
            }
            /*while(!stack.isEmpty()){
                fatherMap.put(stack.pop(),i);
            }*/
            return i;
        }
        public boolean isSameSet(Integer index1, Integer index2){
            return findHead(index1)==findHead(index2);
        }
        public void union(Integer index1, Integer index2){
            Integer father1=findHead(index1);
            Integer father2=findHead(index2);
            Integer big=sizeMap.get(father1)>=sizeMap.get(father2)?father1:father2;
            Integer small=big==father1?father2:father1;
            fatherMap.put(small,big);
            sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
            sizeMap.remove(small);
        }
    }

    public void createEdges(PriorityQueue<Edge> edges, int[][] points){
        int M=points.length;
        for(int i=0;i<M;i++){
            for(int j=i+1;j<M;j++){
                Edge edge1=new Edge(Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1])
                        ,i,j
                );
                Edge edge2=new Edge(Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1])
                        ,j,i
                );
                edges.add(edge1);
                edges.add(edge2);
            }
        }
    }

    public class Edge{
        public int weight;
        public int from; //使用points中的行来给点编号
        public int to;  //使用points中的行下标来给点编号

        public Edge(int weight, int from, int to){
            this.weight=weight;
            this.from=from;
            this.to=to;
        }
    }
}
