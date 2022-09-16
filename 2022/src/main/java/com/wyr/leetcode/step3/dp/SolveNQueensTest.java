package com.wyr.leetcode.step3.dp;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueensTest {
    /**
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     *
     * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
     *
     * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/n-queens
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<String>> res=new ArrayList<>(); //最终的返回结果
    List<List<Integer>> ans =new ArrayList<>(); //存储每一种解法中，每一个皇后所放的列下标
    public List<List<String>> solveNQueens(int n) {
        if(n==1){ //特殊情况
            List<String> temp=new ArrayList<String>();
            temp.add("Q");
            res.add(temp);
            return res;
        }
        if(n==2||n==3){ //没有解决方法
            return res;
        }
        int [] record=new int[n]; //record[i]：代表第i行的皇后放在第record[i]列上
        process(new ArrayList<Integer>(),record,0,n);
        //处理结果ans-->res
        for(List<Integer> list: ans){
            List<String> strList=new ArrayList<>();
            for(Integer integer: list){
                StringBuilder sb=new StringBuilder();
                for(int i=0;i<n;i++){
                    if(integer.equals(i)){
                        sb.append('Q');
                    }else{
                        sb.append('.');
                    }
                }
                strList.add(sb.toString());
            }
            res.add(strList);
        }
        return res;

    }

    /**
     * dfs+回溯
     * @param tempList  收集结果
     * @param record    记录皇后放置的位置
     * @param i         行下标
     * @param n         放置的皇后个数
     */
    public void process(List<Integer> tempList,int [] record, int i, int n){
        if(i==n){ //baseCase
            ans.add(new ArrayList<>(tempList));//这边需要new，回溯的过程会将tempList清空的
            return;
        }
        //从每一列开始尝试
        for(int j=0;j<n;j++){
            if(isValid(record,i,j)){
                record[i]=j;
                tempList.add(j);//加入
                process(tempList,record,i+1,n);
                tempList.remove(tempList.size()-1);//删除
            }
        }

    }

    //返回将皇后放在i行，j列是否有效
    public boolean isValid(int [] record, int i, int j){
        for(int k=0;k<i;k++){ //只需要关注i之前的第k行
            //列相同，或者在斜线上
            if(j==record[k]||Math.abs(i-k)==Math.abs(j-record[k]))
                return false;
        }
        return true;
    }
}
