package com.wyr.bigFactory.date5_6;

import java.util.Scanner;

/**
 * 背包问题
 * 小A一直梦想能成为一位时间管理大师，现在他开始着手管理自己的时间了。
 * 他统计了自己手上的所有任务，计算了完成每一个任务需要花费的时间和能创造的价值
 * 并且将任务按照时间升序进行了排序。
 * 小A能投入在工作上的总时间是固定的，他计划完成两个任务，让他们的价值之和尽可能的高
 *
 * 输入描述：
 * 第一行包含两个整数n，m，分别表示任务的数目，小A能支配的总时长
 * 接下来的n行描述每个任务，每行包含两个整数a和b，分别表示完成任务需要花费的时间与该任务的价值
 * 任务是按照时间升序顺序排序给出的。每一个任务只能完成一次
 *
 * 输入：
 * 4 6
 * 1 8
 * 2 1
 * 4 3
 * 6 4
 * 输出：11
 */
public class TimeManagerTest {

    //dp版本
    public static int getMaxProfit1(int[] weights,int [] values,int bag){
        if(bag==0||weights==null||values==null){
            return 0;
        }
        int N=weights.length;
        int [][] dp=new int[N+1][bag+1];
        for(int i=N-1;i>=0;i--){ //index
            for(int j=bag;j>=0;j--){ //rest
                //不选则当前货物
                int p1=dp[i+1][j];
                //选择当前货物，但是我们要看背包容量是否可以装下这个货物
                int p2=0;
                //如果在选择下一个货物的时候，发现rest<0了，代表当前货物装不了
                if(j-weights[i]>0){  //这个要注意
                    //可以装下当前货物，你去下一个货物再去判断
                    p2=values[i]+dp[i+1][j-weights[i]];
                }
                dp[i][j]=Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }
    /**
     *
     * @param weights 重量数组
     * @param values 价值数组
     * @param bag 背包容量
     * @return 最大价值
     */
    public static int getMaxProfit(int[] weights,int [] values,int bag){
         if(bag==0||weights==null||values==null){
             return 0;
         }
        return process(weights,values,0,bag);
    }
    //index代表货物的编号，rest：背包剩余容量
    public static int process(int[] weights,int [] values,int index,int rest){
        //背包剩余容量<0
        if(rest<0){
            return -1; //当作标志位，判断背包容量是否可以装下货物
        }
        //没有货物可选
        if(index==weights.length){
            return 0;
        }
        //不选则当前货物
        int p1=process(weights,values,index+1,rest);

        //选择当前货物，但是我们要看背包容量是否可以装下这个货物
        int p2=0;
        //如果在选择下一个货物的时候，发现rest<0了，代表当前货物装不了
        if(process(weights,values,index+1,rest-weights[index])!=-1){
            //可以装下当前货物，你去下一个货物再去判断
            p2=values[index]+process(weights,values,index+1,rest-weights[index]);
        }
        return Math.max(p1,p2);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String oneLine=scanner.nextLine();//获取第一行的数据
        String[] s = oneLine.split(" ");
        int N=Integer.parseInt(s[0]); //获取货物数量
        int bag=Integer.parseInt(s[1]);//获取背包容量
        int[] weights=new int[N];
        int [] values=new int[N];
        for(int i=0;i<N;i++){
            String line=scanner.nextLine();
            String []s1 = line.split(" ");
            weights[i]=Integer.parseInt(s1[0]);
            values[i]=Integer.parseInt(s1[1]);
        }
        System.out.println(getMaxProfit1(weights, values, bag));


    }
}
