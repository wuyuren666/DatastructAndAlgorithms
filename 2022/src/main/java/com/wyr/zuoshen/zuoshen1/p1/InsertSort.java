package com.wyr.zuoshen.zuoshen1.p1;

import java.util.Arrays;


public class InsertSort {

    public static void main(String[] args) {
        //使用对数器
        int testTime=5000;
        int maxSize=100;
        int maxValue=100;
        boolean successed=true;
        for (int i=0;i<testTime;i++){
            int[] arr1=generateRandomArray(maxSize,maxValue);
            int[] arr2=Arrays.copyOf(arr1,arr1.length);
            insertSort518(arr1);
            Arrays.sort(arr2);
            if(!isequal(arr1,arr2)){
                successed=false;
                break;
            }
        }
        System.out.println(successed?"Nice!":"Fuck!");

    }

    /**
     * 插入排序优化的写法，不需要每一次都交换
     */
    public static void insertSortBetter(int [] arr){
        for(int i=1;i<arr.length;i++){ //i代表要插入的数的下标
            int insertValue=arr[i];
            int j=i-1;//插入的下标
            for(;j>=0&&insertValue<arr[j];j--){
                arr[j+1]=arr[j];
            }
            arr[j+1]=insertValue;
        }
    }


    //5.18练习
    public static void insertSort518(int [] arr){
        for(int i=1;i<arr.length;i++){ //0~i有序
            for(int j=i-1;j>=0&&arr[j+1]<arr[j];j--){
                swap(arr,j+1,j);
            }
        }
    }


    //5.10练习
    public static void insertSort510(int [] arr){
        for (int i=1 ; i < arr.length; i++){ //i需要插入的数的下标
             for (int j=i-1 ; j>=0 && arr[j+1] < arr[j] ; j--)
                 swap(arr,j,j+1);
        }
    }




    //5.5练习
    public static void insertSort55(int[] arr){
        for (int i=1;i<arr.length;i++){ //0~i有序
            for (int j=i-1;j>=0&&arr[j+1]<arr[j];j--){
                swap(arr,j,j+1);
            }
        }
    }




    //4.30练习
    public static void insertSort430(int arr[]){
        for (int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0&&arr[j+1]<arr[j];j--){
                swap(arr,j+1,j);
            }
        }
    }







    //4.28练习
    public static void insertSort428(int [] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0&&arr[j+1]<arr[j];j--){
                swap(arr,j,j+1);
            }
        }
    }

    /**
     * 插入排序
     * @param a
     */
    public static void insertSort(int [] a){
        for (int i=1;i<a.length;i++){
            for (int j=i-1;j>=0&&a[j+1]<a[j];j--){//j就理解为有序数组的最后一个数组元素下标,就是需要换的数，往左边换到不能再换就停
                swap(a,j+1,j);
            }

        }
    }

    /**
     * 使用异或进行两个数的交换
     * @param a
     * @param i
     * @param j
     */
    public static void swap(int [] a,int i,int j){
        a[i]=a[i]^a[j];
        a[j]=a[i]^a[j];
        a[i]=a[i]^a[j];
    }



    /**
     * 生成长度为maxSize随机数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize,int maxValue){
        int [] arr=new int[maxSize];
        for (int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*(maxValue+1))-(int)(Math.random()*maxValue);
        }
        return arr;
    }

    private static boolean isequal(int [] a,int [] b){
        boolean flag=true;
        for (int i = 0; i < a.length; i++) {
            if(a[i]!=b[i])
                flag=false;
        }
        if(flag)
            return true;
        else
            return false;
    }
}
