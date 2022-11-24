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

    List<String> ans=new ArrayList<>();
    Map<Character,char []> map=new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")){
            return ans;
        }
        map.put('2',new char[]{'a','b','c'}); map.put('3',new char[]{'d','e','f'});
        map.put('4',new char[]{'g','h','i'}); map.put('5',new char[]{'j','k','l'});
        map.put('6',new char[]{'m','n','o'}); map.put('7',new char[]{'p','q','r','s'});
        map.put('8',new char[]{'t','u','v'}); map.put('9',new char[]{'w','x','y','z'});


        char [] chars=digits.toCharArray();
        process(chars,0,"");
        return ans;
    }

    public void process(char[] chars, int curIndex, String tempStr){
        if(curIndex==chars.length){
            ans.add(tempStr);
            return;
        }
        char[] s = map.get(chars[curIndex]);
        for(int i=0;i<s.length;i++){
            process(chars,curIndex+1,tempStr+s[i]);
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
