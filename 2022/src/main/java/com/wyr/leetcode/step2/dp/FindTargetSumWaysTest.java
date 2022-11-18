package com.wyr.leetcode.step2.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FindTargetSumWaysTest {
    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int M=nums.length+1;
        int N=target+1;
        Map<Info,Integer> map=new HashMap<>();
        return process1(nums,0,target,map);
    }

    public static int process1(int [] nums, int index, int rest,Map<Info,Integer> map){
        Info info=new Info(index,rest);
        if(map.containsKey(info)){
            return map.get(info);
        }
        int result=0;
        if(index==nums.length){
            result=(rest==0 ? 1 : 0);
        }else{
            int p1=process1(nums,index+1,rest+nums[index],map);
            int p2=process1(nums,index+1,rest-nums[index],map);
            result=p1+p2;
        }
        map.put(info,result);
        return result;
    }

    public static class Info{
        public int index;
        public int rest;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return index == info.index && rest == info.rest;
        }

        /**
         * 注意如果单单是重写equals方法，不重写hashCode方法，会慢很多
         */
        @Override
        public int hashCode() {
            return Objects.hash(index, rest);
        }

        public Info(int index, int rest){
            this.index=index;
            this.rest=rest;
        }
    }

    //优化点1：可以将数组中的数全都变成正数，不影响结果
    //优化点2：数组中全都是正数加起来的和<target，没有结果
    //优化点3：数组中全都是正数加起来的和和target的奇偶性不一样，一定0种方法
    //优化点4：假设我们最终组合成target的正数的集合的和为P,负数的集合的和为N，那么一定有P-N==target
    // 两边同时加上 P+N ==> 2P==target+P+N  ==> P==(target+SUM)/2  （其中SUM是数组中的所有元素的累加和）
    // 问题最终转换为，我们可以在这个数组中找到多少种累加和P的组合方案就是最终的结果
    public static int wayBest1(int [] nums, int target){
        int sum=0;
        //优化点1
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        //处理 [100] -200 的特殊情况
        if(nums.length==1&&nums[0]!=Math.abs(target)){
            return 0;
        }
        //优化点2
        if(sum<target||((sum&1)^(target&1))!=0){
            return 0;
        }
        //优化点3
        //target+sum之和不是偶数，其实就是target和sum奇偶性不一致的意思
        if(((target+sum)&1) != 0){
            return 0;
        }
        //优化点4
        int P=(target+sum)/2;
        //使用背包问题去解决
        return process2(nums, 0, P);
    }
    //背包问题简化版
    public static int process2(int [] nums, int index, int rest){
        if(index==nums.length){ //没数可选了
            return (rest==0?1:0);
        }
        //不要当前的数
        int p1= process2(nums,index+1,rest);
        //要当前的数
        int p2= process2(nums,index+1,rest-nums[index]);
        return p1+p2;
    }


    //上面的动态规划版本
    public static  int wayBest2(int[] nums, int target) {
        int sum=0;
        //优化点1
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        //处理 [100] -200 的特殊情况
        if(nums.length==1&&nums[0]!=Math.abs(target)){
            return 0;
        }
        //优化点2，3
        if(sum<target||((sum&1)^(target&1))!=0){
            return 0;
        }
        //优化点4
        int P=(target+sum)>>1;
        int M=nums.length+1;
        int N=P+1;
        int [][] dp=new int [M][N];
        dp[M-1][0]=1;
        for(int j=1;j<N;j++){
            dp[M-1][j]=0;
        }
        for(int index=M-2;index>=0;index--){
            for(int rest=0;rest<N;rest++){
                //不要当前的数
                int p1=0;
                p1+= dp[index+1][rest];
                int p2=0;
                //要当前的数
                if(rest-nums[index]>=0){
                    p2+=dp[index+1][rest-nums[index]];
                }
                dp[index][rest]=p1+p2;
            }
        }
        //使用背包问题去解决
        return dp[0][P];
    }


    public static void main(String[] args) {
        int [] arr={10,9,6,4,19,0,41,30,27,15,14,39,33,7,34,17,24,46,2,46};
        System.out.println(wayBest2(arr, 45));
        System.out.println(wayBest1(arr, 45));

    }
}
