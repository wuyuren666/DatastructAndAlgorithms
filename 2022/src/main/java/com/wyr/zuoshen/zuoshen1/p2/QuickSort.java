package com.wyr.zuoshen.zuoshen1.p2;

import java.util.Arrays;

public class QuickSort {


    /**
     * 快速排序的最坏时间复杂度为O(N^2)
     * 但是如果我们每一次在数组中选取一个随机值，放在数组的最后，或者最前面作为标杆
     * 此时的平均复杂度为O(N*logN)
     *
     * @param
     */
    public static void main(String[] args) throws InterruptedException {
        /*int testTime=10000;
        int maxSize=100;
        int maxValue=100;
        boolean successed=true;
        for (int i=0;i<testTime;i++){
            int[] arr1=generateRandomArray(maxSize,maxValue);
            int[] arr2= Arrays.copyOf(arr1,arr1.length);
            quickSort518(arr1,0,arr1.length-1);
            Arrays.sort(arr2);
            if(!isequal(arr1,arr2)){
                successed=false;
                break;
            }
        }*/

        int arr[]={46,79,56,38,40,84};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }

    }


    //5.18练习
    public static void quickSort518(int[] arr,int L, int R){
        if(L<R){
            int i=L;
            int j=R;
            int temp=arr[i];
            while (i<j){
                while (i<j&&arr[j]>temp){
                    j--;
                }
                if(i<j){
                    arr[i++]=arr[j];
                }
                while(i<j&&arr[i]<temp){
                    i++;
                }
                if(i<j){
                    arr[j--]=arr[i];
                }
            }
            arr[i]=temp;
            quickSort518(arr,L,i-1);
            quickSort518(arr,i+1,R);
        }
    }





    //5.10练习
    public static void quickSort510(int [] arr, int left, int right){
        if(left<right){
            int i=left;
            int j=right;
            int temp=arr[i];
            while (i<j){
                while (i<j&&arr[j]>temp){
                    j--;
                }
                if(i<j){
                    arr[i++]=arr[j];
                }
                while (i<j&&arr[i]<temp){
                    i++;
                }
                if(i<j){
                    arr[j--]=arr[i];
                }
            }
            arr[i]=temp;
            quickSort510(arr,left,i-1);
            quickSort510(arr,i+1,right);
        }
    }








    //5.5练习
    public static void quickSort55(int arr[], int left, int right){
        if(left<right){
            int i=left;
            int j=right;
            int temp=arr[i];
            while (i<j){
                while (i<j&&arr[j]>temp){
                    j--;
                }
                if(i<j){
                    arr[i++]=arr[j];
                }
                while (i<j&&arr[i]<temp){
                    i++;
                }
                if(i<j){
                    arr[j--]=arr[i];
                }
            }
            arr[i]=temp;
            quickSort55(arr,left,i-1);
            quickSort(arr,i+1,right);
        }

    }







    //4.30练习
    public static void quickSort430(int [] arr, int left, int right){
        if(left<right){
            int i=left;
            int j=right;
            int temp=arr[i];
            while (i<j){
                while (i<j&&arr[j]>temp){
                    j--;
                }
                if(i<j){
                    arr[i++]=arr[j];
                }
                while (i<j&&arr[i]<temp){
                    i++;
                }
                if(i<j){
                    arr[j--]=arr[i];
                }
            }
            arr[i]=temp;
            quickSort430(arr,left,i-1);
            quickSort(arr,i+1,right);
        }
    }



    //4.26练习
    public static void quickSort426(int arr[], int left,int right){
        if(left<right){
            int i=left;
            int j=right;
            int temp=arr[i];
            while (i<j){
                while (i<j&&arr[j]>temp){
                    j--;
                }
                if(i<j){
                    arr[i++]=arr[j];
                }
                while (i<j&&arr[i]<temp){
                    i++;
                }
                if(i<j){
                    arr[j--]=arr[i];
                }
            }
            arr[i]=temp;
            quickSort426(arr,left,i-1);
            quickSort426(arr,i+1,right);
        }
    }





    public static void quickSort(int [] arr,int left,int right){
        if (left<right){
            //swap(arr,(left+(int)(Math.random()*(right-left+1))),left);//改进,还是有点问题
            int i=left;
            int j=right;
            int temp=arr[i];
            while (i<j){
                while (i<j&&arr[j]>temp){
                    j--;
                }
                if (i<j){
                    arr[i++]=arr[j];
                }
                while (i<j&&arr[i]<temp){
                    i++;
                }
                if (i<j){
                    arr[j--]=arr[i];
                }
            }
            arr[i]=temp;
            quickSort(arr,left,i-1);
            quickSort(arr,i+1,right);
        }
    }



    public static void swap(int [] arr, int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }



    /**
     * 生成长度为maxSize随机数组
     * @param maxSize
     * @param maxValue
     * @return
     */
        public static int[] generateRandomArray ( int maxSize, int maxValue){
            int[] arr = new int[maxSize];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
            }
            return arr;
        }

        private static boolean isequal ( int[] a, int[] b){
            boolean flag = true;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i])
                    flag = false;
            }
            if (flag)
                return true;
            else
                return false;
        }

}
