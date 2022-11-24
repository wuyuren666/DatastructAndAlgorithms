package com.wyr.zuoshen.zuoshen1.p6;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 将数组中字符串进行拼接，要求所有字符串都进行拼接
 * 怎么拼接能让最后形成的大字符串有最小的字典序
 */
public class LowestLexicography {

    public static String lowestString(String[] strs){
        if(strs==null||strs.length==0){
            return "";
        }
        //比较a+b和b+a结合之后两个字符串的字典序，如果a+b>=b+a 那么b放前，否则a放前
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                //a+b的字典序小于b+a的字典序，(a+b).compareTo(b+a)是-1
                //负数的话，(代表先排a后排b)
                //正数的话，(代表先排b后排a)
                return (a+b).compareTo(b+a);
            }
        });
        String res="";
        for(int i=0;i<strs.length;i++){
            res+=strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String [] s={"a","b"};
        int ba = "ab".compareTo("ba");
        System.out.println(ba);
        System.out.println(lowestString(s));
    }
}
