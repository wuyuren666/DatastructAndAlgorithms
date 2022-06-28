package com.wyr.dp.zuoshen.p13_14;

/**
 * 对p7_8_9_10的二次优化
 */
public class Code1 {

    public static int win3(int[] arr){
        int N=arr.length;
        int[][] fmap=new int[N][N];
        int[][] gmap=new int[N][N];
        for(int i=0;i<N;i++){//famp对角线初始化，因为gmap对角线都为0，所以不用去管
            fmap[i][i]=arr[i];
        }
        for(int startCol=1;startCol<N;startCol++){
            int row=0;
            int clo=startCol;
            while(clo<N){
                fmap[row][clo]=Math.max(arr[row]+gmap[row+1][clo],arr[clo]+gmap[row][clo-1]);
                gmap[row][clo]=Math.min(fmap[row+1][clo],fmap[row][clo-1]);
                row++;
                clo++;
            }
        }
        return Math.max(fmap[0][N-1],gmap[0][N-1]);
    }

    public static void main(String[] args) {
        int arr[]=new int[]{12,3,5,1,10};
        System.out.println(win3(arr));
    }
}
