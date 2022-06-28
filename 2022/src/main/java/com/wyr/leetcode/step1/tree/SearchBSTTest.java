package com.wyr.leetcode.step1.tree;

public class SearchBSTTest {

    /**
     * 给定二叉搜索树（BST）的根节点root和一个整数值val。
     *
     * 你需要在 BST 中找到节点值等于val的节点。 返回以该节点为根的子树。 如果节点不存在，则返回null。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/search-in-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public TreeNode searchBST(TreeNode root, int val) {
        if (root.val == val) {
            return root;
        } else if (root.left != null && root.val > val) {
            return searchBST(root.left, val);
        } else if (root.right != null && root.val < val) {
            return searchBST(root.right, val);
        } else {
            return null;
        }
    }

    public class TreeNode {
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
}
