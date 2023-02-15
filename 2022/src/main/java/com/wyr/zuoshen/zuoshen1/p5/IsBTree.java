package com.wyr.zuoshen.zuoshen1.p5;

/**
 * 判断一棵树是否是平衡树
 * 平衡树的条件：
 *
 * 1：左子树是平衡树
 * 2：右子树是平衡树
 * 3：|左子树的高度-右子树的高度|<=1
 */
public class IsBTree {
    public static boolean isBTree(BinaryTreesNode root){
           return process(root).isBalanced;
    }

    public static class ResultType{
        public boolean isBalanced;//是否是平衡的
        public int height;//高度

        public ResultType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    //二叉树的套路
    public static ResultType process(BinaryTreesNode root){
        //baseCase
        if(root==null){
            return new ResultType(true,0);
        }

        //分别向自己的左树和右树要结果
        //左子树的返回结果
        ResultType leftResult=process(root.left);
        //右子树的返回结果
        ResultType rightResult=process(root.right);

        int height=Math.max(leftResult.height,rightResult.height)+1; //当前树的高度为，左子树右子树的最大高度+1

        boolean isBalanced=true;

        //左右子树都是平衡树，且高度差值的绝对值小于等于1
        if(leftResult.isBalanced==false||rightResult.isBalanced==false||Math.abs(leftResult.height-rightResult.height)>1){
            isBalanced=false;
        }

        return new ResultType(isBalanced,height);
    }
}
