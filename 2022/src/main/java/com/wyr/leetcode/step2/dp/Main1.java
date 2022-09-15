package com.wyr.leetcode.step2.dp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s= sc.nextLine();

        String[] s1 = s.split(" ");
        int n=s1.length-1;//n个数
        LinkedList<Integer> queue=new LinkedList<>();
        Map<Integer,Integer> map=new HashMap<>();//记录每一个数赢得的次数
        for(int i=0;i<n;i++){
            int temp=Integer.parseInt(s1[i]);
            queue.add(temp);
            map.put(temp,0);
        }
        int k=Integer.parseInt(s1[s1.length-1]);
        int ans=0;
        for(;;){
            int temp=queue.removeFirst();
            if(queue.peekFirst()>temp){
                //更新数字赢的次数
                map.put(queue.peekFirst(),map.get(queue.peekFirst())+1);
                if(map.get(queue.peekFirst()).equals(k)){
                    ans=queue.peekFirst();
                    break;
                }
                queue.addLast(temp);
            }else{
                //更新数字赢的次数
                map.put(temp,map.get(temp)+1);
                if(map.get(temp).equals(k)){
                    ans=temp;
                    break;
                }
                int temp2=queue.removeFirst();
                queue.addFirst(temp);
                queue.addLast(temp2);
            }
        }
        System.out.println(""+ans);
    }
}
