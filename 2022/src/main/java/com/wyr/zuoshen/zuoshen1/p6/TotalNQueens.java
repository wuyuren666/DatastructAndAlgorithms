package com.wyr.zuoshen.zuoshen1.p6;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题 研究的是如何将 N个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TotalNQueens {

    public int totalNQueens(int n) {
        //采用深度优先遍历，每一行，一行的去进行尝试
        int [] record=new int[n];//一个记录，用来record[0]=2：代表第零行的皇后放在下标为2的位置
        return process(record,0,n);
    }

    //i:代表行的下标，开始的时候我从0行开始尝试放
    public int process(int[] record,int i,int n){
        if(i==n){ //当前行号竟然越界了，代表肯定找到了一种方法
            return 1;
        }
        int res=0;
        //开始对每一个列进行尝试
        for(int j=0;j<n;j++){
            if(isValid(record,i,j)){
                record[i]=j;
                //进入到下一行
                res+=process(record,i+1,n);
            }
        }
        return res;
    }
    //返回皇后放在了i行j列是否有效
    public static boolean isValid(int [] record, int row, int col){
        for(int k=0;k<row;k++){ //k:代表之前第k行的皇后
            //之前第k行的皇后的列，与当前我尝试放的列相同
            //之前第k行的皇后的列，与当前我尝试放的列在同一斜线上
            //如何表示是否在同一斜线上，我们用斜率来表示
            if(record[k]==col||Math.abs(k-row)==Math.abs(record[k]-col)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        totalNQueens1(4);
    }

    static List<List<String>> ans =new ArrayList<>();
    static List<List<Integer>> res =new ArrayList<>();
    public static int totalNQueens1(int n) {
        //采用深度优先遍历，每一行，一行的去进行尝试
        int [] record=new int[n];//一个记录，用来record[0]=2：代表第零行的皇后放在下标为2的位置
        process1(record,0,n,new ArrayList<>());
        return 1;
    }

    //i:代表行的下标，开始的时候我从0行开始尝试放
    public static void process1(int[] record, int i, int n, List<Integer> tempList){
        if(i==n){ //当前行号竟然越界了，代表肯定找到了一种方法
            res.add(new ArrayList<>(tempList));
            return;
        }
        //开始对每一个列进行尝试
        for(int j=0;j<n;j++){
            if(isValid(record,i,j)){
                tempList.add(j);
                record[i]=j;
                process1(record,i+1,n,tempList);
                tempList.remove(tempList.size()-1);
            }
        }
    }


}
