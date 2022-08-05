package com.wyr.leetcode.step1.dp;

public class CountBitsTest {
    /**
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，
     * 返回一个长度为 n + 1 的数组 ans 作为答案。
     *
     * 输入：n = 2
     * 输出：[0,1,1]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     *
     * 输入：n = 5
     * 输出：[0,1,1,2,1,2]
     * 解释：
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 3 --> 11
     * 4 --> 100
     * 5 --> 101
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/counting-bits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] countBits(int n) {
        int [] dp=new int [n+1];
        dp[0]=0;
        if(n==0){
            return dp;
        }
        dp[1]=1;
        //i为偶数 dp[i]==dp[i>>1]
        //i为奇数 dp[i]=dp[i>>1]+1
        for(int i=2;i<n+1;i++){
            if((i&1)==0){//偶数
                dp[i]=dp[i>>1];
            }else{//奇数
                dp[i]=dp[i>>1]+1;
            }
        }
        return dp;
    }
}
