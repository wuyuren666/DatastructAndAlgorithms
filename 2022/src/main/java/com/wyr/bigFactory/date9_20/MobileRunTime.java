package com.wyr.bigFactory.date9_20;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MobileRunTime {
    /**
     * 一台手机，可以同时运行多款App。
     * 假设后台没有App运行时，收集进入到休眠，否则手机处于运行状态
     * 第i个App的开始运行时间start_i，运行结束时间end_i，组成第i个App的运行区间intervals[i] = [start_i,end_i]
     * 数组intervals表示今天手机的各个App的运行区间的集合，求这台手机运行了多长时间
     *
     * 样例输入
     * 1 3
     * 2 6
     * 8 10
     * 15 18
     *
     * 样例输出
     * 10
     *
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Element> list=new ArrayList<>();
        String temp=null;
        while(sc.hasNextLine()){
            temp=sc.nextLine();
            if(!temp.equals("")){
                String[] s = temp.split(" ");
                list.add(new Element(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
            }else {
                break;
            }
        }
        //区间的个数
        int length=list.size();
        //并查集结构
        int[] father=new int[length];
        //初始化
        for(int i=0;i<length;i++){
            father[i]=i;
        }
        //区间合并
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                //后一个应用的开始时间<=前一个应用的结束时间&&后一个应用的结束时间>=前一个应用的开始时间
                //满足这个要求才能说是两个区间相交，这个最重要
                if(list.get(j).start<=list.get(i).end&&list.get(j).end>=list.get(i).start){
                    union(father,i,j);
                }
            }
        }
        List<Integer> fatherIndex=new ArrayList<>(length);
        //获得父元素的下标
        getNum(father,fatherIndex);
        int ans=0;
        for(int i=0;i<fatherIndex.size();i++){
            int minStart=Integer.MAX_VALUE;
            int maxEnd=Integer.MIN_VALUE;
            for(int j=0;j<length;j++){
                if(findFather(father,j)==fatherIndex.get(i)){
                    minStart=Math.min(minStart,list.get(j).start);
                    maxEnd=Math.max(maxEnd,list.get(j).end);
                }
            }
            ans+=(maxEnd-minStart);
        }
        System.out.println(ans);
        sc.close();
    }

    public static class Element{
        public int start;
        public int end;

        public Element(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //将所有父代表元素的下标放入list集合
    public static void getNum(int []father, List<Integer> list){
        for(int i=0;i<father.length;i++){
            if(i==father[i]){
                list.add(i);
            }
        }
    }
    //找到当前元素的父下标
    public static int findFather(int [] father, int index){
        if(index!=father[index]){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }
    //合并
    public static void union(int [] father, int index1, int index2){
        father[findFather(father,index1)]=findFather(father,index2);
    }
}
