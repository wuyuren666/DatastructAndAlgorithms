package com.wyr.zuoshen.zuoshen2.p35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code2 {
    /**
     * 给定整数数组 nums (无序)和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     *
     * https://leetcode.cn/problems/xx4gT2/
     *
     * 这题，要是先排序，在找出第k个较大的数，时间复杂度O(N*logN)
     * 要求使用的方法时间复杂度为O(N)
     */

    public static int findKthLargest(int[] nums, int k) {
        //第k大其实就是顺序排完序后下标为nums.length-k的位置
        return process(nums,0,nums.length-1,nums.length-k);
    }

    public static int process(int [] nums, int L, int R, int index){
        if(L==R){ //言外之意就是L==R==index
            return nums[L];
        }
        //产生0~R-L之间的随机数
        int randomIndex=L+(int)(Math.random()*(R-L+1));
        int [] range=partion1(nums,L,R,nums[randomIndex]);

        if(index>=range[0]&&index<=range[1]){
            return nums[range[0]];
        }else if(index<range[0]){
            return process(nums,L,range[0]-1,index);
        }else{
            return process(nums,range[1]+1,R,index);
        }
    }

    //荷兰国旗问题
    //最终返回一个只有两个元素的数组
    //两个值分别代表nums中==value的数它所在下标的范围
    //也就是说，我们将数组分为三部分 <value ==value >value
    //这是开辟一个新数组，然后挨个复制的方法
    public static int[] partion1(int [] nums,int L,int R, int value){

        int [] temp=new int[nums.length];
        for(int i=0;i<L;i++){
            temp[i]=nums[i];
        }
        for(int i=R+1;i<nums.length;i++){
            temp[i]=nums[i];
        }
        int index=L;
        for(int i=L;i<=R;i++){
            if(nums[i]<value){
                temp[index++]=nums[i];
            }
        }
        boolean flag=false;
        int beginIndex=-1;
        int count=0;
        for(int i=L;i<=R;i++){
            if(nums[i]==value&&!flag){
                beginIndex=index;
                temp[index++]=nums[i];
                count++;
                flag=true;
                continue;
            }
            if(nums[i]==value){
                temp[index++]=nums[i];
                count++;
            }
        }
        for(int i=L;i<=R;i++){
            if(nums[i]>value){
                temp[index++]=nums[i];
            }
        }
        //这里不能这样搞
        //nums=temp
        //为什么呢？因为每一个方法中的nums都是一个引用指向堆中的同一块内存区域
        //在这里，nums=temp，只是将当前方法中的这个nums引用指向其他对象
        //而其他方法中的nums引用指向是没有改变的
        /**
         * 这里的原因是，在主调函数中，我们的nums其实也需要获得被partion后的数组
         */
        for(int i=0;i<nums.length;i++){
            nums[i]=temp[i];
        }

        int [] result=new int[2];
        result[0]=beginIndex;
        result[1]=beginIndex+count-1;
        return result;
    }

    //荷兰国旗问题
    //此方法的时间复杂度为O(logN)
    //空间复杂度为O(1)：（直接在原数组上进行交换）
    //但是保证不了稳定性
    //注意这个方法partion2是有问题的，
    // 对于那些特殊的数组，比如数字都一样；一个数字出现次数很多，另一个数字出现次数很少 [3,3,3,3,4,3,3,3,3,3]
    //原因就在于随机取下标，不一定能取到4这个值的下标
    //所以在leetCode上过不了，只能使用第一种方法
    public static int[] partion2(int [] nums,int L, int R, int value){ //value是指以元素的值作为标准划分
        //作为标准的值可能有多个
        //将多个作为标准的数组元素的下标保存到集合中
        List<Integer> indexList=new ArrayList<>();
        for(int i=L;i<=R;i++){
            if(nums[i]==value){
                indexList.add(i);
            }
        }

        for(int k=0;k<indexList.size();k++){
            int aimIndex=indexList.get(k); //作为标准的数所在的下标
            int i=L;
            int j=R;
            int temp=nums[aimIndex];
            if(aimIndex!=L){
                swap(nums,L,aimIndex);
            }
            while(i<j){
                while(i<j&&nums[j]>=temp){
                    j--;
                }
                if(i<j){
                    nums[i++]=nums[j];
                }
                while(i<j&&nums[i]<=temp){
                    i++;
                }
                if(i<j){
                    nums[j--]=nums[i];
                }
            }
            nums[i]=temp;
        }

        int [] result=new int[2];
        if(indexList.size()==1){
            for(int i=0;i<nums.length;i++){
                if(value==nums[i]){
                    result[0]=i;
                    result[1]=i;
                    break;
                }
            }
        }else{
            int beginIndex=0;
            for(;beginIndex<nums.length;beginIndex++){
                if(value==nums[beginIndex]){
                    break;
                }
            }
            result[0]=beginIndex;
            result[1]=beginIndex+indexList.size()-1;
        }
        return result;
    }



    public static void swap(int [] nums, int i, int j){
        nums[i]=nums[i]^nums[j];
        nums[j]=nums[i]^nums[j];
        nums[i]=nums[i]^nums[j];
    }





    public static void main(String[] args) {
        int a[]={3,2,1,5,6,4};

       // int[] ints = partion1(a, 1, 3, 5);
        //System.out.println("aaaa");

       System.out.println(findKthLargest(a, 2));
       /* partion1(a,0,a.length-1,3);
        for (int i : a) {
            System.out.print(i);
        }*/
    }
}
