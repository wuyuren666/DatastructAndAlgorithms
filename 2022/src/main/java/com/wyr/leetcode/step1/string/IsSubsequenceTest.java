package com.wyr.leetcode.step1.string;

public  class IsSubsequenceTest {
    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * 输入：s = "abc", t = "ahbgdc"
     * 输出：true
     *
     *
     * 输入：s = "axc", t = "ahbgdc"
     * 输出：false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/is-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */
    public boolean isSubsequence(String s, String t) {
        if(s.length()>t.length()){
            return false;
        }


        //双指针
        char [] cs=s.toCharArray();
        char [] ct=t.toCharArray();
        int p1=0;
        int p2=0;
        while(p1<cs.length&&p2<ct.length){
            if(ct[p2]==cs[p1]){
                p1++;
                p2++;
            }else{
                p2++;
            }
        }
        return p1==cs.length?true:false;
    }

    public static void main(String[] args) {

    }


}
