package com.wyr.zuoshen.zuoshen1.p8;

import java.util.*;

public class Permutation {
    /**
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     *
     *  输入："aac"
     *  输出：["aac","caa","aca"]
     *
     *  输入："abc"
     *  输出：["abc","acb","bac","bca","cab","cba"]
     *
     *  https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/submissions/
     */

    /**
     *执行用时：48 ms, 在所有 Java 提交中击败了12.25%的用户
     *内存消耗：49.6 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    Set<String> res=new HashSet<>();
    public  String[] permutation(String s) {
        char[] chs=s.toCharArray();
        int [] used=new int[chs.length]; //当前字符是否使用过
        process(chs,used,"");
        String[] ans =res.toArray(new String[0]);
        return ans;
    }


    public void process214(char[] chs, int []used, String tmpStr){
        if(tmpStr.length()==chs.length){
            res.add(tmpStr);
            return;
        }

        for(int i=0;i<used.length;i++){
            if(used[i]==0){
                used[i]=1;
                process214(chs,used,tmpStr+chs[i]);
                used[i]=0;
            }
        }

    }



    public void process(char[] chs, int[] used, String tempStr){
        if(tempStr.length()==chs.length){
            res.add(tempStr);
        }
        for(int i=0;i<chs.length;i++){
            if(used[i]==0){//当前字符没有被使用过
                //标记当前字符已经用过
                used[i]=1;
                process(chs,used,tempStr+chs[i]);
                //取消标记
                used[i]=0;
            }
        }
    }

    /**
     * 执行用时：10 ms, 在所有 Java 提交中击败了64.87%的用户
     * 内存消耗：46.1 MB, 在所有 Java 提交中击败了65.65%的用户
     */

    public static String[] permutation1(String s) {
        char[] c=s.toCharArray();
        Arrays.sort(c);
        List<String> list=new ArrayList<>();
        list.add(String.valueOf(c));
        int beginIndex=0;
        while((beginIndex=getReverseIndex(c))!=-1){
            int minIndex=getMinValueIndex(beginIndex,c);
            swap(c,beginIndex-1,minIndex);
            Arrays.sort(c,beginIndex,c.length);
            list.add(String.valueOf(c));
        }
        String[] res=new String[list.size()];
        for(int i=0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }

    //找到逆序区第一个数的下标
    public static int getReverseIndex(char[] c){
        for(int i=c.length-1;i>=1;i--){
            if(c[i]>c[i-1]){
                return i;
            }
        }
        return -1;//代表没有逆序区
    }


    //找到逆序区中大于当前元素的最小的元素的下标
    public static int getMinValueIndex(int index,char[] c){
        //index:逆序区的第一个元素的下标
        if(index+1==c.length){ //逆序区中就一个元素
            return index;
        }
        char temp=Character.MIN_VALUE;//临时变量
        int minIndex=-1;//临时变量
        boolean flag=false;
        for(int i=index; i<c.length;i++){
            if(c[i]>c[index-1]){
                if(!flag){//第一个满足大于temp的字符，会进入这个if逻辑
                    temp=c[i];
                    minIndex=i;
                    flag=true;
                }else {
                    if(c[i]<temp){
                        temp=c[i];
                        minIndex=i;
                    }
                }
            }
        }
        return minIndex;
    }

    public static void swap(char [] c, int i, int j){
        char temp=c[i];
        c[i]=c[j];
        c[j]=temp;
    }
    public static void main(String[] args) {
        //permutation("abc");
        permutation1("aac");
    }
}
