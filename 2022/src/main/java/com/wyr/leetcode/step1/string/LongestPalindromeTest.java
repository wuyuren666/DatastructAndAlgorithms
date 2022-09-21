package com.wyr.leetcode.step1.string;


public class LongestPalindromeTest {


    /**
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     *
     * 回文字符串 是正着读和倒过来读一样的字符串。
     *
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     *
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     *
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     *
     *
     * 输入：s = "aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/palindromic-substrings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int countSubstrings(String s) {
        if(s.length()==1){ //长度为1的字符串直接返回
            return 1;
        }

        int N=s.length();//记录字符串的长度

        char[] chs=s.toCharArray(); //转化为字符数组

        int [][] dp=new int [N][N];
        //1代表dp[L][R]是s[L..R]是回文串
        //-1代表dp[L][R]是s[L..R]不是回文串

        int result=0; //记录个数


        //先填主对角线，同时记录个数
        for(int L=0;L<N;L++){
            result++;
            dp[L][L]=1;
        }
        //再填主对角线右边的那条对角线，同时记录个数
        for(int R=1;R<N;R++){
            dp[R-1][R]=chs[R-1]==chs[R]?1:-1;
            if(dp[R-1][R]==1){
                result++;
            }
        }

        //接下来填剩余部分，同时记录个数
        for(int R=2;R<N;R++){
            for(int L=0;L<R-1;L++){
                if(dp[L+1][R-1]==1&&chs[L]==chs[R]){ //dp[L][R]依赖于dp[L+1][R-1]
                    result++;
                    dp[L][R]=1;
                }else{
                    dp[L][R]=-1;
                }
            }
        }
        return result;
    }




    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * https://leetcode.cn/problems/longest-palindromic-substring/
     */

    public static String longestPalindrome1(String s) {
        if(s.length()==1){ //长度为1的字符串直接返回
            return s;
        }

        int N=s.length();//记录字符串的长度

        char[] chs=s.toCharArray(); //转化为字符数组

        int [][] dp=new int [N][N];
        //1代表dp[L][R]是s[L..R]是回文串
        //-1代表dp[L][R]是s[L..R]不是回文串

        int startIndex=-1; //保存最长回文子串的开始下标
        int length=0; //保存最长回文字串的长度

        //先填主对角线，同时记录下标和长度
        for(int L=0;L<N;L++){
            startIndex=L;
            length=1;
            dp[L][L]=1;
        }
        //再填主对角线右边的那条对角线，同时记录下标和长度
        for(int R=1;R<N;R++){
            dp[R-1][R]=chs[R-1]==chs[R]?1:-1;
            if(dp[R-1][R]==1){
                startIndex=R-1;
                length=2;
            }
        }

        //接下来填剩余部分，同时记录下标和长度
        for(int R=2;R<N;R++){
            for(int L=0;L<R-1;L++){
                if(dp[L+1][R-1]==1&&chs[L]==chs[R]){ //dp[L][R]依赖于dp[L+1][R-1]
                    if(R-L+1>length){
                        startIndex=L;
                        length=R-L+1;
                    }
                    dp[L][R]=1;
                }else{
                    dp[L][R]=-1;
                }
            }
        }
        return s.substring(startIndex,startIndex+length);
    }




    /**
     * 给定一个包含大写字母和小写字母的字符串s，返回通过这些字母构造成的 最长的回文串。
     *
     * 在构造过程中，请注意 区分大小写 。比如"Aa"不能当做一个回文字符串。
     *
     * 输入:s = "abccccdd"
     * 输出:7
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-palindrome
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static int longestPalindrome2(String s) {
        int[] hashTable = new int[128];//哈希表
        char[] chs = s.toCharArray();

        for (int i = 0; i < chs.length; i++) {
            hashTable[chs[i]]++;
        }
        //取偶数，或者取大于 2 的奇数并且 - 1。
        //返回结果时，判断 n 是不是等于我们取的数值，如果等于，直接返回，反之 + 1 在返回。
        // 因为回文串正中间允许有一个不需要左右呼应的字符。

        int result = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] > 0) {//取偶数，或者取大于 2 的奇数并且 - 1。
                if ((hashTable[i] & 1) == 0) { //是偶数我们就用
                    result += hashTable[i];
                } else if ((hashTable[i] & 1) != 0 && hashTable[i] > 2) {//个数大于 2 的奇数用 n - 1 个。
                    result += hashTable[i] - 1;
                }
            }
        }
        //如果结果==原字符串的长度，说明全都是偶数
        //如果不等，说明我们已经尽量将所有的偶数算上了，只需要在中间+1即可构成最长的回文串
        return result == chs.length ? result : result + 1;

    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome1("abcdzjudcba"));
    }



}
