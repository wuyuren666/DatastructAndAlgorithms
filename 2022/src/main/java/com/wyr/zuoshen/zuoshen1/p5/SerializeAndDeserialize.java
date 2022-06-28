package com.wyr.zuoshen.zuoshen1.p5;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * 就是内存中的一棵树如何变成字符串形式，又如何从字符串形式变成内存里的树
 */
public class SerializeAndDeserialize {


    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int val){
            this.value=val;
        }
    }
    /**
     * 按照先序遍历序列化二叉树
     * null用#_代替
     * 其中的_代表结束的标记
     */
    public static String serializeByPreOrder(Node head){
        if(head==null){
            return "#_";
        }
        String res=head.value+"_";
        res+=serializeByPreOrder(head.left); //加上左子树的结果
        res+=serializeByPreOrder(head.right); //加上右子树的结果
        return res;
    }

    /**
     * 反序列化
     */
    public static Node recoByPreString(String preStr){
        String[] values = preStr.split("_");
        LinkedList<String> queue=new LinkedList<>();

        for(int i=0;i!=values.length;i++){
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    //消费这个队列去建立这棵树
    private static Node reconPreOrder(Queue<String> queue) {
        String value=queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head=new Node(Integer.parseInt(value));
        head.left=reconPreOrder(queue);
        head.right=reconPreOrder(queue);
        return head;
    }


}
