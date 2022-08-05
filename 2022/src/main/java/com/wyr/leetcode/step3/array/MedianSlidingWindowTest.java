package com.wyr.leetcode.step3.array;


import java.util.*;

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
    public static  PriorityQueue<Long> maxTopHeap;
    public  static PriorityQueue<Long> minTopHeap;

    public static double[] medianSlidingWindow(int[] nums, int k) {
        //初始化大/小顶堆
        maxTopHeap=new PriorityQueue<>((o1,o2)->{
            //因为我们  int compare(T o1, T o2) 返回值的类型应该为int
            //应为题目数据给的很绝 -2147483648,2147483648
            // 如果范型使用Integer肯定会溢出，所以范型我们使用Long
            // 并且，如果直接return (int)(o1-o2)的话，如果o1=2147483648,o2=-2147483648。那么肯定会溢出
            //所以不能写成原先的写法
           if((o2-o1)>0){
               return 1;
           }
           return -1;
        });
        minTopHeap=new PriorityQueue<>((o1,o2)->{
            if((o1-o2)>0){
                return 1;
            }
            return -1;
        });

        int number=nums.length-k+1;//最终的结果个数
        double[] result=new double[number];

        int temp=k-1;
        result[0]=getMid(nums,0,temp);
        if(temp<nums.length-1){
            for(int i=1;i<number;i++){
                removeJthNode(nums,i-1); //每次移除掉前一个节点
                result[i]=getMid(nums,i+k-1); //加入新的节点
            }
        }
        return result;
    }

    public static void removeJthNode(int [] nums, int index){
        if(maxTopHeap.remove((long)nums[index])==true){
            //如果两个堆的大小相差为2，则将较大堆的堆顶元素弹出，保存到另一个堆
            if(maxTopHeap.size()-minTopHeap.size()==2){
                minTopHeap.add(maxTopHeap.poll());
            }else if(minTopHeap.size()-maxTopHeap.size()==2){
                maxTopHeap.add(minTopHeap.poll());
            }
            return;
        }else{
            minTopHeap.remove((long)nums[index]);
            //如果两个堆的大小相差为2，则将较大堆的堆顶元素弹出，保存到另一个堆
            if(maxTopHeap.size()-minTopHeap.size()==2){
                minTopHeap.add(maxTopHeap.poll());
            }else if(minTopHeap.size()-maxTopHeap.size()==2){
                maxTopHeap.add(minTopHeap.poll());
            }
        }
    }
    //重载
    public static double getMid(int []nums, int curIndex){
        //如果下面的数，比大顶堆的堆顶的数小，就放入大顶堆
        if(maxTopHeap.size()==0){
            maxTopHeap.add((long)nums[curIndex]);
        }else{
            if(nums[curIndex]<maxTopHeap.peek()){
                maxTopHeap.add((long)nums[curIndex]);
            }else {//否则，放入小顶堆
                minTopHeap.add((long)nums[curIndex]);
            }
        }
        //如果两个堆的大小相差为2，则将较大堆的堆顶元素弹出，保存到另一个堆
        if(maxTopHeap.size()-minTopHeap.size()==2){
            minTopHeap.add(maxTopHeap.poll());
        }else if(minTopHeap.size()-maxTopHeap.size()==2){
            maxTopHeap.add(minTopHeap.poll());
        }

        //如果最后两个堆的数量相同
        if(maxTopHeap.size()==minTopHeap.size()){
            //注意这里可能会溢出
            return (double)(((double)maxTopHeap.peek()+(double)minTopHeap.peek())/2.0);
        }else if(maxTopHeap.size()>minTopHeap.size()){
            return (double)maxTopHeap.peek();
        }else{
            return (double)minTopHeap.peek();
        }
    }

    public static double getMid(int [] nums, int start, int end){
        int temp=start;
        if(maxTopHeap.size()==0){
            maxTopHeap.add((long)nums[temp]);
            temp++;
        }
        while(temp<=end){
            //如果下面的数，比大顶堆的堆顶的数小，就放入大顶堆
            if((long)nums[temp]<maxTopHeap.peek()){
                maxTopHeap.add((long)nums[temp]);
            }else {//否则，放入小顶堆
                minTopHeap.add((long)nums[temp]);
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
            return (double)(((double)maxTopHeap.peek()+(double)minTopHeap.peek())/2.0);
        }else if(maxTopHeap.size()>minTopHeap.size()){
            return (double)maxTopHeap.peek();
        }else{
            return (double)minTopHeap.peek();
        }
    }

    public static void main(String[] args) {
        int [] nums={-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        double[] doubles = medianSlidingWindow(nums, 3);
        System.out.println(Long.MAX_VALUE);
    }

}
