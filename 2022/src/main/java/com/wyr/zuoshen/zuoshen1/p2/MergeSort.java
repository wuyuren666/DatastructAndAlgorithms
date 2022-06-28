package com.wyr.zuoshen.zuoshen1.p2;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr=new int[]{3,0,1,5,6,7,1,3,10};
        process518(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    //5.18练习
    public static void process518(int [] arr, int L, int R){
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        process518(arr,L,mid);
        process518(arr,mid+1,R);
        merge518(arr,L,mid,R);
    }

    private static void merge518(int[] arr, int l, int mid, int r) {
        int [] helper=new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=mid+1;
        while (p1<=mid&&p2<=r){
            helper[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }

        while (p1<=mid){
            helper[i++]=arr[p1++];
        }
        while (p2<=r){
            helper[i++]=arr[p2++];
        }
        for(i=0;i<helper.length;i++){
            arr[l+i]=helper[i];
        }
    }










    //5.10练习
    public static void process510(int []arr, int L, int R){
        if(L==R)
            return;
        int mid=L+((R-L)>>1);
        process510(arr, L, mid);
        process510(arr, mid+1,R);
        merge510(arr, L ,mid , R);
    }

    public static void merge510(int [] arr, int L, int mid, int R){
        int[] helper=new int[R-L+1];
        int p1= L;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=R){
            helper[i++]=arr[p1]>=arr[p2]?arr[p1++]:arr[p2++]; //从大到小
        }
        while (p1<=mid){
            helper[i++]=arr[p1++];
        }
        while (p2<=R){
            helper[i++]=arr[p2++];
        }

        for (i=0;i<helper.length;i++){
            arr[L+i]=helper[i];
        }
    }




    //5.5练习
    public static void process55(int[] arr,int L, int R){
        if(L==R)
            return;
        int mid=L+((R-L)>>1);
        process55(arr,L,mid);
        process55(arr,mid+1,R);
        merge55(arr, L,mid,R);
    }

    private static void merge55(int[] arr, int L, int mid, int R) {
        int[] helper=new int[R-L+1];
        int p1=L;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=R){
            helper[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            helper[i++]=arr[p1++];
        }
        while (p2<=R){
            helper[i++]=arr[p2++];
        }
        for (i=0;i<helper.length;i++){
            arr[L+i]=helper[i];
        }
    }


    /**
     * 4.26练习
     * @param arr
     * @param L
     * @param R
     */
    /*public static void pro(int[] arr,int L,int R){
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        process(arr,L,mid);
        process(arr,mid+1,R);
        mer(arr,L,mid,R);
    }

    public static void mer(int [] arr, int L,int mid, int R){
        int [] helper=new int[R-L+1];
        int p1=L;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=R){
            helper[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];//左边有序的是否小于等于右边的有序序列，等于的时候，我们以右边的为准
        }
        while (p1<=mid){
            helper[i++]=arr[p1++];
        }
        while (p2<=R){
            helper[i++]=arr[p2++];
        }
        for (i = 0;  i< helper.length; i++) {
            arr[L+i]=helper[i];
        }
    }*/


    //4.30练习
    public static void process430(int arr[],int L,int R){
        if(L==R)
            return;
        int mid=L+((R-L)>>1);
        process430(arr,L,mid);
        process430(arr,mid+1,R);
        merge430(arr, L,mid, R);
    }
    private static void merge430(int[] arr, int l, int mid, int r) {
        int [] helper=new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=r){
            helper[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            helper[i++]=arr[p1++];
        }
        while (p2<=r){
            helper[i++]=arr[p2++];
        }
        for (i = 0;  i< helper.length; i++) {
            arr[l+i]=helper[i];
        }
    }










    //4.28练习
    public static void pro428(int [] arr, int L,int R){
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge428(arr,L,mid,R);
    }
    public static void merge428(int [] arr, int L, int mid,int R){
        int [] helper=new int[R-L+1];
        int p1=L;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=R){
            helper[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }

        while (p1<=mid){
            helper[i++]=arr[p1++];
        }

        while (p2<=R){
            helper[i++]=arr[p2++];
        }

        for ( i = 0; i < helper.length; i++) {
            arr[L+i]=helper[i];
        }

    }




    /**
     * 归并排序归的过程
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int [] arr, int L,int R){
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);//中间下标
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }


    /**
     * 归并排序的并的过程
     * @param arr
     * @param L
     * @param M
     * @param R
     */
    private static void merge(int[] arr, int L, int M, int R) {
        int [] help=new int[R-L+1];//辅助数组
        int i=0;
        int p1=L;//指向左组有序的数组的第一个数组元素
        int p2=M+1;//指向右组有序数组的第一个数组元素
        while (p1<=M&&p2<=R){//这个while的意思是只要两边都没越界，我们将较小的保存在help中
            help[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];//如果p1指向的等于p2指向的，默认拷贝左边的。
        }
        while (p1<=M){//p2首先越界了,将左边的剩下的保存到help中
            help[i++]=arr[p1++];
        }
        while (p2<=R){//p1首先越界了,将右边的剩下的保存到help中
            help[i++]=arr[p2++];
        }
        for (i=0; i < help.length; i++) {
            arr[L+i]=help[i];
        }
    }




    public static void process427(int []arr,int L,int R){
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        process427(arr,L,mid);
        process427(arr,mid+1,R);
        merge427(arr,L,mid,R);
    }

    public static void merge427(int[] arr, int L ,int mid, int R) {
        int [] helper=new int[R-L+1];
        int p1=L;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=R){
            helper[i++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }

        while (p1<=mid){
            helper[i++]=arr[p1++];
        }

        while (p2<=R){
            helper[i++]=arr[p2++];
        }

        for (i = 0;  i< helper.length; i++) {
            arr[L+i]=helper[i];
        }

    }








}
