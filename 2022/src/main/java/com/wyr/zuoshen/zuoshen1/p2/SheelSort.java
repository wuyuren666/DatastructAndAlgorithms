package com.wyr.zuoshen.zuoshen1.p2;

public class SheelSort {
    /**
     * 希尔排序是插入排序的升级
     * 可以采用某些技巧对数组进行预处理，聪明的科学家想到了一种分组排序的方法，以此对数组进行一定的预处理
     * 所谓分组，就是让元素两两一组，同组两个元素之间的跨度，是数组总长度的一半
     * 如果数组的长度是8，那么分组的跨度就是4，2，1
     */

    public static void sheelSort(int [] array){
        //希尔排序的增量
        int d=array.length;
        while (d>1){
            //使用希尔增量的方式，即每次折半
            d=d/2;
            for(int x=0;x<d;x++){ //分的组
                for(int i=x+d;i<array.length;i+=d){ //i代表要插入的数的下标
                    //里面的逻辑其实就是优化后的插入排序的逻辑
                    int temp=array[i];
                    int j=i-d;
                    for(;j>=0&&temp<array[j];j-=d){
                        array[j+d]=temp;
                    }
                    array[j+d]=temp;
                }
            }
        }
    }
}
