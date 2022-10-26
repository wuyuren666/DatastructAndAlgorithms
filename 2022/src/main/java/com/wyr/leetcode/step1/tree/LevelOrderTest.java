package com.wyr.leetcode.step1.tree;

import java.util.*;

/**
 * 给你二叉树的根节点 root ，返回其节点值的层序遍历 。（即逐层地，从左到右访问所有节点）
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 *
 * 输入：root = [1]
 * 输出：[[1]]
 */
public class LevelOrderTest {
    //二叉树的BFS，这个比较好
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> ans=new ArrayList<>();
        //BFS用到的队列
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        List<Integer> tempList=null;
        //while用来走每一层
        while(!queue.isEmpty()){
            int size = queue.size();
            tempList=new ArrayList<>();
            //for是将当前层的节点的下一层节点(如果存在的话)加入到队列中
            for(int i=0;i<size;i++){
                TreeNode curNode=queue.poll();
                tempList.add(curNode.val);
                if(curNode.left!=null){
                    queue.add(curNode.left);
                }
                if(curNode.right!=null){
                    queue.add(curNode.right);
                }
            }
            ans.add(tempList);
        }
        return ans;
    }


    public static List<List<Integer>> levelOrder(BinaryTreesNode root) {
        List<List<Integer>> result=new ArrayList<>();//结果集合

        if(root==null)
            return  result;

        Map<BinaryTreesNode,Integer> map=new HashMap<>();//记录每个节点与其对应层次的表
        LinkedList<BinaryTreesNode> queue=new LinkedList<>(); //队列，先进先出
        queue.add(root);
        BinaryTreesNode cur; //辅助指针
        map.put(root,1); //root节点在第一层
        int curLevel=1; //代表当前统计的层
        List<Integer> curLevelList=null; //当前统计层的list集合
        while(queue.size()!=0){
            if(map.get(cur=queue.removeFirst())==curLevel){ //当前出队节点==当前统计的层
                if(curLevelList==null){
                    curLevelList=new ArrayList<>();
                }
                curLevelList.add(cur.value);
            }else{ //当前节点!=当前统计的层
                result.add(curLevelList); //将上一层统计的list集合加入到结果集合中
                curLevel++; //统计下一层
                curLevelList=new ArrayList<>(); //清楚上一层统计结果
                curLevelList.add(cur.value); //将当前节点的value保存在新的层的list中
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
        result.add(curLevelList); //注意上面的while结束后，最后一层的list集合还没有加入到结果当中
        return result;
    }
}
