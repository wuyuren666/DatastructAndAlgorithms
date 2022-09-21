package com.wyr.leetcode.step2.number;

public class AddBinaryTest {
    /**
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     * 输入: a = "11", b = "1"
     * 输出: "100"
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     *
     *
     * https://leetcode.cn/problems/add-binary/
     */


    public static String addBinary(String a, String b) {
        int aLength=a.length();
        int bLength=b.length();
        char [] longChs;
        char [] shortChs;
        if(aLength>=bLength){
            longChs=new StringBuilder(a).reverse().toString().toCharArray();
            shortChs=new StringBuilder(b).reverse().toString().toCharArray();
        }else{
            longChs=new StringBuilder(b).reverse().toString().toCharArray();
            shortChs=new StringBuilder(a).reverse().toString().toCharArray();
        }

        int jinWeiInfo=0;//进位信息

        int p1=0,p2=0;
        while(p2<shortChs.length){
            int curNum=(longChs[p1]-'0')+(shortChs[p2]-'0')+jinWeiInfo;
            if(curNum==0){
                longChs[p1]='0';
                jinWeiInfo=0;
            }else if(curNum==1){
                longChs[p1]='1';
                jinWeiInfo=0;
            }else if(curNum==2){
                longChs[p1]='0';
                jinWeiInfo=1;
            }else{
                longChs[p1]='1';
                jinWeiInfo=1;
            }
            p1++;
            p2++;
        }

        while(p1<longChs.length){
            int curNum=(longChs[p1]-'0')+jinWeiInfo;
            if(curNum==0){
                longChs[p1]='0';
                jinWeiInfo=0;
            }else if(curNum==1){
                longChs[p1]='1';
                jinWeiInfo=0;
            }else if(curNum==2){
                longChs[p1]='0';
                jinWeiInfo=1;
            }else{
                longChs[p1]='1';
                jinWeiInfo=1;
            }
            p1++;
        }
        if(jinWeiInfo!=0){
            return new StringBuilder(String.valueOf(longChs)).append('1').reverse().toString();
        }
        return new StringBuilder(String.valueOf(longChs)).reverse().toString();
    }

    public static void main(String[] args) {

        System.out.println(addBinary("11", "1"));
    }

}
