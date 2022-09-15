package com.wyr.leetcode.step2.array;

public class CorpFlightBookingsTest {
    /**
     * 这里有n个航班，它们分别从 1 到 n 进行编号。
     *
     * 有一份航班预订表bookings ，
     * 表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
     * 意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
     *
     * 请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数。
     *
     *
     * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
     * 输出：[10,55,45,25,25]
     * 解释：
     * 航班编号        1   2   3   4   5
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       20  20
     * 预订记录 3 ：       25  25  25  25
     * 总座位数：      10  55  45  25  25
     * 因此，answer = [10,55,45,25,25]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/corporate-flight-bookings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int [] ans=new int [n+2];
        for(int i=0;i<bookings.length;i++){
            ans[bookings[i][0]]+=bookings[i][2];
            ans[bookings[i][1]+1]+=(-bookings[i][2]);
        }
        int [] res=new int[n];
        int temp=ans[1];
        for(int i=0;i<n;i++){
            res[i]=temp;
            temp+=ans[i+2];
        }
        return res;
    }
}
