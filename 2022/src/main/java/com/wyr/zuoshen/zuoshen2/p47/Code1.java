package com.wyr.zuoshen.zuoshen2.p47;

import com.wyr.leetcode.step1.array.MinimumDifferenceTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code1 {

    /**
     * 给你一个字符串数组，返回使用所有字符串拼接的所有可能的结果
     */

    //8.4练习
    public static void process84(String[] strs, String tempStr , HashSet<Integer> set, List<String> all){
         if(set.size()==strs.length){ //set保存的是已经选过的字符串的下标
             all.add(tempStr);
         }
         for(int i=0;i<strs.length;i++){
             if(!set.contains(i)){
                 set.add(i);
                 process84(strs,tempStr+strs[i],set,all);
                 set.remove(i);
             }
         }

    }

    public static void main(String[] args) {
        String[] strs={"12","34","56"};
        List<String> all=new ArrayList<>();
        process84(strs,"",new HashSet<>(),all);
        all.stream().forEach(System.out::println);
    }


    public static void process(String[] strs, HashSet<Integer> used, String path , ArrayList<String> all){
        if(used.size()==strs.length){ //已经使用了，全部的字符串，直接加入结果集
            all.add(path);
        }
        for(int i=0;i<strs.length;i++){ //每一个字符串开始尝试
            if(!used.contains(i)){
                used.add(i);
                process(strs,used,path+strs[i],all);
                used.remove(i);
            }
        }
    }


}
