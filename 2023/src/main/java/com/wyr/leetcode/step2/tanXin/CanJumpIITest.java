package com.wyr.leetcode.step2.tanXin;

/**
 *  跳跃问题II，能否使用贪心，每次选最大的跳？？
 *  不行，举一个返例
 *  7  1  2  12  3  1  3  5
 *  可以每次选择 i+nums[i]最大的跳，但是要控制好边界
 *
 *
 *  给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 *  https://leetcode.cn/problems/jump-game-ii/description/
 */
public class CanJumpIITest {
    //最坏时间复杂度O(N^2)
    public int jump(int[] nums) {
        //特殊处理
        if(nums==null|| nums.length==1){
            return 0;
        }
        int cur=0; //当前位置
        int maxCover=nums[0]; //跳step次内能到达的最大位置
        int step=0; //跳跃次数
        while(cur<nums.length-1){
            step++;
            if(maxCover>=nums.length-1){ //如果能够直接到达，break
                break;
            }
            int tempMaxCover=maxCover; //临时变量
            //在[cur,tempMaxCover]中寻找i+nums[i]最大的点，跳过去
            for(int i=cur;i<=tempMaxCover;i++){
                if(i+nums[i]>maxCover){
                    maxCover=i+nums[i];
                    cur=i;
                }
            }
        }
        return step;
    }


    //时间复杂度O(N)
    public int jump2(int[] nums){
        /**
         * 3，4，1，3，2，4，2，1
         */
        if(nums==null||nums.length==1){
            return 0;
        }
        int count=0; //跳的次数
        int maxCoverOfCount=0; //在count次内最远能到达的位置
        int maxCoverOfNextCount=0; //下一次跳跃能到达的最远位置，为下一次的跳跃做准备
        for(int cur=0;cur<nums.length;cur++){
            //必须增加跳跃次数
            if(cur>maxCoverOfCount){
                //同时更新
                count++;
                maxCoverOfCount=maxCoverOfNextCount;
            }
            //不断更新，如果多进行一次跳跃能到达的最远位置
            maxCoverOfNextCount=Math.max(maxCoverOfNextCount,cur+nums[cur]);
        }
        return count;
    }

}
