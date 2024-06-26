package com.wyr.leetcode.step2.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 *
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 *
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 2：
 *
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：
 * 到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 * 示例 3：
 *
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 *
 * https://leetcode.cn/problems/jump-game-iii/description/
 */
public class CanReachIIIBFSTest {

    public boolean canReach(int[] arr, int start) {
        boolean[] isReached = new boolean[arr.length]; //false代表当前位置没有来到过
        return bfs(arr, start);
    }

    public static boolean bfs(int[] arr, int start) {
        boolean res = false;
        boolean [] isReached = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>(); // bfs必备的队列
        queue.add(start); // 初始化
        while(queue.size()!=0){ //循环条件
            Integer cur = queue.poll(); //当前所在位置
            isReached[cur] = true;
            if(arr[cur]==0){
                res=true;
                break;
            }
            if(cur-arr[cur]>=0&&!isReached[cur-arr[cur]]){//能到达，并且之前没有来到过
                queue.add(cur-arr[cur]);
            }
            if(cur+arr[cur]<=arr.length-1&&!isReached[cur+arr[cur]]){//能到达，并且之前没有来到过
                queue.add(cur+arr[cur]);
            }
        }
        return res;
    }

}
