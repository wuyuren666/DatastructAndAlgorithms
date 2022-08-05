package com.wyr.dp.zuoshen.p7_8_9_10;


/**
 * 给定一个整形数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次都只能拿走最左，或最右边的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回获胜者的分数
 */
public class Code1 {


    //以先手姿态拿牌，所能获得的最佳分数
    public static int f1(int [] arr, int L, int R){
         if(L==R){ //basecase,因为就剩一张牌，又是以先手姿态拿牌，所以必然会将这张牌拿走
             return arr[L];
         }
         int p1=arr[L]+g1(arr,L+1,R);//g为后手方法，也就是说，我以先手姿态拿走了最左边的那张牌，在[L+1,R]上，就是以后手的姿态去拿牌
         int p2=arr[R]+g1(arr,L,R-1);//g为后手方法，也就是说，我以先手姿态拿走了最右边的那张牌，在[L,R-1]上，就是以后手的姿态去拿牌
         return Math.max(p1,p2);
    }


    //以后手姿态拿牌，所能获得的最佳分数
    public static int g1(int [] arr, int L, int R){
        if(L==R) //就剩最后一张牌了，我又是以后手的姿态去拿牌，肯定拿不到
            return 0;
        int p1=f1(arr,L+1,R); //别人拿走了最左边的牌，我只能以先手的姿态，在[L+1,R]上去拿
        int p2=f1(arr, L,R-1);//别人拿走了最右边的牌，我只能以先手的姿态，在[L,R-1]上去拿
        return Math.min(p1,p2); //因为对手也是决定聪明的，你只能在对手拿完后，极力争取剩下的最优，对手也是能算出来的，他一定会把那个最小的最优扔给你
    }


    public static int win1(int[] arr){
        if(arr==null||arr.length==0)
            return 0;
        int first=f1(arr,0,arr.length-1);
        int second=g1(arr,0,arr.length-1);
        return Math.max(first,second);
    }


    public static void main(String[] args) {
        int arr[]=new int[]{12,3,5,1,10};
        System.out.println(win1(arr));
    }
}
