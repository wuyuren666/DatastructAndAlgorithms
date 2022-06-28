package com.wyr.zuoshen.zuoshen1.p6;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管切成多大长度的两半的，都需要花费20个铜板
 *
 * 一群人想要整分整块金条，怎样分最省铜板？
 * 例如，给定数组[10,20,30]，代表一共三个人，整块金条的长度为10+20+30=60
 * 金条要分成10，20，30三个部分。如果先把长度60的金条分成10和50，花费60；
 * 再把长度为50的金条分成20和30，花费50，一共花费110铜板
 * 但是如果先把长度60的金条分成30和30，花费60；再把长度30的金条分成10和20 花费30；
 * 一共花费90铜板
 *
 * 输入一个数组，返回分割的最小代价
 */
public class LessMoneySplitGold {

    //思想：先将数组中的元素放入小顶堆中，每次弹出两个较小的元素，得到一个和A；
    // 再把和X再放入小顶堆中，在弹出两个较小的元素，得到和B；
    // 再把和Y再放入小顶堆中，在弹出两个较小的元素，得到和C；
    // 知道小顶堆中的元素不足两个，最终结果就是所有A+B+C...之和
    public int lessMoney(int [] arr){
        //优先级队列，底层就是小顶堆
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for(int i:arr){
            queue.add(i);
        }
        int sum=0;
        int cur=0;
        while (queue.size()>=2){
            //poll()方法是移除堆顶元素,peek()方法是不移除，就将值取出
            cur=queue.poll()+queue.poll();
            sum+=cur;
            queue.add(cur);
        }
        return sum;
    }
}
