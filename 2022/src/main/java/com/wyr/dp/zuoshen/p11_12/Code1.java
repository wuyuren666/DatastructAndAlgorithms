package com.wyr.dp.zuoshen.p11_12;

/**
 * 对p_7_8_9_10的第一次优化
 */
public class Code1 {


    //以先手姿态拿牌，所能获得的最佳分数
    public static int f2(int [] arr, int L, int R,int [][] fmap,int [][] gmap){
        if(fmap[L][R]!=-1){  //先查缓存，缓存中有就直接返回
            return fmap[L][R];
        }
        int res=0;
        if(L==R){ //basecase,因为就剩一张牌，又是以先手姿态拿牌，所以必然会将这张牌拿走
            res= arr[L];
        }else {
            int p1=arr[L]+g2(arr,L+1,R,fmap,gmap);//g为后手方法，也就是说，我以先手姿态拿走了最左边的那张牌，在[L+1,R]上，就是以后手的姿态去拿牌
            int p2=arr[R]+g2(arr,L,R-1,fmap,gmap);//g为后手方法，也就是说，我以先手姿态拿走了最右边的那张牌，在[L,R-1]上，就是以后手的姿态去拿牌
            res =Math.max(p1,p2);
        }
        fmap[L][R]=res; //加入缓存

        return res;
    }


    //以后手姿态拿牌，所能获得的最佳分数
    public static int g2(int [] arr, int L, int R ,int[][] fmap, int[][] gmap){
        if(gmap[L][R]!=-1){ //先查缓存，缓存中有就直接返回
            return gmap[L][R];
        }

        int res=0;

        if(L!=R) {
            int p1=f2(arr,L+1,R,fmap,gmap); //别人拿走了最左边的牌，我只能以先手的姿态，在[L+1,R]上去拿
            int p2=f2(arr, L,R-1,fmap,gmap);//别人拿走了最右边的牌，我只能以先手的姿态，在[L,R-1]上去拿
            res=Math.min(p1,p2);
        }
        gmap[L][R]=res;

        return res; //因为对手也是决定聪明的，你只能在对手拿完后，极力争取剩下的最优，对手也是能算出来的，他一定会把那个最小的最优扔给你
    }


    public static int win2(int[] arr){
        if(arr==null||arr.length==0)
            return 0;
        int N=arr.length;
        int[][] fmap=new int[N][N];
        int[][] gmap=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                fmap[i][j]=-1;
                gmap[i][j]=-1;
            }
        }

        int first=f2(arr,0,arr.length-1,fmap,gmap);
        int second=g2(arr,0,arr.length-1,fmap,gmap);

        return Math.max(first,second);
    }

    public static void main(String[] args) {
        int arr[]=new int[]{5,7,4,5,8,1,6,0,3,4,1,7};
        System.out.println(win2(arr));
    }

}
