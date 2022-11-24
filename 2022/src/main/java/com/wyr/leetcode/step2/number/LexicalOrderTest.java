package com.wyr.leetcode.step2.number;

import java.util.ArrayList;
import java.util.List;

public class LexicalOrderTest {
    /**
     * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
     *
     * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
     *
     * 示例 1：
     *
     * 输入：n = 13
     * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/lexicographical-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static List<Integer> ans=new ArrayList<>();
    public static List<Integer> lexicalOrder(int n) {
        for(int i=1;i<=9;i++){
            dfs(i,n);
        }
        return ans;
    }

    public static void dfs(int curNum, int n){
        if(curNum<=n){
            ans.add(curNum);
            for(int i=0;i<=9;i++){
                int nextNum=curNum*10+i;
                if(nextNum>n){
                    break;
                }
                dfs(n,nextNum);
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> list = lexicalOrder(13);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
