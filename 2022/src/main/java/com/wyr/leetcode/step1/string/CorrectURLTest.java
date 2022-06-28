package com.wyr.leetcode.step1.string;


/**
 * https://\\//\\//wrs21.winshipways.com--->https:wrs21.winshipways.com
 */
public class CorrectURLTest {
    public static void main(String[] args) {
        System.out.println(correctURL("https://\\\\//\\\\//wrs21.winshipways.com"));

    }

    public static String correctURL(String url){
        String[] strings = url.split(":");
        String[] split = strings[1].split("\\.");
        int index1=split[0].lastIndexOf('/');
        int index2=split[0].lastIndexOf('\\');
        int index=index1>index2?index1:index2;
        split[0]=split[0].substring(index+1);
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if(i==split.length-1)
                sb.append(split[i]);
            else
                sb.append(split[i]+".");
        }
        return strings[0]+":"+sb;
    }
}
