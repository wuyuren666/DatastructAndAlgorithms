package com.wyr.bigFactory.date9_1;

import java.util.*;

public class ChangChenTest {
    /**
     * 长城很美特别是他的锯齿形状
     * 现在给你一个数组，请将这个数组，变成"长城"
     * 输入：
     * 第一行输入一个数n，代表数组的长度
     * 第二行输入一个长度为n的数组
     * 5
     * 1 3 4 2 1
     *
     *
     * 输出：2
     * 返回，最少的操作次数
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        int [] nums=new int[n];
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
            set.add(nums[i]);
        }

        List<Integer> list=new ArrayList<>(set);
        int res=Integer.MAX_VALUE;
        //暴力方法
        for(int i=0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size();j++){
                res=Math.min(process(nums,list.get(i),list.get(j)),res);
            }
        }
        System.out.println(res);
        sc.close();
    }

    public static int process(int [] nums, int n1, int n2){
        //选择n1开始
        int p1=0;
        boolean flag=true;
        for(int i=0;i<nums.length;i++) {
            if (flag) {
                if (nums[i] != n1)
                    p1++;
                flag = false;
            } else {
                if (nums[i] != n2)
                    p1++;
                flag = true;
            }
        }
        //选择n2开始
        int p2 = 0;
        flag = true;
        for (int i = 0; i < nums.length; i++) {
            if (flag) {
                if (nums[i] != n2)
                    p2++;
                flag = false;
            } else {
                if (nums[i] != n1)
                    p2++;
                flag = true;
            }
        }
        return Math.min(p1,p2);
    }
}
