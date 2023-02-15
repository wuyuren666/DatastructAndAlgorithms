package com.wyr.leetcode.step1.number;

public class HammingWeightTest {
    /**
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
     *
     * 输入：n = 11 (控制台输入 00000000000000000000000000001011)
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //每次找到最右边的那一个1，然后消去，直到为0
    public int hammingWeight(int n) {
        int count=0;

        while(n!=0){
            int rightOne=n&((~n)+1); //找到最右边的1
            count++; //计数
            n=n^rightOne; //消去最右边的1
        }
        return count;
    }
}
