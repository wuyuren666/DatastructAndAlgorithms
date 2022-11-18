package com.wyr.leetcode.step1.array;

public class DistributeCandiesTest2 {
    /**
     * 排排坐，分糖果。
     *
     * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
     *
     * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n颗糖果。
     *
     * 然后，我们再回到队伍的起点，给第一个小朋友 n+ 1 颗糖果，第二个小朋友 n+ 2 颗，
     * 依此类推，直到给最后一个小朋友 2 * n颗糖果。
     *
     * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），
     * 直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
     *
     * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
     *
     *
     * 输入：candies = 7, num_people = 4
     * 输出：[1,2,3,1]
     * 解释：
     * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
     * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
     * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
     * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/distribute-candies-to-people
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] distributeCandies(int candies, int num_people) {
        int [] res=new int[num_people];
        int curIndex =0; //下标位置
        int numOfCurPeople =1; //当前下标位置应该分得的糖果数
        while(candies>numOfCurPeople){ //当剩余的糖果数大于给应该给当前孩子的糖果数
            res[curIndex] += numOfCurPeople;
            candies-=numOfCurPeople;//剩下的糖果数
            curIndex = (curIndex+1)%num_people; //下标后移
            numOfCurPeople++; //下一位应该得到的糖果数
        }
        res[curIndex] += candies;
        return res;
    }


}
