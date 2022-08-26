package com.wyr.leetcode.step2.dp;

public class IsInterleaveTest {
    /**
     * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2 交错 组成的。
     *
     * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
     *
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
     * 注意：a + b 意味着字符串 a 和 b 连接。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/interleaving-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int M=s1.length();
        int N=s2.length();
        int K=s3.length();
        if (M + N != K) {
            return false;
        }
        //dp[i][j]：s1取前i个字符和s2取前j个字符能够交错组成s3的前i+j个字符
        boolean [][] dp=new boolean[M+1][N+1];
        char [] c1=s1.toCharArray();
        char [] c2=s2.toCharArray();
        char [] c3=s3.toCharArray();
        dp[0][0]=true;

        //填最左边
        for(int i=1;i<M+1;i++){
            dp[i][0]=(c1[i-1]==c3[i-1])?true:false;
            if(!dp[i][0])
                break;
        }
        //填最上边
        for(int j=1;j<N+1;j++){
            dp[0][j]=(c2[j-1]==c3[j-1])?true:false;
            if(!dp[0][j])
                break;

        }
        /**
         *     dp[i][j]
         *
         * s1   i    0~i-1
         * s2   j    0~j-1
         * s总  i+j  0~i+j-1
         */

        for(int i=1;i<M+1;i++){
            for(int j=1;j<N+1;j++){
                // s3的前i+j个字符中的最后一个字符==s1的前i个字符的最后一个
                // 如果dp[i-1][j]还是true，就是s1的前i-1个字符和s2的前j个字符可以交错组成s3的前i+j-1个字符
                // 则dp[i][j]为true，s1的前i个字符和s2的前j个字符可以交错组成s3的前i+j个字符
                //或者
                //s3的前i+j个字符中的最后一个字符==s2的前j个字符的最后一个
                //如果dp[i][j-1]还是true，就是s1的前i个字符和s2的前j-1个字符可以交错组成s3的前i+j-1个字符
                // 则dp[i][j]为true，s1的前i个字符和s2的前j个字符可以交错组成s3的前i+j个字符
                if((c1[i-1]==c3[i+j-1]&&dp[i-1][j])||(c2[j-1]==c3[i+j-1]&&dp[i][j-1]))
                    dp[i][j]=true;
            }
        }
        return dp[M][N];
    }
}
