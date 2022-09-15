package com.wyr.leetcode.step2.huisu;

import java.util.ArrayList;
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

    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        process1(n,k,1,new ArrayList<Integer>());
        return res;
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

    //执行用时：19 ms, 在所有 Java 提交中击败了17.95%的用户
    public void process1(int n, int k,  int curNum ,List<Integer> tempList){
        if(curNum>n){
            if(tempList.size()==k){
                res.add(new ArrayList<>(tempList));
            }
            return;
        }
        //要当前的数
        tempList.add(curNum);
        process1(n,k,curNum+1,tempList);
        //不要当前的数
        tempList.remove(tempList.size()-1);
        process1(n,k,curNum+1,tempList);
    }
}
