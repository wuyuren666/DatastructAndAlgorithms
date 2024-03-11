package com.wyr.leetcode.step1.string;

import com.sun.deploy.model.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 *
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1
 *
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */
public class StrStrTest {
    public static void main(String[] args) {
        strStr("mississippi","issipi");
    }

    public static int strStr(String haystack, String needle) {
       //return haystack.indexOf(needle);
        if (needle.length() > haystack.length()) {
            return -1;
        }
        if (needle.length() == haystack.length()) {
            if (needle.equals(haystack)) {
                return 0;
            }
            return -1;
        }
        // c2的长度一定是大于c1的
        int res = -1;
        char[] c1 = haystack.toCharArray();
        char[] c2 = needle.toCharArray();
        for (int i = 0; i < c1.length; i++) {
            // 根据c2的第一个字符去开始匹配
            if (c1[i] != c2[0]) {
                continue;
            }
            // 在c1中找到与c2[0]相等的字符，就往下接着匹配，注意下标不越界
            int j, k;
            for (j = 1, k = i + 1; j < c2.length && k < c1.length; j++, k++) {
                if (c1[k] != c2[j]) {
                    break;
                }
            }
            if (j == c2.length) {// 代表上面的for循环没有被break，全部匹配成功
                res = i;
                break;
            }
        }
        return res;
    }
}
