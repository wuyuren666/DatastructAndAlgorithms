package com.wyr.leetcode.step1.dp;

public class NumWaysTest {
    /**
     * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
     *
     * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
     * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
     * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
     * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
     *
     * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
     *
     * 输出：3
     *
     * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/chuan-di-xin-xi
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //暴力递归
    public int numWays(int n, int[][] relation, int k) {
        int [][] dp=new int[n][n];
        for(int i=0;i<relation.length;i++){
            dp[relation[i][0]][relation[i][1]]=1;
        }
        return process(dp,0,n-1,k);
    }
    //index：当前到达几号；aim：目标是几号；K：可以走几步
    public int process(int [][] relation, int index, int aim, int k){
        if(k==0){
            if(index==aim){
                return 1;
            }else{
                return 0;
            }
        }
        int res=0;
        for(int j=0;j<relation[0].length;j++){
            if(relation[index][j]!=0){
                res+=process(relation,j,aim,k-1);
            }
        }
        return res;
    }


}
