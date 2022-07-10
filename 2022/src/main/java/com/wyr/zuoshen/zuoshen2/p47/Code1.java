package com.wyr.zuoshen.zuoshen2.p47;

import java.util.ArrayList;
import java.util.HashSet;

public class Code1 {

    /**
     * 给你一个字符串数组，返回使用所有字符串拼接的所有可能的结果
     */

    /**
     *
     * @param strs 放着所有的字符串
     * @param used 已经使用过的字符串，存的是下标
     * @param path 之前使用过的字符串，拼接成的字符串
     * @param all 收集所有的拼接结果
     */
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

    public static void main(String[] args) {
        String[] strs={"12","34","56"};
        ArrayList<String> all=new ArrayList<>();
        process(strs,new HashSet<Integer>(),"",all);
        for (String s : all) {
            System.out.println(s);
        }
    }
}
