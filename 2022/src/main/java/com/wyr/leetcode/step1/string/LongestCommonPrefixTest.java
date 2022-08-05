package com.wyr.leetcode.step1.string;

public class LongestCommonPrefixTest {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     *
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *
     * https://leetcode.cn/problems/longest-common-prefix/
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==1){ //长度为1，直接返回
            return strs[0];
        }

        int smallLength=Integer.MAX_VALUE;
        //找到长度最小的字符串
        for(String str : strs){
            smallLength=Math.min(smallLength,str.length());
        }

        int res=0; //最终的最长前缀结果
        for(int i=0;i<smallLength;i++){
            boolean flag=true;
            char tempChar=strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(strs[j].charAt(i)!=tempChar){
                    flag=false;
                    break;
                }
            }
            if(flag)
                res++;
            else
                break;
        }
        return res==0?"":strs[0].substring(0,res);
    }
}
