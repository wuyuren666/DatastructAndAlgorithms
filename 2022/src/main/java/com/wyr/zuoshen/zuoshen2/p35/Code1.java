package com.wyr.zuoshen.zuoshen2.p35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code1 {
    /**
     * 注意子树和子结构是两个不同的概念,这题是子树
     *
     * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
     *
     * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，
     * 也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
     *
     *  输入：t1 = [1, 2, 3], t2 = [2]
     *  输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/check-subtree-lcci/
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //注意只有子数问题才可以使用序列化的方式
    public static boolean checkSubTree(TreeNode t1, TreeNode t2) {
        //将两棵树先序方式序列化
        String s1=serilize(t1);
        String s2=serilize(t2);
        if(s1.indexOf(s2)==-1){
            return false;
        }
        return true;
    }


    public static String serilize(TreeNode head){
        if(head==null){
            return "#_";
        }
        String result=head.val+"_";
        result+=serilize(head.left);
        result+=serilize(head.right);
        return result;
    }





    /**
     * 子树问题的递归写法
     *
     * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
     *
     * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
     *
     * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
     * 输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/subtree-of-another-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null||subRoot==null){
            return false;
        }
        //第一个可能兴是以subRoot为头的树和以root为头的树结构完全一致
        //下面就是递归去判断
        return isSub1(root,subRoot)||isSubtree(root.left,subRoot)||isSubtree(root.right,subRoot);
    }

    //这个递归是用来判断子树的
    public boolean isSub1(TreeNode A, TreeNode B){
        //两者都为空
        if(A==null&&B==null){
            return true;
        }
        //只要有一个为空另一个不为空
        if(A!=null&&B==null||A==null&&B!=null){
            return false;
        }
        //两者都不为空
        if(A.val!=B.val){
            return false;
        }
        return isSub1(A.left,B.left)&&isSub1(A.right,B.right);
    }




    /**
     * 二叉树的子结构问题 （只能使用递归的方法，不能使用序列化的方法）
     *
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     *
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     *
     * 例如:
     * 给定的树 A:
     *
     *    3
     *   / \
     *  4  5
     * / \
     *1  2
     * 给定的树 B：
     *
     *  4
     * /
     *1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    //递归判断B是否是A的子结构
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null||B==null){
            return false;
        }
        //第一个表示，B棵树从A的根结点和其对应
        return isSub(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B);
    }

    //这个递归使用来判断B是否是A的子结构
    //即从A和B的根结点往下走，往下比较进行判断
    public boolean isSub(TreeNode A,TreeNode B){
        //baseCase
        if(B==null){ //都能比较到B为null的时候，那肯定是之前的都没有问题
            return true;
        }

        //B!=null
        if(A==null||A.val!=B.val){
            return false;
        }
        //A!=null && B!=null && A.val==B.val
        return isSub(A.left,B.left)&&isSub(A.right,B.right);
    }








    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    

    public static void main(String[] args) {

    }



}
