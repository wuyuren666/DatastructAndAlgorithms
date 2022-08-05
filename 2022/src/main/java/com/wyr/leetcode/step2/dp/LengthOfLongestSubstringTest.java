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
        if(s.length()==1){
            return 1;
        }
        if(s.length()==0){
            return 0;
        }

        char[] chs=s.toCharArray();
        int [] map=new int [255];//map[i]代表字符出现的上一个位置
        for(int i=0;i<map.length;i++){
            map[i]=-1;
        }
        int [] dp=new int [chs.length];//dp[i]代表以第i个字符结尾的不含重复字符的子字符串的最长的长度
        dp[0]=1;
        map[chs[0]]=0; //记录第一个字符的位置
        int res=dp[0]; //最终返回的结果

        for(int i=1;i<chs.length;i++){
            //dp[i]依赖于dp[i-1]和chs[i]上一次出现的位置
            int beginIndex=i-dp[i-1];//以i-1位置作为不重复子串结尾的这个子串的开始下标
            if(map[chs[i]]==-1){//代表当前的这个字符，之前没有出现过
                dp[i]=dp[i-1]+1;
                map[chs[i]]=i;//记录第一次出现的位置
            }else{//之前出现过
                //上一次出现的位置在beginIndex之前，不影响
                if(map[chs[i]]<beginIndex){
                    dp[i]=dp[i-1]+1;
                    map[chs[i]]=i;//更新成最新的位置
                }else{
                    dp[i]=i-map[chs[i]];
                    map[chs[i]]=i;//更新成最新的位置
                }
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }
}
