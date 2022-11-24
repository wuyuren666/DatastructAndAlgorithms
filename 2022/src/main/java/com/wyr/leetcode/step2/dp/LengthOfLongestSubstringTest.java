package com.wyr.leetcode.step2.dp;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringTest {
    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.equals("")||s.length()==0){
            return 0;
        }
        char[] chs=s.toCharArray();
        int M=chs.length;
        int[] dp=new int[M];//dp[i]：以i下标结尾的最长不含重复字符的子字符串的长度
        Map<Character,Integer> map=new HashMap<>();//记录每一个字符上一次出现的下标位置
        dp[0]=1;
        int ans=dp[0];
        //记录第一个字符的位置
        map.put(chs[0],0);
        for(int i=1;i<M;i++){
            char  c=chs[i];//拿到下标i位置的字符
            if(!map.containsKey(c)){//当前字符之前没有出现过
                dp[i]=dp[i-1]+1;
                map.put(c,i);
            }else{ //当前字符之前出现过
                int lastIndex = map.get(c);
                int beginIndex = i-dp[i-1];
                //如果lastIndex出现在beginIndex之前，不影响
                if(lastIndex<beginIndex){
                    dp[i]=dp[i-1]+1;
                }else{
                    dp[i]=i-lastIndex;
                }
                map.put(c,i);
            }
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }
}
