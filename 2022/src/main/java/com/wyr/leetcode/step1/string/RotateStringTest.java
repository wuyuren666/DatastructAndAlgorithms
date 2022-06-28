package com.wyr.leetcode.step1.string;

public class RotateStringTest {

    /**
     * 给定两个字符串, s和goal。如果在若干次旋转操作之后，s能变成goal，那么返回true。
     *
     * s的 旋转操作 就是将s 最左边的字符移动到最右边。
     *
     * 例如, 若s = 'abcde'，在旋转一次之后结果就是'bcdea'。
     *
     * 输入: s = "abcde", goal = "cdeab"
     * 输出: true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/rotate-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length()){
            return false;
        }
        int N=s.length();
        String newS=s+s;
        if(newS.indexOf(goal)==-1){
            return false;
        }else{
            return true;
        }

    }
}
