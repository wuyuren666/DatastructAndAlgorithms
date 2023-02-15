package com.wyr.zuoshen.zuoshen2.p17;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.TreeMap;


public class Code1 {

    /**
     * 二分查找有序数组中是否有某个数
     * https://leetcode.cn/problems/binary-search/
     */
    //arr保证有序
    public static boolean binaryFind(int[] arr,int aim) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0; //左下标
        int R = arr.length - 1; //右下标
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == aim) {
                return true;
            } else if (aim > arr[mid]) {
                L = mid + 1;//左下标移动
            } else {
                R = mid - 1;
            }
        }
        return false;
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * 有序数组中找到>=aim 的最左位置
     * 二分法O(logN)或者直接遍历O(N) 注意我们常说的就是最坏时间复杂度
     * 当然二分法更好
     */
    public static int mostLeftNoLessIndex(int [] arr, int aim){
        if(arr==null||arr.length==0){
            return -1;
        }
        int result=-1;
        int L=0;
        int R=arr.length-1;
        while (L<=R){
            int mid=L+((R-L)>>1);
            if(arr[mid]>=aim){
                result=mid; //结果更新
                R=mid-1;
            }else {//arr[mid]<aim
                L=mid+1;
            }
        }
        return result;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 给一个无序数组，且这个数组中相邻的两个数不想等
     * 返回这个数组中的一个局部最小值的下标
     * 局部最小值： 前一个数>当前数 && 当前数<后一个数
     *
     * 所以二分不一定要有序
     */
    public static int getOneMinIndex(int [] arr){
        if(arr==null||arr.length==0){ //数组不存在
            return -1;
        }
        int N=arr.length;
        if(N==1){ //数组的长度为1，我们就认为这个唯一的一个数是局部最小值
            return 0;
        }

        if(arr[0]<arr[1]){ //左边界情况
            return 0;
        }
        if(arr[N-2]>arr[N-1]){ //右边界情况
            return N-1;
        }

        //普遍情况
        //也就是最左边是下降的趋势，最右边是上升的趋势
        int L=0;
        int R=N-1;
        //int result=-1;
        while (L<R-1){ //最少有三个数的时候，我才进这个while循环
            int mid=L+((R-L)>>1);//中点位置
            //注意这样写，mid-1或者mid+1可能会越界。假如只有两个数，取这个mid就会出现这种情况
            //比如这种情况: 3,2,3,2,3 就会出现越界
            if(arr[mid-1]>arr[mid]&&arr[mid]<arr[mid+1]){ // 大小大
               return mid;
            }
            if(arr[mid-1]<arr[mid]){ //左>我
                R=mid-1;
                continue;
            }
            if(arr[mid]>arr[mid+1]){ //右<我
                L=mid+1;
                continue;
            }
        }
        return arr[L]<arr[R]?L:R;
    }


    public static void main(String[] args) {
        int [] arr={3,2,3,2,3};
        System.out.println(getOneMinIndex(arr));

    }
}
