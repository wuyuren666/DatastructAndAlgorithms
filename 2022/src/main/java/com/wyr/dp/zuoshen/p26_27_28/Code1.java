package com.wyr.dp.zuoshen.p26_27_28;



/**
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/qJnOS7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 */
public class Code1 {
    //（样本对应模型）,一个样本做行，一个样本做列，就以他们的结尾来做可能性

    //暴力递归版本
    public int longestCommonSubsequence1(String text1, String text2) {
        if(text1==null||text2==null||text1.length()==0||text2.length()==0){
            return 0;
        }
        char[] chs1=text1.toCharArray();
        char[] chs2=text2.toCharArray();

        return process(chs1,chs2,chs1.length-1,chs2.length-1);
    }

    //我们就从0~i和0~j的范围上去考虑
    public int process(char [] chs1, char[] chs2, int i, int j){
        if(i==0&&j==0){
            return chs1[i]==chs2[j]?1:0;
        }else if(i==0){
            if(chs1[i]==chs2[j]){
                return 1;
            }else{
                return process(chs1,chs2,i,j-1);
            }
        }else if(j==0){ //i!=0,j==0
            if(chs2[j]==chs1[i]){
                return 1;
            }else{
                return process(chs1,chs2,i-1,j);
            }
        }else{ //i!=0,j!=0
            //考虑三种情况

            //情况一：最长公共子序列，不以j结尾，可能以i结尾
            int p1=process(chs1, chs2, i,j-1);
            //情况二：最长公共子序列，不以i结尾，可能以j结尾
            int p2=process(chs1, chs2 , i-1, j);
            //情况三：最长公共子序列必须既以i结尾又以j结尾
            int p3=chs1[i]==chs2[j]?1+process(chs1,chs2, i-1, j-1):0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }



    //动态规划版本
    public static int longestCommonSubsequence2(String text1, String text2) {
        if(text1==null||text2==null||text1.length()==0||text2.length()==0){
            return 0;
        }
        char[] chs1=text1.toCharArray();
        char[] chs2=text2.toCharArray();
        int M=chs1.length;
        int N=chs2.length;
        int [][] dp=new int[M][N];
        dp[0][0]=chs1[0]==chs2[0]?1:0;
        for(int j=1;j<N;j++){
            if(chs1[0]==chs2[j]){
                dp[0][j]=1;
            }else{
                dp[0][j]=dp[0][j-1];
            }
        }

        for(int i=1;i<M;i++){
            if(chs1[i]==chs2[0]){
                dp[i][0]=1;
            }else{
                dp[i][0]=dp[i-1][0];
            }
        }

        for(int i=1;i<M;i++){
            for(int j=1;j<N;j++){
                //情况一：最长公共子序列，不以j结尾，可能以i结尾
                int p1=dp[i][j-1];
                //情况二：最长公共子序列，不以i结尾，可能以j结尾
                int p2=dp[i-1][j];
                //情况三：最长公共子序列必须既以i结尾又以j结尾
                int p3=chs1[i]==chs2[j]?1+dp[i-1][j-1]:0;
                dp[i][j]=Math.max(p1,Math.max(p2,p3));
            }
        }
        return dp[M-1][N-1];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence2("asd", "dad"));
    }

}
