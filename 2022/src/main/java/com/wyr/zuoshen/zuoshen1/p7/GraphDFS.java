package com.wyr.zuoshen.zuoshen1.p7;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

//图的深度优先遍历
//使用栈和Set集合
//一条道走到黑，然后往回看之前的顶点是否还有路走
public class GraphDFS {

    public static void dfs11_23(Node head){
        Set<Node> set=new HashSet<>();
        Stack<Node> stack=new Stack<>();
        set.add(head);
        stack.add(head);
        System.out.println(head.value);
        while (!stack.isEmpty()){
            Node curNode=stack.pop();
            //一条道走到黑，然后往回看之前的顶点是否还有路走
            for (Node next : curNode.nexts) {
                if(!set.contains(next)){
                    System.out.println(next.value);
                    stack.push(curNode);//先压
                    stack.push(next);//后压
                    set.add(next);
                    break;
                }
            }
        }
    }



    //需要一个栈和一个set集合
    public void dfs_627(Node head){
        Stack<Node> stack=new Stack<>();
        Set<Node> set=new HashSet<>();
        set.add(head);
        stack.push(head);
        System.out.println(head.value);//第一个先打印
        while (!stack.isEmpty()){
            Node curNode = stack.pop();
            for(Node nextNode: curNode.nexts){
                if(!set.contains(nextNode)){
                    stack.push(curNode);//先压
                    stack.push(nextNode);//后压
                    set.add(nextNode);//加入set
                    System.out.println(nextNode.value);//打印
                    break;
                }
            }
        }
    }


    public static void dfs(Node node){
        if(node==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        HashSet<Node> set=new HashSet<>();
        set.add(node);
        stack.push(node);//先将node加入栈
        System.out.println(node.value);
        while(stack.size()!=0){
            Node cur = stack.pop(); //先拿
            for (Node next : cur.nexts) {
                if(!set.contains(next)){
                    stack.push(cur); //先放
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break; //记住break，因为要一条道走到黑，也就是说接下来就从这个next出发，向后找后序节点
                }
            }
        }

    }
}
