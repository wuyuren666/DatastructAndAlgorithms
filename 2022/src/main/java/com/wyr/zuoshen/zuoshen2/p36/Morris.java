package com.wyr.zuoshen.zuoshen2.p36;

public class Morris {
    /**
     *
     *  以后使用非递归实现二叉树的前中后序遍历，就用Morris遍历！！！
     *
     *  Morris遍历，实现树的前中后序遍历，能将空间复杂度优化为O(1)
     *
     *  遍历规则
     *  当前节点cur，一开始来到整棵树的头
     *  1）cur无左树，cur=cur.right
     *  2）cur有左树，找到左树最右节点mostRight
     *      1：mostRight的右指针指向null的，mostRight.right=cur，cur=cur.left
     *      2：mostRight的右指针指向cur，mostRight.right=null，cur=cur.right
     */
    //Morris序，有左树的节点会来到两次，没有左树的节点，只会来到一次
    public static void morris(Node head){
        if(head==null){
            return;
        }
        Node cur=head;
        Node mostRight=null;

        //每一次，cur要么向左走，要么向右走
        while(cur!=null){
            //cur有没有左树
            mostRight=cur.left;
            if(mostRight!=null){ // 有左树的节点回来到自己两次
                while(mostRight.right!=null&&mostRight!=cur){ //mostRight来到除了cur本身的真正的左树的最右节点
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null){//第一次来到自己
                    mostRight.right=cur;
                    cur=cur.left; //向左走
                }else{//2：第二次来到自己
                    mostRight.right=null;
                    cur=cur.right; //向右走
                }
            }else { //没有左树的节点，只会来到自己一次，然后向右走
                cur=cur.right;
            }

        }
    }


    //Morris实现先序
    //对于先序来说，会来到自己两次的节点，你第一次来到就打印；对于只能到达一次的节点，直接打印
    public static void MorrisPreOrder(Node head){
        Node cur=head;
        Node mostRight=null;

        while(cur!=null){
            mostRight=head.left;
            if(mostRight!=null){ //cur有左树，有左树的节点会来到自己两次
                while(mostRight.right!=null&&mostRight.right!=cur){ //mostRight来到除了cur本身的真正的左树的最右节点
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null){ //第一次来到自己
                    mostRight.right=cur;
                    System.out.println(cur.value+" ");//这里打印一处，对于来到两次的节点，第一次来到的时候打印
                    cur=cur.left;
                }else{ //mostRight.right!=null ；第二次来到自己
                    mostRight.right=null;
                    cur=cur.right;
                }
            }else {
                System.out.println(cur.value+" "); //这里打印一处，对于只来到一次的节点，直接打印
                cur=cur.right;
            }

        }
    }


    //Morris实现中序
    public static void MorrisInOrder(Node head){
        Node cur=head;
        Node mostRight=null;

        while(cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){ //cur有左树，有左树的节点会来到自己两次
                while(mostRight.right!=null&&mostRight.right!=cur){ //mostRight来到除了cur本身的真正的左树的最右节点
                    mostRight=mostRight.right;
                }

                if(mostRight.right==null){ //第一次来到自己
                    mostRight.right=cur;
                    cur=cur.left;
                }else{ //mostRight.right!=null ；第二次来到自己
                    mostRight.right=null;
                    System.out.println(cur.value+" "); //对于有左树的节点，第二次来到的时候打印
                    cur=cur.right;
                }
            }else { //对于没有左树的节点，来到就打印；
                System.out.println(cur.value+" ");
                cur=cur.right;
            }
        }
    }




    //Morris实现后序（稍微麻烦）
    //Morris实现中序，就是在morris序的基础上，只看能够来到自己两次的节点，第二次来到时，逆序打印他的左树的右边界；
    // 最后再打印整棵树的右边界
    public static void MorrisPosOrder(Node head){
        Node cur=head;
        Node mostRight=null;

        while(cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){ //cur有左树，有左树的节点会来到自己两次
                while(mostRight.right!=null&&mostRight.right!=cur){ //mostRight来到cur左树上的真实的最右节点
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null){ //第一次来到自己
                    mostRight.right=cur;
                    cur=cur.left;
                }else{ //mostRight.right!=null
                    //第二次来到自己；后序遍历就是能回来到自己两次的节点，第二来到自己的时候，将其左树的右边界逆序打印
                    mostRight.right=null;
                    printEdge(cur.left);
                    cur=cur.right;
                }
            }else{ //cur没有左树，没有左树的节点只会来到自己一次，只能往右走
                cur=cur.right;
            }
        }
        printEdge(head);//while结束时，整棵树的右边界，逆序打印
    }

    public static void printEdge(Node head){
        Node tail= reverseEdge(head); //将树的右边界反转之后获得反转前的尾巴
        Node cur=tail;
        while (cur!=null){
            System.out.println(cur.value+" ");
            cur=cur.right;
        }
        reverseEdge(tail); //再反转回去
    }

    public static Node reverseEdge(Node head){
        Node pre=null;
        Node cur=head;
        Node temp=null;
        while (cur!=null){
            temp=cur.right;
            cur.right=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }







    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


}
