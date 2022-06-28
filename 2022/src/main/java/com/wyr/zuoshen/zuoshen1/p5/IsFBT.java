package com.wyr.zuoshen.zuoshen1.p5;

/**
 * 判断一棵树是否是满二叉树：FBT（full binary tree）
 *
 * 满二叉树的条件： 节点个数==2^树的高度-1
 */
public class IsFBT {



    public static class Info{
        public int nodes;
        public int heights;
        public Info(int n,int h){
            this.nodes=n;
            this.heights=h;
        }
    }

    public static boolean isFBT(BinaryTreesNode root){
        if(root==null){ //只有一个节点
            return true;
        }
        Info process = process(root);
        int heights=process.heights;//以root为节点的这棵树的高度
        int nodes=process.nodes;//以root为节点的这棵树的节点个数

        if(nodes==Math.pow(2,heights)-1){
            return true;
        }else {
            return false;
        }
    }

    public static Info process(BinaryTreesNode root){
        if(root==null)
            return new Info(0,0);

        //向左子树要信息
        Info leftInfo=process(root.left);
        //向右子树要信息
        Info rightInfo=process(root.right);

        int nodes=leftInfo.nodes+rightInfo.nodes+1;//左子树的节点个数+右子树的节点个数+1

        int heights=Math.max(leftInfo.heights,rightInfo.heights)+1;//左右子树中较高的高度+1

        return new Info(nodes,heights);
    }

}
