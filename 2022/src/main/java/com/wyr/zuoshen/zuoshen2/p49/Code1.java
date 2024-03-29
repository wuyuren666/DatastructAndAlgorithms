package com.wyr.zuoshen.zuoshen2.p49;

public class Code1 {
    /**
     * 给定一个有序数组arr，代表坐落在X轴上的点
     * 给定一个正数k，代表绳子的长度
     * 返回绳子最多能压住几个点
     * 即使绳子边缘处盖住点也算盖住
     */
    //11_18练习
    //使用滑动窗口的最大模版去做，最大模版就是条件不满足，尝试左边界增加。循环出来后，在更新最优
    public static int maxPoint11_18(int [] arr, int K){
        int left =0;
        int right =0;
        int ans=Integer.MIN_VALUE;
        while(right<arr.length){
            while (arr[right]-arr[left]>K){
                //条件不满足，缩小窗口
                left++;
            }
            //条件满足，记录
            ans = Math.max(ans,right-left+1);
            right++;
        }
        return ans;
    }






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


    //使用滑动窗口求最大的模版
    public static int getMaxPoint83(int [] arr, int value){
        int left=0;
        int right=0;
        int ans=Integer.MIN_VALUE;
        while(right<arr.length){
            //当条件不满足时候
            while(arr[right]-arr[left]>value){
                left++;
            }
            //从while循环中出来就是条件满足
            ans=Math.max(ans,right-left+1);
            right++;
        }
        return ans;
    }






    //滑动窗口的思想 O(N) ，类似于双指针，使用滑动窗口的模版
    public static int maxPoint2(int []arr, int L){
        int res=0;
        int left=0,right=0;

        while (right<arr.length){

            while (arr[right]-arr[left]>L){ //不满足条件
                left++;
            }
            res=Math.max(res,right-left+1); //更新结果
            right++;
        }
        return res;
    }






    //滑动窗口的思想 O(N) ，类似于双指针
    public static int maxPoint3(int []arr, int L){
        int res=0;
        int left=0,right=0;
        while(left<arr.length) {
            while (right < arr.length && arr[right] - arr[left] <= L) {
                right++;
            }
            res=Math.max(res,right-(left++));
        }
        return res;

    }






    public static void main(String[] args) {
        int [] arr={1,4,7,11,12,14,19,20,22,30};
        System.out.println(maxPoint11_18(arr,20));
        System.out.println(maxPoint1(arr, 20));
        // System.out.println(getMaxPoint83(arr,15));
    }

}
