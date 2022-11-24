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
    List<List<Integer>> ans=new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root==null){
            return ans;
        }
        List<Integer> tmpList=new ArrayList<>();
        tmpList.add(root.val); //根节点存在先加入
        process(root,targetSum,root.val,tmpList);
        return ans;
    }


    public void process(TreeNode root, int targetSum, int tmpSum, List<Integer> tmpList){
        if(root.right==null&&root.left==null){//到达了叶子节点
            if(tmpSum==targetSum){
                ans.add(new ArrayList<>(tmpList));
            }
            return;
        }

        if(root.left==null&&root.right!=null){//只能往右走
            tmpList.add(root.right.val);
            process(root.right,targetSum,tmpSum+root.right.val,tmpList);
            tmpList.remove(tmpList.size()-1);
        }else if(root.left!=null&&root.right==null){//只能往左走
            tmpList.add(root.left.val);
            process(root.left,targetSum,tmpSum+root.left.val,tmpList);
            tmpList.remove(tmpList.size()-1);
        }else{
            //既可以往左走，也可以往右走
            tmpList.add(root.right.val);
            process(root.right,targetSum,tmpSum+root.right.val,tmpList);
            tmpList.remove(tmpList.size()-1);

            tmpList.add(root.left.val);
            process(root.left,targetSum,tmpSum+root.left.val,tmpList);
            tmpList.remove(tmpList.size()-1);
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
