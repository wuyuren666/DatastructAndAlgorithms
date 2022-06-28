package com.wyr.leetcode.step2.graph;

import java.util.*;

/**
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，
 * 其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
 *
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 *
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 *
 * 执行用时：8 ms, 在所有 Java 提交中击败了17.83%的用户
 * 内存消耗：41.8 MB, 在所有 Java 提交中击败了22.31%的用户
 * 通过测试用例：51 / 51
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanFinish1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //获取Node集合
        List<Node> list=createCourseList(numCourses,prerequisites);
        //创建节点和入度的关系表
        Map<Node,Integer> inMap=new HashMap<>();
        //队列，存放入度为0的节点
        Queue<Node> zeroInQueue=new LinkedList<>();
        //存放学过的课程
        Set<Node> learnedCourse=new HashSet<>();

        for(Node curNode: list){
            inMap.put(curNode,curNode.in);
            if(curNode.in==0){
                zeroInQueue.add(curNode);
            }
        }
        //竟然没有入度为0的节点，那肯定学不完
        if(zeroInQueue.size()==0){
            return false;
        }

        //拓扑排序
        while(zeroInQueue.size()!=0){
            Node cur=zeroInQueue.poll();
            learnedCourse.add(cur);
            //将此节点的后续节点的入度-1
            for(Node node: cur.nexts){
                int nextNodeIn=inMap.get(node)-1;
                inMap.put(node,nextNodeIn);
                if(inMap.get(node)==0){
                    zeroInQueue.add(node);
                }
            }
        }
        if(learnedCourse.size()!=numCourses){
            return false;
        }else{
            return true;
        }
    }


    public List<Node> createCourseList(int numCourses, int [][] prerequisites){
        List<Node> result=new ArrayList<>();
        //将所有节点加入nodes
        for(int i=0;i<numCourses;i++){
            Node newNode=new Node(i);
            result.add(newNode);
        }

        for(int i=0;i<prerequisites.length;i++){
            int toNodeKey=prerequisites[i][0];
            int fromNodeKey=prerequisites[i][1];

            Node toNode=result.get(toNodeKey);
            Node fromNode=result.get(fromNodeKey);
            toNode.in++; //入度++
            fromNode.out++; //出度++
            fromNode.nexts.add(toNode);
        }
        return result;
    }

    public class Node{
        public int value;
        public int in; //入度
        public int out; //出度
        public List<Node> nexts;

        public Node(int value){
            this.value=value;
            this.in=0;
            this.out=0;
            nexts=new ArrayList<>();
        }
    }
}
