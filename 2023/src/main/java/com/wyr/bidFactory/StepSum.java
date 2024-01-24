package com.wyr.bidFactory;

/**
 * 思想：二分
 *
 * 定义何为stepSum？
 * 比如680，680+68+6+0=754，则680的stepSum叫754
 * 给定一个正整数，判断它是不是某一个数的stepSum
 *
 * 思路：就拿上面的例子，754是680的stepSum，721是650的stepSum，我们发现这是有单调性的
 * 单调性就是指，680的stepSum一定是大于650的stepSum的
 * 也就是说，我们要看一个数n是不是另一个数m的stepSum，我们只需要在0~n之间找就行了
 * 又因为上面所说的单调性，所以就可以利用二分了
 * 具体我们一起看代码
 */
public class StepSum {

    public static boolean hasStepSum(int stepSum) {
        int left=0;
        int right=stepSum;
        boolean res=false;
        while(left<=right){
            int mid = (left+right)>>1;
            if(getStepSum(mid)==stepSum){
                res=true;
                System.out.println(stepSum+"是"+mid+"的stepSum");
                break;
            }else if(getStepSum(mid)<stepSum){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return res;
    }

    public static int getStepSum(int n){
        //680-->680+68+6
        int tmp=n;
        int sum = 0;
        while(tmp!=0){
            sum+=tmp;
            tmp/=10;
        }
        return sum;
    }

    public static void main(String[] args) {
        //System.out.println("650的stepSum是："+getStepSum(650));
        //754,721
        System.out.println(hasStepSum(721));
    }
}
