package com.wyr.zuoshen.zuoshen2.p36;

public class Code1 {
    /**
     * 尽量将数组中奇数放奇数下标上，偶数放偶数下标上
     * 思想：
     *      每一次判断数组最后一个元素是偶数还是奇数，
     *          如果是偶数，就和p1所指向的数组元素交换
     *          如果是奇数，就和p2所指向的数组元素交换
     */
    public static void test(int [] arr){
        int p1=0;
        int p2=1;
        int p3=arr.length-1;
        while(p1<arr.length&&p2<arr.length){
            if((arr[p3]&1)==0){//偶数
                swap(arr,p1,p3);
                p1+=2;
            } else if((arr[p3]&1)!=0){//奇数
                swap(arr,p2,p3);
                p2+=2;
            }
        }
    }

    public static void swap(int[] arr, int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        int[] arr={1,3,6,4,8,5};
        test(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
