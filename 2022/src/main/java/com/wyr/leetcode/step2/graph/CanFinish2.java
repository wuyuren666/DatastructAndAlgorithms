package com.wyr.leetcode.step2.graph;

import java.util.LinkedList;
import java.util.Queue;

public class CanFinish2 {
    /**
     * 拓扑排序
     *
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
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
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

    //这里我们全都是用数组来表示课的所有关系
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //课表的相邻关系也可以使用二维数组来表示
        int[][] map=new int[numCourses][numCourses];

        //node下标就代表第几门课，node[i]就代表第i门课的入度是多少
        int [] node=new int[numCourses];

        init(prerequisites,map,node);

        //队列，存放入队节点的下标
        Queue<Integer> zeroInQueue=new LinkedList<>();
        //代表学过的课程数
        int learnedCourseCounts=0;

        //初始化好zeroInQueue这个队列
        for(int i=0;i<node.length;i++){
            if(node[i]==0){
                zeroInQueue.add(i);
            }
        }

        //竟然没有入度为0的节点下标，那肯定学不完
        if(zeroInQueue.size()==0){
            return false;
        }

        //拓扑排序
        while(zeroInQueue.size()!=0){
            //能够放在这个对列中的课肯定是可以学的
            int curCourseIndex=zeroInQueue.poll();
            learnedCourseCounts++;
            //将此节点的后续节点的入度-1
            for(int j=0;j<numCourses;j++){
                if(map[curCourseIndex][j]!=0){
                    node[j]--;
                    if(node[j]==0){
                        zeroInQueue.add(j);
                    }
                }
            }
        }
        if(learnedCourseCounts!=numCourses){
            return false;
        }else{
            return true;
        }
    }


    public void init(int [][] prerequisites,int[][] map,int [] node){
        for(int i=0;i<prerequisites.length;i++){
            int fromCourseIndex=prerequisites[i][1];
            int toCourseIndex=prerequisites[i][0];
            node[toCourseIndex]++; //入度++
            map[fromCourseIndex][toCourseIndex]=1;
        }
    }
}
