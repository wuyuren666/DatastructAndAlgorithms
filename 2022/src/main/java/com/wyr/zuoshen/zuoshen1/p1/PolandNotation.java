package com.wyr.zuoshen.zuoshen1.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        // (3+4)*5-6 -> 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList = " + rpnList);

        int res = caculate(rpnList);
        System.out.println("计算的结果是:" + res);
    }


    /**
     * 将一个逆波兰表达式，依次将数据和运算符 放入到ArrayList中
     */
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 完成逆波兰表达式的运算
     */
    public static int caculate(List<String> ls) {
        //只需一个栈
        Stack<String> stack = new Stack<String>();
        //遍历List
        for (String item : ls) {
            //正则表达式
            if (item.matches("\\d+")) { //匹配的是多位数
                stack.push(item);
            } else {
                // pop出两个数，并运算再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误！");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
