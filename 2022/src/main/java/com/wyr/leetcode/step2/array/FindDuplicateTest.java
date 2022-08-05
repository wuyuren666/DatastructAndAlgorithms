package com.wyr.leetcode.step2.array;

public class FindDuplicateTest {

    /**
     * 给定一个包含n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。
     *
     * 假设 nums 只有 一个重复的整数 ，返回这个重复的数 。
     *
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     *
     * 输入：nums = [1,3,4,2,2]
     * 输出：2
     *
     * 输入：nums = [3,1,3,4,2]
     * 输出：3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-the-duplicate-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //把数组看成链表，数组的值看作链表节点的值，索引看成next指针
    public int findDuplicateBest(int[] nums) { //O(1)，满足题目的要求
        int slow=0,fast=0;
        do{
            slow=nums[slow];
            fast=nums[nums[fast]]; //快指针一次走两步
        }while(slow!=fast);
        fast=0;//快指针移动到开头
        while(slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;
    }

    //空间复杂度O(N)
    public int findDuplicate(int[] nums) {
        int N=nums.length;
        int [] counts=new int[N-1];//统计表
        int i=0;
        for(;i<N;i++){
            if(++counts[nums[i]-1]>1){
                break;
            }
        }
        return nums[i];
    }
}
