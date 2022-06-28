package com.wyr.zuoshen.zuoshen1.p12;

public class KMP {
    /**
     * KMP算法 （用来求一个字符串是否是另一个字符串的子串）
     */
    public static int getIndexOf(String s, String m){
        if(s==null||m==null||m.length()<1||s.length()<m.length()){
            return -1;
        }
        char[] str1=s.toCharArray();
        char[] str2=s.toCharArray();
        int i1=0;
        int i2=0;
        int [] next=getNextArray(str2);

        while (i1<str1.length&&i2<str2.length){
            if (str1[i1]==str2[i2]){
                i1++;
                i2++;
            }else if(next[i2]==-1){//str2中比对的位置已经无法往前跳了
                i1++;
            }else {
                i2=next[i2];
            }
        }
        //i1越界 或者i2越界
        return i2==str2.length?i1-i2:-1;
    }





    public static int [] getNextArray(char [] ms){
        if(ms.length==1){
            return new int[]{-1};
        }
        int [] next=new int[ms.length];
        next[0]=-1;
        next[1]=0;
        int i=2;//next数组的位置
        int cn=0;
        while (i<next.length){
            if(ms[i-1]==ms[cn]){
                next[i++]=++cn;
            }else if(cn>0){
                cn=next[cn];
            }else {
                next[i++]=0;
            }
        }
        return next;
    }
}
