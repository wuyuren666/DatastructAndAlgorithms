package com.wyr.zuoshen.zuoshen2.p12;

/**
 * 前缀和数组
 *
 * 假设我们有一个数组，我们很频繁的想去求的数组下标L...R的这些数的和
 */
public class RangeSum {

    public int rangeSum(int []arr, int L, int R){
        int N=arr.length;
        int [] preSum=new int[N];
        preSum[0]=arr[0];
        for(int i=1;i<N;i++){
            preSum[i]=preSum[i-1]+arr[i];
        }
        return L==0?preSum[R]:preSum[R]-preSum[L-1];
    }
}
