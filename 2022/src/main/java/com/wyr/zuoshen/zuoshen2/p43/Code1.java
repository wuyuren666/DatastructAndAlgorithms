package com.wyr.zuoshen.zuoshen2.p43;


/**
 * 给定一个二叉树的头节点
 * 返回这棵二叉树中最大的二叉搜索子树的节点个数
 */
public class Code1 {


    //所有子树需要返回的信息
    public static class Info{
        public boolean isAllBST;
        public int maxSubBSTSize;
        public int min; //整棵树的最小值
        public int max; //整棵树的最大值

        public Info(boolean isBST, int maxSubBSTSize, int min, int max) {
            this.isAllBST = isBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }


    public static Info process(Node root){
        if(root==null){
            return null;
        }
        Info leftInfo=process(root.left);
        Info rightInfo=process(root.right);


        int min=root.value;
        int max=root.value;
        //左树不为空
        if(leftInfo!=null){
            min=Math.min(min, leftInfo.min);
            max=Math.max(max, leftInfo.max);
        }
        //右树不为空
        if(rightInfo!=null){
            min=Math.min(min, rightInfo.min);
            max=Math.max(max, rightInfo.max);
        }
        int maxSubBSTSize=0;
        //maxSubBSTSize与root无关，可能性一
        if(leftInfo!=null){
            maxSubBSTSize= leftInfo.maxSubBSTSize;
        }
        if(rightInfo!=null){
            maxSubBSTSize= Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }
        boolean isAllBST=false;

        //可能性二：maxSubBSTSize与root有关
        //左树整体需要是二叉搜索树&&右树整体是二叉搜索树&&左树最大值<root.value&&右树的最小值>=root.value
        if((leftInfo==null?true: leftInfo.isAllBST)&&(rightInfo==null?true: rightInfo.isAllBST)
                &&(rightInfo==null?true:leftInfo.max<root.value)&&(rightInfo==null?true:rightInfo.min>root.value)){
                maxSubBSTSize= (leftInfo==null?0:leftInfo.maxSubBSTSize)+
                        (rightInfo==null?0:rightInfo.maxSubBSTSize)+1;
                isAllBST=true;
        }


        return new Info(isAllBST,maxSubBSTSize,min,max);
    }




    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

}
