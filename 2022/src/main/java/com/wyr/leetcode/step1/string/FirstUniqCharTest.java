package com.wyr.leetcode.step1.string;

import java.util.*;

/**
 * 给定一个字符串 s ，找到它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 * s 只包含小写字母
 *
 * 输入: s = "leetcode"
 * 输出: 0
 *
 * 输入: s = "aabb"
 * 输出: -1
 *
 * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */



public class FirstUniqCharTest {
    public static void main(String[] args) {
        //System.out.println(firstUniqChar1("leetcode"));
        System.out.println(firstUniqChar2("leetcode"));
    }

    //使用HashMap
    public static int firstUniqChar1(String s) {

        char[] chars = s.toCharArray();
        int index = 0;
        Map<Character, Integer> map = new HashMap<>();//Map集合
        for (char c : chars) { //利用HashMap的特点
            map.put(c, map.containsKey(c) == true ? map.get(c) + 1 : 1);
        }

        for (char c : chars) {
            if (map.get(c) == 1) {
                return index;
            }
            index++;
        }
        return -1;
    }

    //使用自制哈希表
    public static int firstUniqChar2(String s) {
        int[] count = new int[26];  //这个数组保存s中每个小写字母出现的次数
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            //s.charAt(i) - 'a'为当前字母在count数组中的下标
            if (count[s.charAt(i) - 'a'] == 1) {
                return index;
            }
            index++;
        }
        return -1;
    }


    /**
     * 还是使用上面的方法好，打败接近90%。
     * 这个方法只打败55%
     * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
     */
    public char firstUniqChar(String s) {
        char res=' ';
        if(s.equals("")){
            return res;
        }
        char temp=' ';
        for(int i=0;i<s.length();i++){
            //将s中下标为i的字符赋值给res
            temp=s.charAt(i);
            if(s.indexOf(temp)==s.lastIndexOf(temp)){ //只出现一次的字符，肯定其第一次出现的下标和最后一次出现的下标是一样的
                return temp;
            }
        }
        return res;
    }
}