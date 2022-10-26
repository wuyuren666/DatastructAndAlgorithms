package com.wyr.zuoshen.zuoshen1.p5;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 二叉树的广度优先遍历
 */
public class BreadthFirstTraverse {


    //5.10广度优先遍历
    public static void breadthFirstTraverse510(BinaryTreesNode head){
        LinkedList<BinaryTreesNode> queue=new LinkedList<>();//队列
        queue.add(head);
        BinaryTreesNode cur;
        while(queue.size()!=0){
            cur=queue.removeFirst();
            System.out.println(cur.value);
            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
    }


    //5.10 获的二叉树最宽的长度
    public static int breadthFirstTraverseGetMaxWidth510(BinaryTreesNode head){
        LinkedList<BinaryTreesNode> queue=new LinkedList<>();//队列
        Map<BinaryTreesNode,Integer> map=new HashMap<>();//保存每个节点对应的层次
        map.put(head,1);
        int curLevel=1;//当前统计的层
        int curLevelCounts=0;//当前统计的层的节点个数
        int max=0; //最终返回的结果
        queue.add(head);
        BinaryTreesNode cur;

        while (queue.size()!=0){
            if(map.get(cur=queue.poll())==curLevel){
                curLevelCounts++;
            }else {
                max=Math.max(max,curLevelCounts);
                curLevelCounts=1;
                curLevel++;
            }
            if(cur.left!=null){
                map.put(cur.left,curLevel+1);
                queue.add(cur.left);
            }
            if(cur.right!=null){
                map.put(cur.right,curLevel+1);
                queue.add(cur.right);
            }
        }
        return Math.max(max,curLevelCounts);
    }





    // 广度优先遍历二叉树
    // 用队列，先进先出，出的同时，将自己的左孩子和右孩子先后加入队列
    public static void breadthFirstTraverse(BinaryTreesNode head){
        if(head==null)
            return;
        LinkedList<BinaryTreesNode> queue = new LinkedList<>();
        BinaryTreesNode cur;
        queue.add(head);
        while (queue.size()!=0){
            System.out.println(cur = queue.removeFirst());
            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
    }


    // 广度优先遍历二叉树，返回这棵树的最大宽度
    // 用队列，先进先出，出的同时，将自己的右孩子和左孩子先后加入队列
    //同时使用一个map集合记录每一个节点所对应的层数
    public static int breadthFirstTraverseGetMaxWidth(BinaryTreesNode head){
            if(head==null)
                return -1;
            LinkedList<BinaryTreesNode> queue=new LinkedList<>();
            Map<BinaryTreesNode,Integer> map=new HashMap<>();
            BinaryTreesNode cur;
            queue.add(head);
            map.put(head,1);
            int curLevel=1;//代表当前所在的层
            int curLevelCounts=0;//代表当前所在层节点的个数
            int max=-1;//返回的最大宽度

            while (queue.size()!=0){
                if(map.get(cur=queue.removeFirst())==curLevel){ //出队列的节点所在的层数==当前统计的层
                    curLevelCounts++; //当前层数统计的数目+1
                }else { //出队列的节点所在的层数不在当前统计的层
                    curLevel++; //当前的层往下移一层，来到新的层
                    max=Math.max(max,curLevelCounts);//将统计过的层中最大值赋值给max
                    curLevelCounts=1; //新的层也发现了第一个，因为走了else就是下一层的节点弹出了

                }
                if(cur.left!=null){
                    map.put(cur.left,curLevel+1);
                    queue.add(cur.left);
                }
                if(cur.right!=null){
                    map.put(cur.right,curLevel+1);
                    queue.add(cur.right);
                }
            }
            //注意这里应该考虑边界情况，最后一行有可能比max大
            return Math.max(max,curLevelCounts);
    }



    public static void main(String[] args) {
        //构造一颗二叉树
        BinaryTreesNode node1=new BinaryTreesNode(1,null,null);
        BinaryTreesNode node2=new BinaryTreesNode(2,null,null);
        BinaryTreesNode node3=new BinaryTreesNode(3,null,null);
        BinaryTreesNode node4=new BinaryTreesNode(4,null,null);
        BinaryTreesNode node5=new BinaryTreesNode(5,null,null);
        BinaryTreesNode node6=new BinaryTreesNode(6,null,null);
        BinaryTreesNode node7=new BinaryTreesNode(7,null,null);
        BinaryTreesNode node8=new BinaryTreesNode(8,null,null);
        BinaryTreesNode node9=new BinaryTreesNode(9,null,null);
        BinaryTreesNode node10=new BinaryTreesNode(10,null,null);
        BinaryTreesNode node11=new BinaryTreesNode(11,null,null);

        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node8;
        node3.right=node9;
        node5.left=node6;
        node5.right=null;
        node6.left=node7;
        node6.right=null;
        node8.left=null;
        node8.right=node10;
        node10.left=node11;

       /* BinaryTreesNode node1=new BinaryTreesNode(1,null,null);
        BinaryTreesNode node2=new BinaryTreesNode(2,null,null);
        BinaryTreesNode node3=new BinaryTreesNode(3,null,null);
        BinaryTreesNode node4=new BinaryTreesNode(4,null,null);
        BinaryTreesNode node5=new BinaryTreesNode(5,null,null);
        BinaryTreesNode node6=new BinaryTreesNode(6,null,null);
        BinaryTreesNode node7=new BinaryTreesNode(7,null,null);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;*/

        breadthFirstTraverse(node1);
        System.out.println(breadthFirstTraverseGetMaxWidth510(node1));
    }
}
