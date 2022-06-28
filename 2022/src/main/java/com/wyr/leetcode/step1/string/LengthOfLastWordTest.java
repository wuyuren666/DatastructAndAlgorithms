package com.wyr.leetcode.step1.string;

public class LengthOfLastWordTest {

    /**
     * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
     *
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/length-of-last-word
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 输入：s = "Hello World"
     * 输出：5
     * 解释：最后一个单词是“World”，长度为5。
     */


    public static int lengthOfLastWord(String s) {
        String[] strArr=s.split(" ");
        int length=strArr.length;
        int count=0;
        for(int i=length-1;i>=0;i--){
            //65~90   97~122
            if((strArr[i].charAt(0)>=65&&strArr[i].charAt(0)<=90)||(strArr[i].charAt(0)>=97&&strArr[i].charAt(0)<=122)){
                count=strArr[i].length();
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        lengthOfLastWord("   fly me   to   the moon  ");
    }
}
