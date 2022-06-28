package com.wyr.leetcode.step1.string;

public class ReverseLeftWordsTest {

    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，
     * 该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String reverseLeftWords(String s, int n) {
        //将s先和自己拼接成一个新的字符串，这个新的字符串中就包含了s旋转的所有情况
        //abcdefgabcdefg
        String newS=s+s;
        int N=s.length();
        return newS.substring(n,n+N);
    }




}
