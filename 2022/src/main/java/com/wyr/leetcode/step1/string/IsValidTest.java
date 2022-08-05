package com.wyr.leetcode.step1.string;

import java.util.Stack;

public class IsValidTest {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * 输入：s = "()"
     * 输出：true
     *
     * 输入：s = "()[]{}"
     * 输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isValid(String s) {
        //如果是左括号就加入栈中，右括号的话就从栈中弹出一个和其匹配
        if((s.length()&1)!=0){//奇数肯定不行
            return false;
        }
        Stack<Character> stack=new Stack<>();
        char[] chs=s.toCharArray(); //转成数组做快一点
        for(int i=0;i<chs.length;i++){
            if(chs[i]=='('||chs[i]=='{'||chs[i]=='['){//左括号就如栈
                stack.push(chs[i]);
            }else{//右括号，就从栈中弹一个进行比较
                if(stack.isEmpty()||!isOk(chs[i],stack.pop())){
                    return false;
                }
            }
        }
        return stack.isEmpty()?true:false;
    }

    public boolean isOk(char a, char b){
        if(a=='}'&&b=='{'){
            return true;
        }else if(a==')'&&b=='('){
            return true;
        }else if(a==']'&&b=='['){
            return true;
        }
        return false;
    }
}
