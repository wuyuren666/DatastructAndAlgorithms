package com.wyr.leetcode.step2.dp;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesisTest {
    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * https://leetcode.cn/problems/generate-parentheses/
     */

    public List<String> generateParenthesis(int n) {
        List<String> res=new ArrayList<>();
        process(n,n,"",res);
        return res;
    }
    public void process(int left, int right, String curStr, List<String> res){
        if(left==0&&right==0){//baseCase
            res.add(curStr);
        }

        //如果左括号还剩余的话，添加左括号
        if(left >0){
            process(left-1,right,curStr+"(",res);
        }
        //如果右括号>左括号的话，加右括号
        if(right>left){
            process(left,right-1,curStr+")",res);
        }

    }
}
