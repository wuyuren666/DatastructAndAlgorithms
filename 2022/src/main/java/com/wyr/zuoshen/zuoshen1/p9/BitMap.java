package com.wyr.zuoshen.zuoshen1.p9;

public class BitMap {
    public static void main(String[] args) {
        int[] arr=new int [2]; //32bit*2->64 bits
        //arr[0]  0~31
        //arr[1]  32~63

        int i= 3;//想要取得下标为3的bit的状态

        int numIndex=3/32; //获得第三个bit所在数组的下标
        int bitIndex=3%32; //获得第三个比特在a[numIndex]中的位置，从0位置开始


        //拿到下标为3的状态
        //或者 s=(1<<bitIndex)&arr[numIndex]
        int s=((arr[numIndex]>>(bitIndex))&1);

        //将下标为3的状态改为1
        arr[numIndex]=arr[numIndex]|(1<<(bitIndex));


        //将下标为3的状态改为0
        arr[numIndex]=arr[numIndex] & (~(1<<bitIndex));

    }
}
