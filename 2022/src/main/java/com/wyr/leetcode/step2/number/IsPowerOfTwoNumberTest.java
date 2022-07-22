package com.wyr.leetcode.step2.number;

public class IsPowerOfTwoNumberTest {
    /**
     * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 如果存在一个整数 x 使得n == 2x ，则认为 n 是 2 的幂次方。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/power-of-two
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isPowerOfTwo(int n) {
        if(n==0||n==Integer.MIN_VALUE)
            return false;
        return (n&(n-1))==0;
    }

    //怎么判断一个数是否是4的整数次幂呢
    //首先得是2的整数次幂，其次就是这个唯一出现的1需要出现在0,2,4,6...位置上
    public boolean isPowerOfFour(int n){
        if(n==0||n==Integer.MIN_VALUE)
            return false;
                                    //01010101..010101
        return (n&(n-1))==0&&(n& 0X55555555) !=0;
    }
}
