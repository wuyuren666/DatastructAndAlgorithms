package com.wyr.zuoshen.zuoshen2.p49;

public class Code1 {
    /**
     * 给定一个有序数组arr，代表坐落在X轴上的点
     * 给定一个正数k，代表绳子的长度
     * 返回绳子最多能压住几个点
     * 即使绳子边缘处盖住点也算盖住
     */
    //二分法，时间复杂度O(NlogN)
    public static int maxPoint1(int [] arr, int L){
        int res=1;
        for(int i=0;i<arr.length;i++){
            int nearest=nearestIndex(arr,i,arr[i]-L);
            res=Math.max(res,i-nearest+1);
        }
        return res;
    }
    public  static int  nearestIndex(int [] arr, int R, int value){
        int L=0;
        int index=R;
        while(L<=R){
            int mid=L+((R-L)>>1);
            if(arr[mid]>=value){
                index=mid;
                R=mid-1;
            }else{
                L=mid+1;
            }
        }
        return index;
    }

    //滑动窗口的思想 O(N) ，类似于双指针
    public static int maxPoint2(int []arr, int L){
        int left=0;
        int right=0;
        int N=arr.length;
        int max=0;
        while(left<N){
            while (right<N && arr[right]-arr[left]<=L){
                right++;
            }
            max=Math.max(max, right-(left++));
        }
        return max;
    }
}
