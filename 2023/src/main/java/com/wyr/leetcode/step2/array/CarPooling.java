package com.wyr.leetcode.step2.array;

/**
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 *
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 *
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * 示例 2：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 */

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        boolean res=true;
        //初始化差分数组
        int[] diff= new int[1001];
        int [] orgin =new int[1001];
        //进行区间频繁加减，这里要注意乘客在to位置处是会下车的，所以只需对[from,to-1]这一区间去操作即可
        for(int i=0;i<trips.length;i++){
            diff[trips[i][1]]+=trips[i][0];
            diff[trips[i][2]]-=trips[i][0];
        }
        //这边是做原数组的还原
        orgin[0]=diff[0];
        if(orgin[0]>capacity){
            return false;
        }
        for(int i=1;i<1001;i++){
            orgin[i]=orgin[i-1]+diff[i];
            if(orgin[i]>capacity){
                res=false;
                break;
            }
        }
        return res;
    }




}
