package com.wyr.leetcode.step2.dp;

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
        if(s==null||s.length()==0){
            return 0;
        }
        char[] str=s.toCharArray();
        int [] map=new int[255];//记录str中的每一个字符上一次出现的位置
        for(int i=0;i<255;i++){
            map[i]=-1; //开始都没有出现过记为-1
        }
        int res=1;//最终返回的答案
        int pre=1;//前一个位置他往左走无重复字符的最长子串的长度
        int cur;//当前位置往左走无重复字符的最长子串的长度
        map[str[0]]=0;//记录下标为0的字符他出现的位置
        for(int i=1;i<str.length;i++){ //从下标为1的字符开始
            //i位置的字符往左走无重复字符的最长子串的长度，取决的第一点：上一次这个字符出现的位置
            int p1=i-map[str[i]];
            //i位置的字符往左走无重复字符的最长子串的长度，取决的第二点：他前面一个字符往左走无重复字符的最长子串的长度
            int p2=pre+1;
            cur=Math.min(p1,p2);
            res=Math.max(res,cur);
            map[str[i]]=i; //记录当前字符出现的位置
            pre=cur; //pre更新
        }
        return res;
    }
}
