package com.wyr.bigFactory.date9_1;

import java.util.Scanner;

public class BingDu {
    /**
     * 一年一度的入侵活动来了，病毒小绿被病毒大王安排去入侵多个主机。
     * 但是每一个主机都安装了杀毒设备。小绿想要避开这些杀毒设备去入侵主机，是一项非常困难的任务
     * 好在，病毒大王赋予了小绿特殊的力量，让他能够通过伪装去绕开杀毒设备的检测。
     * 小绿需要按顺序，去入侵n个主机，每一个主机上的杀毒设备有自己的辨识度a[i],
     * 如果小绿的伪装度v与病毒设备的辨识度a[i]绝对值不超过x（主机的辨识精度）
     * 即：｜a[i]-v｜<=x。则病毒可以入侵当前的主机
     * 现给定n个主机的辨识度，问小绿最少需要变化多少次伪装度（伪装度可以取任意值），
     * 才能按顺序，成功入侵所有的主机，注意小绿的初始伪装度可以取任意值，且不记入小绿变化的伪装次数。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int x= sc.nextInt();
        sc.nextLine();
        int [] nums=new int[n];

        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        sc.close();
        //并查集结构
        int [] father =new int[n];

        for(int i=0;i<father.length;i++){
            father[i]=i;
        }

        for(int i=0;i<nums.length-1;i++){
            for(int j=nums[i]-x;j<=nums[i]+x;j++){
                if(j==nums[i+1]){
                    //下标i位置的数和i+1位置的数不在同一个集合中，
                    if(findFather(father,i)!=findFather(father,i+1)){
                        union(father,i,i+1);
                    }
                }
            }
        }
        System.out.println(getSetNum(father)-1);
    }
    //寻找父代表元素
    public static int findFather(int [] father ,int index){
        if(father[index]!=index){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }
    //合并
    public static void union(int [] father, int index1, int index2){
        father[findFather(father,index1)]=findFather(father,index2);
    }

    public  static int getSetNum(int [] father){
        int res=0;
        for(int i=0; i< father.length; i++){
            if(father[i]==i){
                res++;
            }
        }
        return res;
    }
}
