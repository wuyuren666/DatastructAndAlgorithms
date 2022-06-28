package com.wyr.zuoshen.zuoshen1.p2;

public class SmallSumQuestion {
    /**
     * 小和问题：使用归并排序
     * [1,3,5,4,7]
     * 1左边没有比他小的，记为0
     * 3左边1比他小，记为1
     * 5左边1，3比他小，记为1+3=4
     * 4左边1，3比他小，记为4
     * 7左边1，3，5，4比他小，记为13
     * 所以这个数组的小和为0+1+4+4+13=22
     *
     * 当然也可以从右边这样看
     * 1右边3，5，4，7比他大，记为4个1=4
     * 3右边有5，4，7比他大，记为3个3=9
     * 5右边有7比他大，记为1个5=5
     * 4右边7比他大，记为1个4=4
     * 7右边没有比他大，记为0个7=0
     * 所以这个数组的小和为4+9+5+4=22
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 4, 7};
        System.out.println(process510(arr, 0, arr.length - 1));
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
            if(arr[p1]<arr[p2]){
                result+=(R-p2+1)*arr[p1];
            }
            helper[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++]; //从大到小
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
    public static int process55(int [] arr, int L, int R){
        if(R==L)
            return 0;
        int mid=L+((R-L)>>1);
        return process55(arr,L,mid)+process55(arr,mid+1,R)+merge55(arr,L,mid,R);
    }

    private static int merge55(int[] arr, int l, int mid, int r) {
        int[] helper=new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int result=0;
        int i=0;
        while (p1<=mid&&p2<=r){
            if(arr[p1]<arr[p2]){
                result+=(r-p2+1)*arr[p1];
            }
            helper[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
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
        return result;
    }


    //4.30练习
    public static int process430(int arr[],int L,int R){
        if(L==R)
            return 0;
        int mid=L+((R-L)>>1);
        return process430(arr,L,mid)+process430(arr,mid+1,R)+ merge430(arr, L,mid, R);

    }
    private static int merge430(int[] arr, int l, int mid, int r) {
        int [] helper=new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int i=0;
        int res=0;
        while (p1<=mid&&p2<=r){
            if(arr[p2]>arr[p1]){
               res=res+(r-p2+1)*arr[p1];
            }
            helper[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
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









    /**
     * 4.26练习
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int pro(int [] arr, int L, int R){
        if(L==R){
            return 0;
        }
        int mid= L+((R-L)>>1);
        return pro(arr,L,mid)+pro(arr,mid+1,R)+mer(arr,L,mid,R);
    }


    public static int mer(int [] arr,int L,int mid, int R){
        int [] helper=new int[R-L+1];
        int p1=L;
        int p2=mid+1;
        int i=0;
        int result=0;
        while (p1<=mid&&p2<=R){
            if(arr[p2]>arr[p1])
                result+=arr[p1]*(R-p2+1);
            helper[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
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






    /**
     * 计算小和
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int process(int [] arr,int L,int R){
        if(L==R){
            return 0;
        }
        int mid=L+((R-L)>>1);
        return process(arr,L,mid)
                +process(arr,mid+1,R)
                +merge(arr,L,mid,R);

    }

    public static int merge(int []arr ,int L,int mid,int R){
        int [] helper=new int[R-L+1];
        int i=0;
        int p1=L;
        int p2=mid+1;
        int result=0;//记录小和的结果
        while (p1<=mid && p2<=R){
            result += arr[p1]<arr[p2] ? arr[p1]*(R-p2+1) :0;
            helper[i++] = arr[p1]<arr[p2] ? arr[p1++]: arr[p2++];//如果p1指向的等于p2指向的，默认拷贝右边的。
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
