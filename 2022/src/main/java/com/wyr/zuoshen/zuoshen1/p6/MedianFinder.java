package com.wyr.zuoshen.zuoshen1.p6;

import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4]的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 思想：将第一个数先放入大顶堆中，接下来的数去和大顶堆中的堆顶数进行比较
 * 如果<=大顶堆的堆顶的数，那么就加入到大顶堆中
 * 否则，加入到小顶堆中
 * 如果在此期间两个堆的个数差值==2，那么就把较大的堆的堆顶元素移动到另一个堆中
 *
 * 数字都加入完毕之后，如果两个堆的大小相等，那么中位数就是两个堆堆顶元素之和除以2
 * 如果不想等，空间较大的堆的堆顶元素就是中位数
 */
public class MedianFinder {
    //大根堆
    public PriorityQueue<Integer> maxRootHeap;

    //小根堆
    public PriorityQueue<Integer> minRootHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        //Comparator是函数式接口
        maxRootHeap=new PriorityQueue<Integer>((o1,o2)->{return o2-o1;});
        minRootHeap=new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        //检查大根堆是否为空
        if(maxRootHeap.size()==0){
            //将第一个数，放入
            maxRootHeap.add(num);
            return;
        }
        //大根堆不为空，如果当前数<=大根堆堆顶的数，就放入大根堆中；
        //如果当前数>大根堆堆顶的数，就放入到小根堆中
        if(maxRootHeap.size()!=0&&num<=maxRootHeap.peek()){
            //放入大根堆
            maxRootHeap.add(num);
        }
        if(maxRootHeap.size()!=0&&num>maxRootHeap.peek()){
            //放入小根堆
            minRootHeap.add(num);
        }
        //当两个堆中的size个数相差二时
        if(maxRootHeap.size()-minRootHeap.size()==2){
            //较大的堆顶的元素弹出，进较小的堆
            minRootHeap.add(maxRootHeap.poll());
        }else if(minRootHeap.size()-maxRootHeap.size()==2){
            //较大的堆顶的元素弹出，进较小的堆
            maxRootHeap.add(minRootHeap.poll());
        }
    }

    public double findMedian() {
        if(minRootHeap.size()==maxRootHeap.size()){
            return (double)((minRootHeap.peek()+maxRootHeap.peek())/2.0);
        }else if(minRootHeap.size()>maxRootHeap.size()){
            return (double)minRootHeap.peek();
        }else{
            return (double)maxRootHeap.peek();
        }
    }
}
