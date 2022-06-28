package com.wyr.leetcode.step2.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PivotArrayTest {

    /**
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 pivot 。请你将 nums 重新排列，
     * 使得数组呈现 <pivot  ==pivot  >pivot的状态，并且要保持稳定性
     *
     * https://leetcode.cn/problems/partition-array-according-to-given-pivot/
     */

    //此方法的空间复杂为O(N) :因为开辟了一个新的数组
    //时间复杂度为O(N)
    //能保证稳定性
    public static int[] pivotArray(int[] nums, int pivot) {
        int [] result=new int [nums.length];
        int index=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<pivot){
                result[index++]=nums[i];
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]==pivot){
                result[index++]=nums[i];
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]>pivot){
                result[index++]=nums[i];
            }
        }
        return result;
    }



    //index的意思就是以哪个下标的元素值作为标准
    //此方法的时间复杂度为O(logN)
    //空间复杂度为O(1)：（直接在原数组上进行交换）
    //但是保证不了稳定性
    public static void partion(int [] nums, int index){ //index是指以哪个下标的元素值作为标准划分
        //作为标准的值可能有多个
        //将多个作为标准的数组元素的下标保存到集合中
        List<Integer> indexList=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==nums[index]){
                indexList.add(i);
            }
        }

        for(int k=0;k<indexList.size();k++){
            int aimIndex=indexList.get(k); //作为标准的数所在的下标
            int i=0;
            int j=nums.length-1;
            int temp=nums[aimIndex];
            if(aimIndex!=0){
                swap(nums, 0,aimIndex);
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

    }

    public static void swap(int [] nums, int i, int j){
        nums[i]=nums[i]^nums[j];
        nums[j]=nums[i]^nums[j];
        nums[i]=nums[i]^nums[j];
    }



    public static void main(String[] args) {

        int [] a={5,1,6,8,3,5,5,2,3};
        partion(a,0);
        for (int i : a) {
            System.out.print(i);
        }

        System.out.println();
        int[] b={5,1,6,8,3,5,5,2,3};
        b = pivotArray(b,5);
        for (int i : b) {
            System.out.print(i);
        }

    }

}
