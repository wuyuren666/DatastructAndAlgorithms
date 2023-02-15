package com.wyr.leetcode.step2.dp;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesisTest {
    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * //两个if逻辑
     * //有左括号就用
     * //右括号个数大于左括号，就是用右括号，
     *
     * https://leetcode.cn/problems/generate-parentheses/
     */

    public List<String> generateParenthesis(int n) {
        //只要右括号个数大于左括号个数就放
        //只要有左括号就放
        List<String> result=new ArrayList<>();
        process(result,n,n,"");
        return result;
    }
    public void process(List<String> result, int leftCount , int rightCount , String tempStr){
        if(leftCount==0&&rightCount==0){
            result.add(tempStr);
            return;
        }
        if(leftCount>0){
            process(result,leftCount-1,rightCount, tempStr+"(");
        }
        if(rightCount>leftCount){
            process(result,leftCount,rightCount-1, tempStr+")");
        }
    }
}
