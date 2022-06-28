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
        if(strs.length==0){
            return "";
        }
        if(strs.length==1){
            return strs[0];
        }
        StringBuilder sb=new StringBuilder();
        String big=strs[0].length()>strs[1].length()?strs[0]:strs[1];
        String small= big.equals(strs[0])?strs[1]:strs[0];
        //首先在前两个数组元素中去找到最长的公共前缀
        process(small,big,0,sb);
        for(int i=2;i<strs.length;i++){
            big=sb.toString().length()>strs[i].length()?sb.toString():strs[i];
            small=big.equals(sb.toString())?strs[i]:sb.toString();
            sb=new StringBuilder();
            process(small,big,0,sb);
        }
        return sb.toString();
    }

    public void process(String small,String big, int index ,StringBuilder sb){
        if(index==small.length()){
            return;
        }
        if(small.charAt(index)!=big.charAt(index)){
            return;
        }
        sb.append(small.charAt(index));
        process(small,big,index+1,sb);
    }
}
