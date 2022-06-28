package com.wyr.leetcode.step2.number;

public class AddBinaryTest {
    /**
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     *
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
        int lengthA = a.length();
        int lengthB = b.length();
        char [] longArr;
        char [] smallArr;
        //首先转化为字符数组
        if (lengthA >= lengthB) {
            longArr = new StringBuilder(a).reverse().toString().toCharArray();
            smallArr = new StringBuilder(b).reverse().toString().toCharArray();
        } else {
            longArr = new StringBuilder(b).reverse().toString().toCharArray();
            smallArr = new StringBuilder(a).reverse().toString().toCharArray();
        }


        int info = 0;//进位信息
        for (int i = 0; i < smallArr.length; i++) {
            int binaryNum =( longArr[i]-'0') + (smallArr[i]-'0') + info;
            if (binaryNum >= 2) {
                if(binaryNum % 2==0){
                    longArr[i] = '0';
                }else {
                    longArr[i] = '1';
                }
                info = 1;
            } else {
                if(binaryNum==0){
                    longArr[i] ='0';
                }else{
                    longArr[i] ='1';
                }
                info = 0;
            }
        }

        for (int i = smallArr.length; i < longArr.length; i++) {
            int binaryNum =( longArr[i]-'0') + info;
            if (binaryNum >= 2) {
                if(binaryNum % 2==0){
                    longArr[i] = '0';
                }else {
                    longArr[i] = '1';
                }
                info = 1;
            } else {
                if(binaryNum==0){
                    longArr[i] ='0';
                }else{
                    longArr[i] ='1';
                }
                info = 0;
            }
        }

        StringBuilder result = new StringBuilder(new String(longArr));
        if (info == 0) {
            return result.reverse().toString();
        } else {
            return result.append(1).reverse().toString();
        }

    }

    public static void main(String[] args) {

        System.out.println(addBinary("11", "1"));
    }

}
