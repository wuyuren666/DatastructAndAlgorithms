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
        if((s.length()&1)!=0){ //奇数字符肯定不行
            return false;
        }
        char[] chs=s.toCharArray();
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<chs.length;i++){
            if(chs[i]=='('||chs[i]=='{'||chs[i]=='['){//左括号入栈
                stack.push(chs[i]);
            }else{ //右括号则栈中弹出一个比较
                if(!stack.isEmpty()){ //栈不为空
                    if(!isOK(stack.pop(),chs[i])){
                        return false;
                    }
                }else{ //栈为空
                    return false;
                }

            }
        }
        if(stack.isEmpty()){ //最后栈空，返回true
            return true;
        }
        return false;
    }

    public boolean isOK(char a, char b){
        if(a=='{'&&b=='}'){
            return true;
        }else if(a=='('&&b==')'){
            return true;
        }else if(a=='['&&b==']'){
            return true;
        }
        return false;
    }
}
