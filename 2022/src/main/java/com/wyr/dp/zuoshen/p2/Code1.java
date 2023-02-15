package com.wyr.dp.zuoshen.p2;


/**
 * 假设我们有 1,2,3,4
 * 当前位置cur=2
 * 目标位置aim=4
 * 规定走的步数K=4
 * 有哪些位置1～N
 * 问从cur出发走K步到aim有多少种走法？
 */




public class Code1 {

    //5.18练习
    //cur:当前所在的位置，aim目标位置，rest：剩余步数
    public static int process518(int N,int cur,int aim,int rest){
        //当没有剩余步数时
        if(rest==0){
            return cur==aim?1:0; //没有剩余步数时，来到了目标位置，表示找到了一种行走的方法
        }
        //还有剩余的步数时
        //如果当前位置在1位置，机器人只能向右走
        if(cur==1){
           return process518(N,cur+1,aim,rest-1);
        }
        //如果机器人来到了N位置，他只能向左走
        if(cur==N){
           return process518(N,cur-1,aim,rest-1);
        }
        //机器人在中间位置，他既可以向右也可以向左走
        return  process518(N,cur-1,aim,rest-1)+process518(N,cur+1,aim,rest-1);
    }







    public static int way1(int N,int aim,int cur,int K){
                return process1(N,aim,cur,K);
        }

    /**
     process1为暴力递归，可以优化为动态规划
     比如(7,10)代表现在在7位置还有10步可以走
     那么(7,10)-->(6,9)+(8,9)-->((5,8)+(7,8)) + ((7,8)+(9,8))
     很显然在(7,8)这个点上出现了重复，我们就可以使用动态规划去解决
     */
        public static int process1(int N,int aim,int cur,int rest){
            if(rest==0)//剩余的步数走完了
                return cur==aim?1:0;

            //步数没有走完

            //假如当前cur在1位置，他只能向右走
            if(cur==1)//1-->2
                return process1(N,aim,2,rest-1);

            //假如当前cur在N位置，他只能向左走
            if(cur==N)//N-->N-1
                return process1(N,aim,N-1,rest-1);

            //假如在中间，他既可以向左走，也可以向右走
            return process1(N,aim,cur-1,rest-1)
                    +process1(N,aim,cur+1,rest-1);
        }

    public static void main(String[] args) {
        System.out.println(way1(4,4,2,4));
    }
}
