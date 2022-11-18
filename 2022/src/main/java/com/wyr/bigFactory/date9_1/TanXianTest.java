package com.wyr.bigFactory.date9_1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public  class TanXianTest {
    /**
     * 小X在一片大陆上探险，有一天他发现了一个洞穴，洞穴中有n道门，打开每一道门需要对应的钥匙
     * 编号为1的钥匙能用于打开第1道门，而且只有在打开了第i（i>=1）道门之后，才能打开第i+1道门，
     * 一开始只能打开第1道门。幸运的是，小X在外面探险的途中，每天都能发现一道能打开这n道门中其中
     * 一道门的钥匙，每天找完钥匙后他都会去打开所有能打开的门。现在给出他每天找到的钥匙编号，
     * 请问每一道门分别在哪一天被打开
     *
     * 输入描述：
     *  第一行包含一个正整数n，表示门的数量
     *  接下来一行包含n个正整数，表示每一天找到的钥匙编号
     *
     * 输出描述：
     *  输出一行n个数，表示每一个道门被打开的时间
     *
     * 样例输入
     * 5
     * 5 3 1 2 4
     *
     * 样例输出
     * 3 4 4 5 5
     *
     * 样例说明：
     * 到第三天时获得的钥匙是1、3、5，能够打开第1道门，到第四天时钥匙为1、2、3、5，能继续打开第2和3道门，
     * 道第五天时获得了全部的钥匙，能打开所有剩下的门
     */

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        sc.nextLine();
        int [] nums=new int [N+1];
        for(int i=1;i<N+1;i++){
            nums[i]=sc.nextInt();
        }
        //默认所有的数组，下标为0的位置我们不用
        int [] day_key_map=new int[N+1]; //day_key_map[i]：表示第i天获得的打开第i扇门的钥匙
        int [] daymap=new int[N+1];//dayMap[i]：第i扇门被打开的日期，dayMap[i]==0代表第i扇门没有被打开

        for(int i=1;i<nums.length;i++){
            int curDay=i;//第几天
            int curKeyOfDoor=nums[i]; //第curDay天获得的第curKeyOfDoor扇门的钥匙
            int j=curKeyOfDoor-1;
            for(;j>=1;j--){
                if(daymap[j]!=0){//说明之前的门已经被打开了
                    continue;
                }else{
                    break;
                }
            }
            day_key_map[curDay]=curKeyOfDoor;//记录第curDay天获得哪扇门的钥匙
            if(j<1){//可以打开，说明在上一步的for循环中，没有触发到break，也就是说，当前这扇门之前的几扇门全都被打开了
                daymap[curKeyOfDoor]=curDay; //记录第curKeyOfDoor被打开的日期
                //这个时候需要看手里存在的钥匙能否打开后续的门
                for(int nextKeyOfDoor=curKeyOfDoor+1;nextKeyOfDoor<=N+1;nextKeyOfDoor++){
                    boolean flag=true; //标识位
                    for(int day=1;day<i;day++){ //从第1天开始道curDay-1中去看有没有获得过下一个扇门的钥匙
                        if(day_key_map[day]==nextKeyOfDoor){//有下一个门的钥匙
                            flag=false;
                            daymap[day_key_map[day]]=curDay;
                            break; //这个break完全可以加上
                        }
                    }
                    if(flag){ //仍然是true的话说明，第nextKeyOfDoor扇门是无法被打开的
                        break;
                    }
                }
            }
        }

        for(int i=1;i<N+1;i++){
            System.out.print(daymap[i]+" ");
        }
        sc.close();
    }
}
