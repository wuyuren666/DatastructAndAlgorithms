package com.wyr.bigFactory.date9_1;

import java.math.BigInteger;

public class DoubleRedTest {
    /**
     * 京东笔试题
     *
     *
     * 给一个n，代表字符串的长度，问这个长度为n的字符串，包含有两个red的可能性有多少种？
     * 字符串由小写字母组成
     */

    public static void main(String[] args) {
        /**
         * dp1[i]=26^(i-3)+(26*dp1[i-1]-dp1[i-3])
         * dp2[i]=dp1[i-3]+(26*dp2[i-1]-dp2[i-3])
         *
         * dp1[i]：长度为i的字符串至少包含有一个red的可能性有多少种
         *  1）长度为i的字符串中以red结尾，可能性为：26^(i-3)
         *  2) 长度为i的字符串不以red结尾
         *          .....XXa， 以a结尾   dp1[i-1]
         *          .....XXb， 以b结尾   dp1[i-1]
         *          .....XXc， 以c结尾   dp1[i-1]     ==> 25*dp1[i-1]
         *          .....XXe， 以e结尾   dp1[i-1]
         *        ...
         *          .....XXz， 以z结尾   dp1[i-1]
         *
         *          以d结尾的单独拿出来讨论
         *               (1) .....red，这种情况已经在1)中讨论过了，所以可能性为dp1[i-1]-dp1[i-3]
         */

    }





}
