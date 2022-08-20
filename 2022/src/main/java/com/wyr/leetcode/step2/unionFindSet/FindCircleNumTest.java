package com.wyr.leetcode.step2.unionFindSet;

public class FindCircleNumTest {
    /**
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。
     * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     *
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     *
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
     * 而 isConnected[i][j] = 0 表示二者不直接相连。返回矩阵中 省份 的数量。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/bLyHh0
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */



    public int findCircleNum(int[][] isConnected) {
        int N=isConnected.length;
        int [] father=new int[N];
        for(int i=0;i<N;i++){
            father[i]=i;
        }

        /*for(int j=1;j<N;j++){ //列
            for(int i=0;i<j;i++){//行
                if(isConnected[i][j]==1){
                    union(father,i,j);
                }
            }
        }*/

        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(isConnected[i][j]==1){
                    if(findFather(father,i)!=findFather(father,j)){
                        union(father,i,j);
                    }
                }
            }
        }

        return getSetNum(father);
    }


    public int findFather(int [] father ,int index){
        if(father[index]!=index){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }

    public void union(int [] father, int index1, int index2){
        father[findFather(father,index1)]=findFather(father,index2);
    }

    public int getSetNum(int [] father){
        int res=0;
        for(int i=0; i< father.length; i++){
            if(father[i]==i){
                res++;
            }
        }
        return res;
    }
}
