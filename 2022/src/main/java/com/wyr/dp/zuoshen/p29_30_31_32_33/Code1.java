package com.wyr.dp.zuoshen.p29_30_31_32_33;

/**
 * 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 *
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 */
public class Code1 {
    //暴力递归版本
    public int longestPalindromeSubseq1(String s) {
        if(s.length()==0||s==null){
            return 0;
        }
        char[] chs=s.toCharArray();
        return process(chs,0,chs.length-1);
    }
    //我就在s[L...R]上去尝试
    public int process(char[] chs, int L, int R){
        if(L==R){ //一个字符肯定为算作长度为1的回文串
           return 1;
        }
        if(L==R-1){ //两个字符，得看他们是否相等
            //相等，就是长度为2的回文串，不想等就是长度为1的回文串
            return chs[L]==chs[R]?2:1;
        }

        //普遍情况

        //情况一：最长回文子序列，既不以L开头，又不以R结尾
        int p1=process(chs,L+1, R-1);

        //情况二：最长回文子序列，以L开头，但是不以R结尾
        int p2=process(chs,L,R-1);

        //情况三：最长回文子序列，不以L开头，但是以R结尾
        int p3=process(chs,L+1, R);

        //情况四：最长回文子序列，既以L开头，又以R结尾
        int p4=chs[L]==chs[R]?2+process(chs,L+1,R-1):0;

        return Math.max(Math.max(Math.max(p1,p2),p3),p4);
    }



    //动态规划版本
    public int longestPalindromeSubseq2(String s) {
        if(s.length()==0||s==null){
            return 0;
        }
        char[] chs=s.toCharArray();
        int N=chs.length;
        int [][] dp=new int[N][N];
        dp[N-1][N-1]=1;
        for(int i=0;i<N-1;i++){ //同时填对角线和对角线右边的一条线
            dp[i][i]=1;
            dp[i][i+1]=chs[i]==chs[i+1]?2:1;
        }
        //N>2的情况下才会走这个嵌套的for循环
        for (int i = 2; i < N; i++) { //代表偏移量
            for (int L = 0; L < N - 2; L++) { //代表行
                if (L + i < N) {
                    //情况一：最长回文子序列，既不以L开头，又不以R结尾
                    int p1 = dp[L + 1][L + i - 1];

                    //情况二：最长回文子序列，以L开头，但是不以R结尾
                    int p2 = dp[L][L + i - 1];

                    //情况三：最长回文子序列，不以L开头，但是以R结尾
                    int p3 = dp[L + 1][L + i];

                    //情况四：最长回文子序列，既以L开头，又以R结尾
                    int p4 = chs[L] == chs[L + i] ? 2 + dp[L + 1][L + i - 1] : 0;

                    dp[L][L + i] = Math.max(Math.max(Math.max(p1, p2), p3), p4);
                } else {
                    break;
                }
            }
        }

        return dp[0][N-1];
    }

    public static void main(String[] args) {

    }


}
