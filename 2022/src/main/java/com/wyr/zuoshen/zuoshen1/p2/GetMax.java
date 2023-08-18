package com.wyr.zuoshen.zuoshen1.p2;

public class GetMax {
    public static void main(String[] args) {
        int[] arr=new int[]{3,0,1,5,6,7,1,3,10};
        System.out.println(process518(arr, 0, arr.length - 1));
    }


    //5.18
    public static int process518(int[] arr, int L, int R){
        if(R==L){
            return arr[L];
        }
        int mid=L+((R-L)>>1);
        int p1=process510(arr,L,mid);
        int p2=process510(arr,mid+1,R);
        return Math.max(p1,p2);
    }






    public static int process510(int [] arr, int L, int R){
        if(L==R){
            return arr[L];
        }

        int mid=L+((R-L)>>1);
        int leftMax=process510(arr, L,mid);
        int rightMax=process510(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }









    //5.5练习
    public static int process55(int[] arr,int L,int R){
        if(L==R){
            return arr[L];
        }
        int mid=L+((R-L)>>1);
        int leftMax=process55(arr,L,mid);
        int rightMax=process55(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }

    //4.30练习
    public static int process430(int [] arr, int L, int R){
        if(L==R){
            return arr[L];
        }
        int mid=L+((R-L)>>1);
        int leftMax=process430(arr,L,mid);
        int rightMax=process430(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }





    /**
     *  找出数组中最大的数，要求时间复杂度：O(logN)
     *  [2,3,1,0,5]
     */
    public int getM(int [] arr, int left, int right){
        //baseCase
        if(right==left){
            return arr[left];
        }
        int mid = left+((right-left)>>1); //(left+right)/2
        int leftMax=getM(arr,left,mid);
        int rightMax=getM(arr,mid+1,right);
        return Math.max(leftMax,rightMax);
    }



















    //arr[L...R]范围求最大值，求数组最大值
    private static int process(int [] arr,int L,int R){
        if(L==R){//arr[L...R]范围上只有一个数，直接返回
            return arr[L];
        }
        int mid=L+((R-L)>>1);//中点，防止使用mid=(L+R)/2的时候L+R溢出，我们可以这样写mid=L+(R-L)/2==>mid=L+((R-L)>>1)使用位运算
        int leftMax=process(arr,L,mid);
        int rightMax=process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }
}
