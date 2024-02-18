package com.wyr.leetcode.step2.tanXin;

/**
 *  *  跳跃问题II，能否使用贪心，每次选最大的跳？？
 *  *  不行，举一个返例
 *  *  7  1  2  12  3  1  3  5
 *  *  可以每次选择 i+nums[i]最大的跳，但是要控制好边界
 *  *
 *  *  step：步数
 *  *  maxCover：在step内最远能到达的位置
 *  *  next：如果允许多跳一步，最远能到达的位置
 */
public class CanJumpIITest {
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
}
