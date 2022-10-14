package com.wyr.leetcode.step2.tree;

public class KthSmallestTest {
    /**
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     *
     * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
     */
    int res;
    int rank;
    public int kthSmallest(TreeNode root, int k) {
        process(root,k);
        return res;
    }

    public void process(TreeNode root,int k){
        if(root==null)
            return;
        process(root.left, k);
        /* 中序遍历代码位置 */
        rank++;
        if(rank==k){
            res=root.val;
            return;
        }
        /*****************/
        process(root.right,k);
    }
}
