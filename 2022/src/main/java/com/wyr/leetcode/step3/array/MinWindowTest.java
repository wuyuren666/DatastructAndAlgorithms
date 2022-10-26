package com.wyr.leetcode.step3.array;

import java.util.HashMap;
import java.util.Map;

public class MinWindowTest {
    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
     * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     *
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * https://leetcode.cn/problems/minimum-window-substring/
     */
    public static String minWindow(String s, String t) {
        //保存t的映射关系
        Map<Character,Integer> needMap = new HashMap<>();
        //窗口中的映射关系
        Map<Character,Integer> windowMap = new HashMap<>();
        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            needMap.put(c,needMap.getOrDefault(c,0)+1);
        }

        int left=0,right=0;
        int start=0; //最终结果的开始下标
        int length=Integer.MAX_VALUE; //最终结果的长度
        int count = 0; //记录解决掉的字符数
        while(right<s.length()){
            char c = s.charAt(right);
            if(needMap.containsKey(c)){ //当前字符是所需要的字符
                windowMap.put(c,windowMap.getOrDefault(c,0)+1); //往窗口中更新
                if(windowMap.get(c).equals(needMap.get(c))){ //刚好当前字符的个数满足最终条件，将解决掉的字符数+1
                    count++;
                }
            }
            //滑动窗口最小模版
            while(count==needMap.size()){ //解决掉的字符数等于需要解决的字符数
                //更新最优结果
                if(right-left+1<length){
                    start = left;
                    length = right-left+1;
                }
                char leftChar = s.charAt(left);
                //移除left所指向的字符
                if(windowMap.containsKey(leftChar)){
                    if(windowMap.get(leftChar).equals(1)){
                        windowMap.remove(leftChar);
                        count--;
                    }else{
                        windowMap.put(leftChar,windowMap.get(leftChar)-1);
                        if(windowMap.get(leftChar).intValue()<needMap.get(leftChar).intValue()){
                            count--;
                        }
                    }
                }
                left++;
            }
            right++;
        }
        return (length==Integer.MAX_VALUE? "" : s.substring(start,start+length));
    }

    public static void main(String[] args) {
        System.out.println(minWindow("EBBANCF", "ABC"));
    }
}
