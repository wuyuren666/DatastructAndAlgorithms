package com.wyr.leetcode.step2.array;

import java.util.*;

public class MergeTest {
    /**
     * 以数组 intervals 表示若干个区间的集合，
     * 其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * <p>
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] n=new int[][]{{1,3},{2,6},{8,10},{15,18}};
        merge(n);
    }
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //贪心的思想，首先对intervals进行排序，按照开始值从小到大进行排序
        Arrays.sort(intervals,(o1,o2)->o1[0]-o2[0]);

        LinkedList<int[]> list=new LinkedList<>();

        for(int i=0;i<intervals.length;i++){
            int L=intervals[i][0],R=intervals[i][1];
            if(list.size()==0||list.peekLast()[1]<L){//需要新增区间
                list.add(new int[]{L,R});
            }else{ //需要合并区间
                list.peekLast()[1]=Math.max(R,list.peekLast()[1]);
            }
        }
        return list.toArray(new int[0][0]);


    }
}
