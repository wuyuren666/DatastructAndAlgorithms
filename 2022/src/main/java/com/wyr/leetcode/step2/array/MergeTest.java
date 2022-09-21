package com.wyr.leetcode.step2.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        //先根据每一个区间的开始值，进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        //保存区间
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {//不是同一区间的逻辑
                merged.add(new int[]{L, R});//新增区间
            } else {
                //是同一区间，需要合并
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
