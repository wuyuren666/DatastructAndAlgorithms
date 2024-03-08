package com.wyr.leetcode.step1.array;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 *
 * 示例 1:
 *
 * 输入: s = "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: s = "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: s = "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 *
 * https://leetcode.cn/problems/roman-to-integer/
 */
public class RomanToIntTest {
    public static int romanToInt(String s) {
        //I  V  X   L   C    D   M
        //1  5  10 50  100  500   1000
        char[] chars = s.toCharArray();
        int res=0;
        //"MCMXCIV"
        // 1000,100,800,10,80,1,3
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='V'||chars[i]=='X'||chars[i]=='L'||chars[i]=='C'||chars[i]=='D'||chars[i]=='M'){
                res+=process(chars,i);
            }else{
                res+=1;
            }
        }

        return res;
    }




    public static int process(char[] chars,int index){
        int res=0;
        switch(chars[index]){
            case 'V':
            case 'X':  if(index-1>=0&&chars[index-1]=='I'){
                if(chars[index]=='V'){
                    res=3;
                }else{
                    res=8;
                }
            }else {
                if (chars[index] == 'V') {
                    res = 5;
                } else {
                    res = 10;
                }
            }break;
            case 'L':
            case 'C':  if(index-1>=0&&chars[index-1]=='X'){
                if(chars[index]=='L'){
                    res=30;
                }else{
                    res=80;
                }
            }else{
                if(chars[index]=='L'){
                    res=50;
                }else{
                    res=100;
                }
            } break;

            case 'D':
            case 'M':  if(index-1>=0&&chars[index-1]=='C'){
                if(chars[index]=='D'){
                    res=300;
                }else{
                    res=800;
                }
            }else{
                if(chars[index]=='D'){
                    res=500;
                }else{
                    res=1000;
                }
            } break;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }
}
