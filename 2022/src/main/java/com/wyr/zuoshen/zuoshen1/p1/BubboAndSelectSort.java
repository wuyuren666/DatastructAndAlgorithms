package com.wyr.zuoshen.zuoshen1.p1;

public class BubboAndSelectSort {
    public static void main(String[] args) {
        int[] arr=new int[]{3,0,1,5,6,7,1,3,10};
        //bubboSort518(arr);
        selectSort518(arr);
        for (int i : arr) {
            System.out.println(i);
        }

    }

    //5.18练习
    public static void bubboSort518(int[] arr){
        boolean flag=false;
        for(int i=0;i<arr.length-1;i++){
            flag=true;
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j+1]<arr[j]){
                    flag=false;
                    swap(arr,j+1,j);
                }
            }
            if(flag==true)
                break;
        }
    }

    //5.18练习
    public static void selectSort518(int [] arr){
        for(int i=0;i<arr.length-1;i++){
            int k=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[k])
                    k=j;
            }
            if(k!=i)
                swap(arr,k,i);
        }
    }





    //5.10冒泡
    public static void bubboSort510(int [] arr){
        for (int i=0;i<arr.length-1;i++){//代表趟数
            boolean flag=false;
            for (int j=0;j<arr.length-1-i;j++){ //代表排序的次数
                 if(arr[j]>arr[j+1]){ //从小到大
                     swap(arr,j,j+1);
                     flag=true;
                 }
            }
            if(!flag)
                break;
        }
    }










    //5.5冒泡
    public static void bubboSort55(int[] arr){
        boolean flag;
        for (int i=0;i<arr.length-1;i++){ //代表趟数
            flag=true;
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    flag=false;
                }
            }
            if(flag)
                break;
        }
    }

    //5.10选择
    public static void selectSort510(int[] arr){
        for (int i=0;i<arr.length-1;i++){ //代表我要在i下标的位置选出一个最小的数放在那
            int k=i;
            for(int j=i+1;j<arr.length;j++)
                if(arr[j]<arr[k])
                    k=j;//使用k保存i+1~arr.length-1最小数的下标

            if(k!=i)
                swap(arr,k,i);
        }
    }








    //5.5选择
    public static void selectSort55(int [] arr){
        for (int i=0;i<arr.length-1;i++){
            int k=i;
            for (int j=i+1;j<arr.length;j++)
            {
                if(arr[j]<arr[k]){
                    k=j;//k保存最大数的下标
                }
            }
            if(k!=i){
                swap(arr,k,i);
            }
        }
    }











    //冒泡排序4.30练习
    public static void bubboSort430(int [] arr){
        boolean flag;//判断是在一趟中是否进行了互换
        for(int i=0;i<arr.length-1;i++){
            flag=true;
            for (int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    flag=false;
                }
            }
            if(flag)
                break;
        }
    }


    //选择排序4.30练习
    public static void selectSort430(int arr[]){
        for (int i=0;i<arr.length-1;i++){
            int k=i;
            for (int j=i+1;j<arr.length;j++)
                if (arr[j]<arr[k])//从小到大
                    k=j;
            if(k!=i){
                swap(arr,i,k);
            }
        }
    }










    //冒泡排序
    public static void bubboSort(int [] a){

        for (int i=0;i<a.length-1;i++){
            boolean flag=false;
            for (int j=0;j<a.length-1-i;j++){
                if(a[j]<a[j+1]){
                    swap(a,j,j+1);
                    flag=true;
                }
            }
            if(!flag){
                break;
            }
        }
    }
    //选择排序
    public static void selectSort(int [] a){
        for (int i=0;i<a.length-1;i++){
            int k=i;
            for (int j=i+1;j<a.length;j++){
                if(a[k]<a[j]) k=j;//k应该为最大值的下标
            }
            if(k!=i)
                swap(a,i,k);
        }
    }


    /**
     * 规则：a^a=0; a^0=a ,有交换律和结合律
     * 开始：a=甲，b=乙
     * a=甲^乙   b=乙
     * b=乙^甲^乙==>乙^乙^甲==>0^甲==>甲
     * a=甲^乙^甲==>甲^甲^乙==>0^乙==>乙
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[]arr,int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
}
