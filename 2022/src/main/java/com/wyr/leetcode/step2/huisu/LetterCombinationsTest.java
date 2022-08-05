package com.wyr.leetcode.step2.huisu;

import java.util.*;

public class LetterCombinationsTest {
    /**
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<String>();
        }
        char[] chs = digits.toCharArray();
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> result = new ArrayList<>();
        process(result, chs, map, 0, "");
        return result;
    }

    public static void process(List<String> result, char[] chs, Map<Character, String> map, int index, String tempStr) {
        if (index == chs.length) {
            result.add(tempStr);
            return;
        }
        for (int i = 0; i < map.get(chs[index]).length(); i++) {
            process(result, chs, map, index + 1, tempStr + map.get(chs[index]).charAt(i));
        }
    }


    public static void main(String[] args) {
        /*letterCombinations("23");*/
        List<String> l1=new ArrayList<>();
        List<String> l2=new ArrayList<>();

        l1.add("a");
        l1.add("b");

        l2.add("b");
        l2.add("a");
        Set<List<String>> set=new HashSet<>();
        set.add(l1);
        set.add(l2);
        System.out.println("aaa");
    }
}
