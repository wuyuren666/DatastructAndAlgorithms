package com.wyr.leetcode.step2.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LCR 043. 完全二叉树插入器
 * https://leetcode.cn/problems/NaqhDT/
 */
public class CBTInserter {

    // 双端队列，用来保存倒数第二层第一个非双全节点及其同层右侧的其他节点
    // 以及最后一层的所有节点
    // 因为新加的节点，肯定只能作为这些节点的左右孩子
    private Queue<TreeNode> queue = new LinkedList<>();

    private TreeNode root;

    private boolean isMeet = false;

    public CBTInserter(TreeNode root) {
        this.root = root;
        // Bfs遍历
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.size() != 0) {
            int size = q.size();
            for(int i=0;i<size;i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }

                if (isMeet) {
                    queue.offer(cur);
                    continue;
                }
                if (cur.left == null || cur.right == null) {
                    queue.offer(cur);
                    isMeet = true;
                }
            }
        }
    }

    public int insert(int v) {
        TreeNode cur = queue.peek();// 拿出来看
        TreeNode node = new TreeNode(v);
        if (cur.left == null) {
            cur.left = node;
        } else {
            cur.right = node;
            queue.poll();// 真正移除
        }
        queue.offer(node);
        return cur.val;
    }

    public TreeNode get_root() {
        return this.root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        CBTInserter cbtInserter = new CBTInserter(root);
        cbtInserter.insert(6);
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
