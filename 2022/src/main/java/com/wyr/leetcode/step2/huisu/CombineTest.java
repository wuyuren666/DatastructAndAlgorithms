package com.wyr.leetcode.step2.huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombineTest {
    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 你可以按 任何顺序 返回答案。
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * https://leetcode.cn/problems/combinations/
     */

    static List<List<Integer>> res=new LinkedList<>();
    public static List<List<Integer>> combine(int n, int k) {
        process(n,k,1,new LinkedList<Integer>());
        return res;
    }

    public static void main(String[] args) {
        combine(4,2);
    }

    //执行用时：51 ms, 在所有 Java 提交中击败了5.03%的用户
    public void process(int n, int k,  int curNum ,List<Integer> tempList){
        if(curNum>n){
            if(tempList.size()==k){
                res.add(tempList);
            }
            return;
        }
        //复制一份,加入当前的数
        List<Integer> copyList=new ArrayList<>(tempList);
        copyList.add(curNum);
        process(n,k,curNum+1,copyList);
        //不要当前的数
        process(n,k,curNum+1,tempList);
    }


    public static void process(int n, int rest, int curNum, LinkedList<Integer> tmpList){
        if(rest==0){
            res.add(new LinkedList<>(tmpList));
            return;
        }
        //剪枝
        //剩下的数+当前数-1<=n
        if(rest+curNum-1>n){
            return;
        }
        //要当前数字
        tmpList.add(curNum);
        process(n,rest-1,curNum+1,tmpList);
        //不要当前数字
        tmpList.removeLast();
        process(n,rest,curNum+1,tmpList);
    }
}
