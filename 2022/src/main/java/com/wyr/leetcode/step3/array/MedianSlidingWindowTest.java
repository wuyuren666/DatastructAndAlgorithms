package com.wyr.leetcode.step3.array;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianSlidingWindowTest {
    /**
     *中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
     *
     * 例如：
     *
     * [2,3,4]，中位数是3
     * [2,3]，中位数是 (2 + 3) / 2 = 2.5
     * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/sliding-window-median
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //大顶堆和小顶堆
    public  PriorityQueue<Node> maxTopHeap;
    public  PriorityQueue<Node> minTopHeap;

    public class Node{
        public int index;
        public int value;

        public Node(int index, int value){
            this.index=index;
            this.value=value;
        }

        //在这里重写equals方法
        //因为我们想根据index是否相同去判断是否是同一个对象
        public boolean equals(Object o){
            Node temp=(Node) o;
            return this.value== temp.value?true:false;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        //因为有可能溢出，所以我们进行强制类型转换为精度更高的double类型
        maxTopHeap=new PriorityQueue<>((o1,o2)->(int)((double)o2.value-(double)o1.value));
        minTopHeap=new PriorityQueue<>((o1,o2)->(int)((double)o1.value-(double)o2.value));

        int number=nums.length-k+1;//最终的结果个数
        double[] result=new double[number];

        int temp=k-1;
        result[0]=getMid(nums,0,temp);
        if(temp<nums.length-1){
            for(int i=1;i<number;i++){
                removeJthNode(nums,i-1); //每次移除掉前一个节点
                result[i]=getMid(nums,i+k-1,i+k-1); //加入新的节点
            }
        }
        return result;
    }

    public void removeJthNode(int [] nums, int index){
        if(maxTopHeap.remove(new Node(index,nums[index]))==true){
            //如果两个堆的大小相差为2，则将较大堆的堆顶元素弹出，保存到另一个堆
            if(maxTopHeap.size()-minTopHeap.size()==2){
                minTopHeap.add(maxTopHeap.poll());
            }else if(minTopHeap.size()-maxTopHeap.size()==2){
                maxTopHeap.add(minTopHeap.poll());
            }
            return;
        }else{
            //如果两个堆的大小相差为2，则将较大堆的堆顶元素弹出，保存到另一个堆
            if(maxTopHeap.size()-minTopHeap.size()==2){
                minTopHeap.add(maxTopHeap.poll());
            }else if(minTopHeap.size()-maxTopHeap.size()==2){
                maxTopHeap.add(minTopHeap.poll());
            }
            minTopHeap.remove(new Node(index,nums[index]));
        }
    }


    public double getMid(int [] nums, int start, int end){
        int temp=start;
        if(maxTopHeap.size()==0){
            maxTopHeap.add(new Node(temp,nums[temp]));
            temp++;
        }
        while(temp<=end){
            //如果下面的数，比大顶堆的堆顶的数小，就放入大顶堆
            if(nums[temp]<=maxTopHeap.peek().value){
                maxTopHeap.add(new Node(temp,nums[temp]));
            }else {//否则，放入小顶堆
                minTopHeap.add(new Node(temp,nums[temp]));
            }

            //如果两个堆的大小相差为2，则将较大堆的堆顶元素弹出，保存到另一个堆
            if(maxTopHeap.size()-minTopHeap.size()==2){
                minTopHeap.add(maxTopHeap.poll());
            }else if(minTopHeap.size()-maxTopHeap.size()==2){
                maxTopHeap.add(minTopHeap.poll());
            }
            temp++;
        }
        //如果最后两个堆的数量相同
        if(maxTopHeap.size()==minTopHeap.size()){
            //注意这里可能会溢出
            return (double)(((double)maxTopHeap.peek().value+(double)minTopHeap.peek().value)/2.0);
        }else if(maxTopHeap.size()>minTopHeap.size()){
            return (double)maxTopHeap.peek().value;
        }else{
            return (double)minTopHeap.peek().value;
        }
    }

    public static void main(String[] args) {
    }

}
