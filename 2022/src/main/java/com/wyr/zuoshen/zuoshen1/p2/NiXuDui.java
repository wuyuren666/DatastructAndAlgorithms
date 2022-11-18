package com.wyr.zuoshen.zuoshen1.p2;

import java.util.Objects;

public class NiXuDui {
    /**
     * 逆序对问题：使用归并排序
     * 一个数组中，左边的数比右边的数大，则这两个数构成一个逆序对，请打印所有的逆序对，或者找出有多少对逆序对
     * [3,2,4,5,0]
     * [3,2],[3,0],[2,0],[4,0],[5,0]
     * @param args
     */
    public static void main(String[] args) {
        int arr[]={3,2,4,5,0,2,4,0,1};
        //processPrint(arr, 0, arr.length - 1);
       // System.out.println(process55(arr, 0, arr.length - 1));
       System.out.println(process510(arr,0,arr.length-1));
        //System.out.println(process11_17(arr,0,arr.length-1));
    }



    public static int process11_17(int [] nums, int left, int right){
        if(left==right){
            return 0;
        }
        int mid = left+((right-left)>>1);
        return process11_17(nums,left,mid)+process11_17(nums,mid+1,right)+merge11_17(nums,left,mid,right);
    }

    public static int merge11_17(int[] nums, int left, int mid, int right) {
        int [] help = new int[right-left+1];
        int p1=left;
        int p2=mid+1;
        int i=0;
        int res=0;
        while(p1<=mid&&p2<=right){
            if(nums[p1]>nums[p2]){
                for(int j=1;j<=right-p2+1;j++)
                    res++;
            }
            help[i++]=nums[p1]>nums[p2]?nums[p1++]:nums[p2++];
        }

        while (p1<=mid){
            help[i++]=nums[p1++];
        }
        while (p2<=right){
            help[i++]=nums[p2++];
        }
        for(i=0;i<help.length;i++) {
            nums[left+i]=help[i];
        }
        return res;
    }


    //5.10练习
    public static int process510(int []arr, int L, int R){
        if(L==R)
            return 0;
        int mid=L+((R-L)>>1);
        return process510(arr, L, mid)+ process510(arr, mid+1,R)+merge510(arr, L ,mid , R);
    }

    public static int merge510(int [] arr, int L, int mid, int R){
        int[] helper=new int[R-L+1];
        int p1= L;
        int p2=mid+1;
        int i=0;
        int result=0;
        while (p1<=mid&&p2<=R){
            if(arr[p1]>arr[p2]){
                for(int j=0;j<R-p2+1;j++){
                    result++;
                }
            }
            helper[i++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++]; //从大到小
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
        return result;
    }






    //5.5练习
    public static int process55(int [] arr, int L,int R){
        if(L==R){
            return 0;
        }
        int mid=L+((R-L)>>1);
        return process55(arr,L,mid)+ process55(arr,mid+1,R)+merge55(arr,L,mid,R);


    }

    private static int merge55(int[] arr, int l, int mid, int r) {
        int [] helper=new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int result=0;
        int i=0;
        while (p1<=mid&&p2<=r){
            if(arr[p1]>arr[p2]){
                for(int j=0;j<r-p2+1;j++)
                    result++;
            }
            helper[i++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++];
        }

        while (p1<=mid){
            helper[i++]=arr[p1++];
        }
        while (p2<=r){
            helper[i++]=arr[p2++];
        }
        for (i=0;i<helper.length;i++)
            arr[l+i]=helper[i];
        return result;
    }







    /**
     * 4.26练习
     * @param arr
     * @param L
     * @param R
     */
    public static void process426(int [] arr, int L, int R){
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        process426(arr,L,mid);
        process426(arr,mid+1,R);
        merge426(arr,L,mid,R);
    }

    public static void merge426(int [] arr, int L, int mid ,int R){
        int helper []=new int[R-L+1];
        int p1=L;
        int p2=mid+1;
        int i=0;
        while (p1<=mid&&p2<=R){
            if(arr[p1]>arr[p2])
            {
                for(int j=0;j<R-p2+1;j++)
                    System.out.println("["+arr[p1]+","+arr[p2+j]+"]");
            }
            helper[i++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++];
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


    //4.30练习
    public static int process430(int arr[],int L,int R){
        if(L==R)
            return 0;
        int mid=L+((R-L)>>1);
        return process430(arr,L,mid)+process430(arr,mid+1,R)+merge430(arr, L,mid, R);
    }

    private static int merge430(int[] arr, int l, int mid, int r) {
        int [] helper=new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int i=0;
        int res=0;
        while (p1<=mid&&p2<=r){
            if(arr[p1]>arr[p2]){
                for(int j=0;j<r-p2+1;j++){
                    res++;
                }
            }
            helper[i++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++];
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
        return res;
    }









    public static void processPrint(int [] arr,int L, int R){
        if(L==R){
            return;
        }
        int mid=L+((R-L)>>1);
        processPrint(arr,L,mid);
        processPrint(arr,mid+1,R);
        mergePrint(arr,L,mid,R);

    }

    /**
     * 这是打印出来
     * @param arr
     * @param L
     * @param mid
     * @param R
     */
    public static void mergePrint(int [] arr,int L,int mid,int R){
        int helper[]=new int[R-L+1];
        int i=0;
        int p1=L;
        int p2=mid+1;
        while (p1<=mid&&p2<=R){
            if(arr[p2]<arr[p1]){
                for (int j=0;j<R-p2+1;j++)
                System.out.println("["+arr[p1]+","+arr[p2+j]+"]");
            }
            helper[i++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++];
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

    public static int processCount(int [] arr,int L, int R){
        if(L==R){
            return 0;
        }
        int mid=L+((R-L)>>1);

        return  processCount(arr,L,mid)+processCount(arr,mid+1,R)+ mergeCount(arr,L,mid,R);
    }


    /**
     * 这是计算出个数出来
     * @param arr
     * @param L
     * @param mid
     * @param R
     */
    public static int mergeCount(int [] arr,int L,int mid,int R){
        int helper[]=new int[R-L+1];
        int i=0;
        int p1=L;
        int p2=mid+1;
        int result=0;
        while (p1<=mid&&p2<=R){
            if(arr[p1]>arr[p2]){
                for (int j=0;j<R-p2+1;j++)
                    result++;
            }
            helper[i++]=arr[p1]>arr[p2]?arr[p1++]:arr[p2++];
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
        return result;
    }
}
