package com.wyr.zuoshen.zuoshen1.p5;

/**
 * 现实生活中，我们用一张纸条
 * 对折一次，会有一条凹折横
 * 对折两次，折横从上往下为：凹，凸，凹
 * 对折三次，折横从上往下为：凹，凹，凸，凹，凹，凸，凸
 *
 * 其实，折对应了一棵root节点为凹，左孩子为凹，右孩子为凸的二叉树
 * 只要，我们采用中序遍历，即可得到正确的顺序
 */
public class FoldPaper {
    public static class Node{
        public boolean flag;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(boolean flag){
            this.flag=flag;
        }
    }

    /**
     * @param i 代表当前的层数
     * @param N 代表共对折几次，即二叉树的深度
     * @param flag  flag为true代表凹，false代表凸
     */
    public static void process(int i, int N ,boolean flag){
        if(i>N) //baseCase
            return;
        process(i+1,N,true);
        System.out.println(flag==true?"凹":"凸");
        process(i+1,N,false);
    }

    public static void main(String[] args) {
        //flag为true代表凹，false代表凸
        int N=3;
        process(1,N,true);
    }
}
