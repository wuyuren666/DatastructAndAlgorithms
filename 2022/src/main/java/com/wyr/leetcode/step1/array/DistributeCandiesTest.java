package com.wyr.leetcode.step1.array;

import java.util.HashSet;
import java.util.Set;

public class DistributeCandiesTest {
    /**
     * Alice 有 n 枚糖，其中第 i 枚糖的类型为 candyType[i] 。Alice 注意到她的体重正在增长，所以前去拜访了一位医生。
     *
     * 医生建议 Alice 要少摄入糖分，只吃掉她所有糖的 n / 2 即可（n 是一个偶数）。
     * Alice 非常喜欢这些糖，她想要在遵循医生建议的情况下，尽可能吃到最多不同种类的糖。
     *
     * 给你一个长度为 n 的整数数组 candyType ，返回： Alice 在仅吃掉 n / 2 枚糖的情况下，可以吃到糖的 最多 种类数。
     *
     *
     * 输入：candyType = [1,1,2,2,3,3]
     * 输出：3
     * 解释：Alice 只能吃 6 / 2 = 3 枚糖，由于只有 3 种糖，她可以每种吃一枚。
     *
     * 输入：candyType = [1,1,2,3]
     * 输出：2
     * 解释：Alice 只能吃 4 / 2 = 2 枚糖，不管她选择吃的种类是 [1,2]、[1,3] 还是 [2,3]，她只能吃到两种不同类的糖。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/distribute-candies
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int distributeCandies(int[] candyType) {
        int index=0;
        int length=candyType.length;
        int count=0; //吃掉糖的个数
        Set<Integer> set=new HashSet<>();//记录种类，去重
        while( index<length&& count<(length>>1)){
            if(!set.contains(candyType[index])){//没有吃过这一种类的糖果，赶快就吃
                set.add(candyType[index]);
                count++;
            }
            index++;
        }
        return set.size();
    }
}
