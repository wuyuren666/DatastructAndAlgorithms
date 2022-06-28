package com.wyr.zuoshen.zuoshen1.p5;

/**
 * 重要！！！！！！！！
 *
 * 在二叉树中找到一个节点的后继节点
 * 现在有一种新的二叉树节点类型如下
 * public class Node{
 *     public int value;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 *     public Node(int val){
 *         this.value=val;
 *     }
 * }
 * 该结构比普通的二叉树节点结构多了一个指向父节点的parent指针
 * 假设有一棵Node类型的节点组成的二叉树，树中的每个节点的parent指针，都正确的指向自己的父节点，头节点的parent指向null
 * 只给一个在二叉树中的某一个节点node，请实现返回node节点后继节点的函数
 * 在二叉树的中序遍历中，node的下一个节点叫做node的后继节点
 */
public class GetSuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            this.value = val;
        }
    }

    /**
     * 梳理情况很重要！！！！
     * 三种情况需要考虑：
     * 情况一：当前节点有右孩子（有右子树），那么右子树中的最左边的节点就是其后继节点
     * 情况二：当前节点没有右子树，那么从当前节点向上面出发如果他的父节点是其爷爷节点的左孩子，那么这个爷爷就是他的后继节点
     * 情况三：在第二种情况下，得不到满足要求的爷爷节点。那么这个节点的后继节点就是null，意味着当前节点处在整棵树的最右边
     */
    public static Node getSuccessorNode66(Node node){
        if(node==null){
            return node;
        }
        if(node.right!=null){//当前节点有右孩子
            return getMostLeftNode(node);
        }else{//当前节点没有右孩子
            Node parent=node.parent;
            while (parent!=null&&parent.left!=node){
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }

    public static Node getSuccessorNode520(Node node){
        if(node==null){
            return node;
        }
        //如果当前节点有右孩子，那么右孩子的最左节点就是他的后继节点
        if(node.right!=null){
           return getMostLeftNode(node.right);
        }else{
            Node parent= node.parent;
            while(parent!=null&&parent.left!=node){ //当父节点不为null，且父节点的左孩子不是自己的时候，继续循环
                node=parent;
                parent= node.parent;
            }
            //最后，要么parent==null退出的循环，意思就是当前node节点在整棵树的最右边；
            //要么就是从node节点往上搜寻他的祖先节点
            //直到找到了某一个祖先节点是其父节点的左孩子，那么其父节点就是当前node的中序后继节点
            return parent;
        }
    }

    public static Node getSuccessorNode(Node node){
        if(node==null)
            return node;
        if(node.right!=null){ //有右孩子，即有右子树
            //找到右子树中的最左边的节点返回即可
            return getMostLeftNode(node.right);
        }else {//没有右孩子
            Node parent=node.parent;
            //这个while将情况一和情况二都包含了
            while (parent!=null&&parent.left!=node){//从自己开始，一路向上找，知道发现某一个节点，是其父亲节点的左孩子
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }
    //找到右子树中的最左节点的方法
    public static Node getMostLeftNode(Node node){
        if(node==null)
            return node;
        while(node.left!=null) {
            node = node.left;
        }
        return node;
    }

}
