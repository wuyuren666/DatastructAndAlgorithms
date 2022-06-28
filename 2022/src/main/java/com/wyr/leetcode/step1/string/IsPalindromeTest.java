package com.wyr.leetcode.step1.string;



/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 *
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 * https://leetcode.cn/problems/valid-palindrome/
 */
public class IsPalindromeTest {
    public static void main(String[] args) {
        System.out.println(isPalindrome("0P"));

    }


    public static boolean isPalindrome(String s){
        if(s.equals(""))
            return true;
        char[] chars = s.toCharArray();
        StringBuilder sb=new StringBuilder();
        for (char c : chars) {
            if((c>=97&&c<=122)||(c>=65&&c<=90||c>=48&&c<=57)){
                sb.append(c);
            }
        }
        return sb.toString().equalsIgnoreCase(sb.reverse().toString());
    }
}
