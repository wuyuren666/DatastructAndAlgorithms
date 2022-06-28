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
        List<String> result=new ArrayList<>();
        if(digits.equals(""))
            return result;
        HashMap<Character,String> map=new HashMap<>();
        map.put('2',"abc");map.put('3',"def");map.put('4',"ghi");
        map.put('5',"jkl");map.put('6',"mno");map.put('7',"pqrs");
        map.put('8',"tuv");map.put('9',"wxyz");
        digits+='0';//后面加一个0
        char [] chs=digits.toCharArray();
        process(0,chs,"",result,map.get(chs[0]),map);
        return result;
    }

    public static void process(int index, char [] chs, String perStr, List<String> result, String curStr, HashMap<Character,String> map){

        if(curStr==null){ //baseCase
            result.add(perStr);
            return;
        }

        for(int i=0; i<curStr.length();i++){

            String cp1=new String(perStr);
            cp1+=curStr.charAt(i);
            process(index+1,chs,cp1,result,map.get(chs[index+1]),map);
        }

    }

    public List<String> letterCombinations1(String digits) {
        List<String> result=new ArrayList<>();
        if(digits.equals(""))
            return result;
        String [] all={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        digits+='0';//后面加一个0
        char [] chs=digits.toCharArray();
        process1(0,chs,"",result,all[chs[0]-'0'],all);
        return result;
    }

    public void process1(int index, char [] chs, String perStr, List<String> result, String curStr, String [] all){

        if(curStr.equals("")){ //baseCase
            result.add(perStr);
            return;
        }

        for(int i=0; i<curStr.length();i++){

            String cp1=new String(perStr);
            cp1+=curStr.charAt(i);
            process1(index+1,chs,cp1,result,all[chs[index+1]-'0'],all);
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
