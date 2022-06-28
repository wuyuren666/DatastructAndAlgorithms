package com.wyr.dp.zuoshen.p35_36;

/**
 * 一个10X9的棋盘，棋盘的左下角的位置是(0,0)位置
 * 给你三个参数 x，y，k
 * 返回"马" 从(0,0)位置出发，必须走k步，最后落在(x,y)上的方法数有多少种？
 */
public class Code1 {

    //暴力递归版本
    public static int jump1(int x, int y ,int k){
        return process(0,0,k,x,y);
    }

    /**
     * 暴力递归版本
     * @param curX  当前横坐标位置
     * @param curY  当前纵坐标位置
     * @param rest  还剩多少步没走
     * @param aimX  目标横坐标位置
     * @param aimY  目标纵坐标位置
     * @return  有多少种方法
     */
    public static int process(int curX, int curY, int rest, int aimX, int aimY){
        //考虑越界的问题
        if(curX>9||curX<0||curY>8||curY<0){
            return 0;
        }
        if(rest==0){ //当还剩下0步时，当前位置如果来到目标位置，则找到一种方法
            return (curX==aimX&&curY==aimY?1:0);
        }
        //普遍情况，有八种方向可以走
        int ways=process(curX+1,curY+2,rest-1,aimX,aimY);
        ways+=process(curX+2,curY+1,rest-1,aimX,aimY);
        ways+=process(curX+2,curY-1,rest-1,aimX,aimY);
        ways+=process(curX+1,curY-2,rest-1,aimX,aimY);
        ways+=process(curX-1,curY-2,rest-1,aimX,aimY);
        ways+=process(curX-2,curY-1,rest-1,aimX,aimY);
        ways+=process(curX-2,curY+1,rest-1,aimX,aimY);
        ways+=process(curX-1,curY+2,rest-1,aimX,aimY);

        return ways;
    }



    //动态规划版本
    public static int jump2(int x, int y ,int k){
        int[][][] dp=new int[10][9][k+1];//需要一个三维数组
        //当我们分析位置依赖的时候，发现，同层的位置不互相依赖
        //只有上一层的位置会依赖于下一层的位置
        //所以，我们从下往上填
        //第0层，根据basecase得出
        dp[x][y][0]=1;
        for(int rest=1;rest<=k;rest++){ //从第一层开始，一层一层的往上填
            for(int i=0;i<10;i++){
                for(int j=0;j<9;j++){
                    //普遍情况，有八种方向可以走
                    int ways=pick(dp,i+1,j+2,rest-1);
                    ways+=pick(dp,i+2,j+1,rest-1);
                    ways+=pick(dp,i+2,j-1,rest-1);
                    ways+=pick(dp,i+1,j-2,rest-1);
                    ways+=pick(dp,i-1,j-2,rest-1);
                    ways+=pick(dp,i-2,j-1,rest-1);
                    ways+=pick(dp,i-2,j+1,rest-1);
                    ways+=pick(dp,i-1,j+2,rest-1);
                    dp[i][j][rest]=ways;
                }
            }
        }
        return dp[0][0][k];
    }


    public static int pick(int[][][] dp, int x, int y, int rest){
        if(x>9||x<0||y>8||y<0){
            return 0;
        }
        return dp[x][y][rest];
    }



    public static void main(String[] args) {
        System.out.println(jump1(7, 7, 10));
        System.out.println(jump2(7, 7, 10));
    }

}
