package com.wyr.zuoshen.zuoshen1.p6;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：
 * 正整数数组costs
 * 正整数数组profits
 * 正数k
 * 正数m
 * 含义：
 * count[i]表示i号项目的花费
 * profit[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * k表示你只能串行的最多做k个项目
 * m表示你的初始的资金
 *
 * 说明：
 * 你没做完一个项目，马上获得的收益，可以支持你去做下一个项目
 *
 * 输出：
 * 你最后获得的最大钱数
 */
public class MaxProfit {
    public static class Node{
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static int findMaximizedCapital(int k, int m, int[] profits, int[] costs){
        //创建按照每个项目花费从小到大的优先级队列
        PriorityQueue<Node> minCostQ=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) { //第一个参数减去第二个参数就是从小到大
                return o1.c-o2.c;
            }
        });
        //创建按照每个项目的利润从大到小排序
        PriorityQueue<Node> maxProfitQ=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) { //第二个参数减去第一个参数就是从大到小
                return o2.p-o1.p;
            }
        });
        //先将所有项目，放在minCostQ中锁起来
        for(int i=0;i<profits.length;i++){
            minCostQ.add(new Node(profits[i],costs[i]));
        }
        for(int i=0;i<k;i++){//可以做k个项目
            //力所能及的项目都解锁,放到maxProfitQ
            while (minCostQ.size()!=0&&m>=minCostQ.peek().c){
                maxProfitQ.add(minCostQ.poll()); //将力所能及的项目从minCostQ中移到maxProfitQ中
            }
            if(maxProfitQ.size()==0){ //无项目做
                return m;
            }
            m+=maxProfitQ.poll().p; //选择力所能及的项目中的利润最大的来做
        }
        return m;
    }


}
