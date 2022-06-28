package com.wyr.zuoshen.zuoshen1.p5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 */
public class LowestAncestor {

    /**
     * 第一种方法：比较容易想，代码多一点
     * 首先，为这棵树的所有的节点维护一张父亲表
     * 然后，将第一个节点，所有向上的父亲，放在set集合中
     * 接着，将第二个节点，也从下往上，遍历所有的父节点，第一次与set中的父亲节点相交的节点就是，最近公共祖先
     */
    public static BinaryTreesNode lowestAncestor(BinaryTreesNode root, BinaryTreesNode o1,BinaryTreesNode o2){
        Map<BinaryTreesNode,BinaryTreesNode> parentMap=new HashMap<>();
        Set<BinaryTreesNode> set1=new HashSet<>();
        parentMap.put(root,root);//先放入根节点，root的祖先即自己
        process(root,parentMap);//递归调用

        BinaryTreesNode cur=o1; //辅助指针
        while (cur!=parentMap.get(cur)){ //将o1的父节点放入set集合中（没有放root）
            set1.add(cur);
            cur=parentMap.get(cur);
        }

        cur=o2;
        while (cur!=parentMap.get(cur)){ //从下往上得到o2的父节点，碰到第一个相交节点即返回
            if(set1.contains(cur)){
                return cur;
            }
            cur=parentMap.get(cur);
        }
        //上面的while没有遇到，那么肯定在root处相交了
        return root;
    }

    //先序遍历，同时维护map（map集合可以存入key为null的键值对集合）
    public static void process(BinaryTreesNode root,Map<BinaryTreesNode,BinaryTreesNode> parentMap){
        if(root==null)
            return;
        parentMap.put(root.left,root);
        parentMap.put(root.right,root);
        process(root.left,parentMap);
        process(root.right,parentMap);
    }


    /**
     * 第二种写法：
     * 两种情况
     * 情况一：o1是o2的最低公共祖先，或者o2是o1的最低公共祖先
     * 情况二：o1和o2不互为最低公共祖先，需要向上才能找到
     */
    public static BinaryTreesNode lowestAncestorProcess(BinaryTreesNode root, BinaryTreesNode o1,BinaryTreesNode o2){
        //在第一种情况中，baseCase会直接返回在o1或者o2（谁靠上返回谁）
        //在第二种情况中，baseCase直接返回自己：比如左孩子是o1，右孩子是o2，那么左边就会返回o1，右边就会返回o2
        if(root==null||root==o1||root==o2)
            return root;
        BinaryTreesNode left=lowestAncestorProcess(root.left,o1,o2);
        BinaryTreesNode right=lowestAncestorProcess(root.right,o1,o2);

        //左右子树返回都不为null，返回自己（相当于情况二）
        if(left!=null&&right!=null){
            return root;
        }
        //左右两棵树，并不都有返回值，返回非空的
        return left!=null?left:right;
    }




    //给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
    //节点值val满足区间 [0,n)
    public int lowestCommonAncestor (BinaryTreesNode root, int o1, int o2) {
        if(root==null){
            return -1;
        }
        if(root.value==o1||root.value==o2){
            return root.value;
        }
        int left=lowestCommonAncestor(root.left,o1,o2);
        int right=lowestCommonAncestor(root.right,o1,o2);

        if(left!=-1&&right!=-1){
            return root.value;
        }
        return left!=-1?left:right;
    }

}
