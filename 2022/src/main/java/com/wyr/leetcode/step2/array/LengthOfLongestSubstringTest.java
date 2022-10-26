package com.wyr.leetcode.step2.array;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringTest {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     *
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     */

    public int lengthOfLongestSubstring(String s) {
        //记录字符和对应个数的映射关系
        Map<Character,Integer> map=new HashMap<>();
        int left=0,right=0;
        int ans=Integer.MIN_VALUE;//最终的结果
        while(right<s.length()){
            char curChar= s.charAt(right);
            map.put(curChar,map.getOrDefault(curChar,0)+1);
            //不满足条件，即窗口中的字符个数大于map的大小
            while(right-left+1>map.size()){
                char leftChar= s.charAt(left);
                //窗口左边界缩小，同时更新map
                if(map.get(leftChar).equals(1)){
                    map.remove(leftChar);
                }else{
                    map.put(leftChar,map.get(leftChar)-1);
                }
                left++;
            }
            //满足条件更新最优结果
            ans=Math.max(ans,right-left+1);
            right++;
        }
        return ans==Integer.MIN_VALUE ?0:ans;
    }
}
