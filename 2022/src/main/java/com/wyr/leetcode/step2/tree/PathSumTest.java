package com.wyr.leetcode.step2.tree;

import java.util.ArrayList;
import java.util.List;

public class PathSumTest {
    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
     * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/path-sum-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> res=new ArrayList<>();
    List<Integer> tempList=new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum,0);
        return res;
    }

    public void dfs(TreeNode root,int targetSum,int tempSum){
        if(root==null){
            return;
        }
        //先将当前节点的值累加
        tempSum+=root.val;
        //来到叶子节点
        if(root.left==null&&root.right==null){
            if(tempSum==targetSum){
                List<Integer> list=new ArrayList<>(tempList);
                list.add(root.val);
                res.add(list);
            }
            return;
        }
        //加入
        tempList.add(root.val);
        dfs(root.left,targetSum,tempSum);
        dfs(root.right,targetSum,tempSum);
        //删除
        tempList.remove(tempList.size()-1);
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
