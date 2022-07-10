package com.wyr.leetcode.step1.string;

public class MinimumMovesTest {
    /**
     * 给你一个字符串 s ，由 n 个字符组成，每个字符不是 'X' 就是 'O' 。
     *
     * 一次操作定义为从 s 中选出 三个连续字符 并将选中的每个字符都转换为 'O' 。注意，如果字符已经是 'O' ，只需要保持不变 。
     *
     * 返回将 s 中所有字符均转换为 'O' 需要执行的最少操作次数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-moves-to-convert-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minimumMoves(String s) {
        int p=0; //单指针
        int N=s.length();
        int res=0;
        while(p<=N-1){
            if(s.charAt(p)=='O'){
                p++;
            }else{
                p+=3;
                res++;
            }
        }
        return res;
    }
}
