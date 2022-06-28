package com.wyr.leetcode.step1.string;


/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * s 和 t 仅包含小写字母
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 */
public class IsAnagramTest {
        public static void main(String[] args) {
                System.out.println(isAnagram("aabbbb", "aaaabb"));
        }

        //自制哈希表
        public static boolean isAnagram(String s, String t) {
                int[] count = new int[26];
                // s全部存到哈希表中
                for (int i = 0; i < s.length(); i++) {
                        count[s.charAt(i) - 'a']++;
                }
                for (int i = 0; i < t.length(); i++) {
                        count[t.charAt(i) - 'a']--;
                        // 如果已经出现不一致的提前返回
                        if (count[t.charAt(i) - 'a'] < 0) {
                                return false;
                        }
                }
                // 遍历哈希表，不为0表示不一致
                for (int c : count) {
                        if (c != 0) {
                                return false;
                        }
                }
                return true;
        }
}
