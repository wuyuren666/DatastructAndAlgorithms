package com.wyr.leetcode.step2.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAnagramsTest {

    /**
     * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     *
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static List<Integer> findAnagrams(String s, String p) {
        //使用固定大小的窗口去滑动
        int windowSize = p.length();
        int left=0,right=0;
        int count=0; //已经解决好的字符的个数
        Map<Character, Integer> needMap = new HashMap<>();
        Map<Character,Integer> windowMap = new HashMap<>();
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<p.length();i++){
            needMap.put(p.charAt(i),needMap.getOrDefault(p.charAt(i),0)+1);
        }

        while(right<s.length()){
            char curChar = s.charAt(right);
            //先将当前字符加入到窗口
            windowMap.put(curChar,windowMap.getOrDefault(curChar,0)+1);
            //如果窗口形成了
            if(right-left+1==windowSize){
                //如果两个map一致的话
                if(windowMap.equals(needMap)){
                    ans.add(left);
                }
                char leftChar = s.charAt(left);
                //将窗口缩小
                if(windowMap.get(leftChar).equals(1)){
                    windowMap.remove(leftChar);
                }else{
                    windowMap.put(leftChar,windowMap.get(leftChar)-1);
                }
                left++;
            }
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        findAnagrams("baa","aa");
    }
}
