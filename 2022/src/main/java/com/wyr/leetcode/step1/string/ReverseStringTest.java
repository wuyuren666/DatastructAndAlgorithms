package com.wyr.leetcode.step1.string;
import java.util.LinkedHashSet;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * https://leetcode.cn/problems/reverse-string/
 *
 */
public class ReverseStringTest {
    public static void main(String[] args) {
        char[] chars = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverseString(chars);
        for (char aChar : chars) {
            System.out.println(aChar);
        }
    }


    public static void reverseString(char[] chars) {
        char temp;
        for (int i = 0; i < chars.length / 2; i++) {
            temp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = temp;
        }
    }


}

