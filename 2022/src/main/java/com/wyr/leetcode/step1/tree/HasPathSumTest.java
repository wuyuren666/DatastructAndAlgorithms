package com.wyr.leetcode.step1.tree;

public class HasPathSumTest {
    /**
     * 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
     * 如果存在，返回 true ；否则，返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root,targetSum,0);
    }

    public boolean dfs(TreeNode root, int targetSum, int tempSum){
        if(root==null){
            return false;
        }
        tempSum+=root.val;
        //叶子节点
        if(root.left==null&&root.right==null){
            if(targetSum==tempSum)
                return true;
            else
                return false;
        }
        return dfs(root.left,targetSum,tempSum)||dfs(root.right,targetSum,tempSum);
    }

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        return dfs2(root,targetSum,root.val);
    }

    public boolean dfs2(TreeNode root, int targetSum, int tmpSum){
        if(root.left==null&&root.right==null){//到达了叶子结点
            if(tmpSum==targetSum){
                return true;
            }
            return false;
        }

        if(root.left!=null&&root.right==null){
            return dfs2(root.left,targetSum,tmpSum+root.left.val);
        }else if(root.left==null&&root.right!=null){
            return dfs2(root.right,targetSum,tmpSum+root.right.val);
        }else{
            return dfs2(root.left,targetSum,tmpSum+root.left.val)||dfs2(root.right,targetSum,tmpSum+root.right.val);
        }

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
