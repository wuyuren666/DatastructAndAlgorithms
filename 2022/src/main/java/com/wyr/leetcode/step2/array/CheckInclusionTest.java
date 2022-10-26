package com.wyr.leetcode.step2.array;

import java.util.HashMap;
import java.util.Map;

public class CheckInclusionTest {
    /**
     * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
     *
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/permutation-in-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 和 leetcode-step3-array-MinWindowTest思想一样，使用可变大小的窗口去滑动，只需要改动最后一行代码
     */
    public boolean checkInclusion1(String s1, String s2) {
        //保存t的映射关系
        Map<Character, Integer> needMap = new HashMap<>();
        //窗口中的映射关系
        Map<Character, Integer> windowMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            needMap.put(c, needMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int start = 0; //最终结果的开始下标
        int length = Integer.MAX_VALUE; //最终结果的长度
        int count = 0; //记录解决掉的字符数
        while (right < s2.length()) {
            char c = s2.charAt(right);
            if (needMap.containsKey(c)) { //当前字符是所需要的字符
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1); //往窗口中更新
                if (windowMap.get(c).equals(needMap.get(c))) { //刚好当前字符的个数满足最终条件，将解决掉的字符数+1
                    count++;
                }
            }
            //滑动窗口最小模版
            while (count == needMap.size()) { //解决掉的字符数等于需要解决的字符数
                //更新最优结果
                if (right - left + 1 < length) {
                    start = left;
                    length = right - left + 1;
                }
                char leftChar = s2.charAt(left);
                //移除left所指向的字符
                if (windowMap.containsKey(leftChar)) {
                    if (windowMap.get(leftChar).equals(1)) {
                        windowMap.remove(leftChar);
                        count--;
                    } else {
                        windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                        if (windowMap.get(leftChar).intValue() < needMap.get(leftChar).intValue()) {
                            count--;
                        }
                    }
                }
                left++;
            }
            right++;
        }
        return (length == s1.length());
    }
    //使用固定大小的窗口进行滑动
    public  boolean checkInclusion(String s1, String s2){
        int windowSize=s1.length();
        Map<Character,Integer> needMap=new HashMap<>();
        Map<Character,Integer> windowMap=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            needMap.put(s1.charAt(i),needMap.getOrDefault(s1.charAt(i),0)+1);
        }
        int left=0,right=0;
        while(right<s2.length()){
            char curChar=s2.charAt(right);
            //先加入当前字符
            windowMap.put(curChar,windowMap.getOrDefault(curChar,0)+1);

            if(right-left+1==windowSize){ //窗口形成了
                if(windowMap.equals(needMap)){
                    return true;
                }
                char leftChar=s2.charAt(left);
                if(windowMap.get(leftChar).equals(1)){
                    windowMap.remove(leftChar);
                }else{
                    windowMap.put(leftChar,windowMap.get(leftChar)-1);
                }
                left++;
            }
            right++;
        }
        return false;
    }

}
