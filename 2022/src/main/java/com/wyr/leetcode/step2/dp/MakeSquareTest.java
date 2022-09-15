package com.wyr.leetcode.step2.dp;

public class MakeSquareTest {

    /**
     * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i个火柴棒的长度。你要用 所有的火柴棍拼成一个正方形。
     * 你不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须使用一次 。
     *
     * 如果你能使这个正方形，则返回 true ，否则返回 false 。
     *
     * 输入: matchsticks = [1,1,2,2,2]
     * 输出: true
     * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
     *
     * 输入: matchsticks = [3,3,3,3,4]
     * 输出: false
     * 解释: 不能用所有火柴拼成一个正方形。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/matchsticks-to-square
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static boolean makesquare1(int[] matchsticks) {
        int sum=0;
        for(int num: matchsticks){
            sum+=num;
        }

        if(sum%4!=0)
            return false;

        int len=sum/4;
        return f(matchsticks,0,0,len,4);
    }

    //这是暴力递归，会超时
    //edges:还剩多少条边没有搞定；len:当前边已经搞定的长度
    public static boolean f(int []arr, int status, int cur, int len, int edges){
        if(edges==0)
            return true;

        boolean ans=false;
        //arr中，还没有尝试的火柴！全部是一遍
        for(int i=0;i< arr.length;i++) {
            if ((status & (1 << i)) == 0) {
                if(cur + arr[i] > len){ //不能用

                } else if (cur + arr[i] < len) {
                    ans |= f(arr, status | (1 << i), cur + arr[i], len, edges);
                } else {//cur + arr[i]==len
                    ans |= f(arr, status | (1 << i), 0, len, edges - 1);
                }
                if (ans) {
                    return true;
                }
            }
        }
        return ans;
    }
}
