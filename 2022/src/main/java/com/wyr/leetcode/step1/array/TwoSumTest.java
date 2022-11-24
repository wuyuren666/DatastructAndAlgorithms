package com.wyr.leetcode.step1.array;

import java.util.*;


public class TwoSumTest {
    /**
     *
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，
     * 并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     *
     * 你可以按任意顺序返回答案。
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    //执行用时：8 ms, 在所有 Java 提交中击败了47.53%的用户
    public static int[] twoSum(int[] nums, int target) {
        List<Element> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            list.add(new Element(i,nums[i]));
        }
        Collections.sort(list);
        int left=0,right=list.size()-1;
        int ans1=-1,ans2=-1;
        while(left<right){
            int sum=list.get(left).value+list.get(right).value;
            if(sum==target){
                ans1=list.get(left).index;
                ans2=list.get(right).index;
                break;
            }else if(sum<target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{ans1,ans2};

    }


    static class Element implements Comparable<Element>{
        public int index;
        public int value;
        public Element(int index, int value){
            this.index=index;
            this.value=value;
        }
        @Override
        public int compareTo(Element ele) {
            return this.value-ele.value;
        }
    }

    //执行用时：6 ms, 在所有 Java 提交中击败了48.41%的用户
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                List<Integer> list=new ArrayList<>();
                list.add(i);
                map.put(nums[i],list);
            }else{
                List<Integer> list=map.get(nums[i]);
                list.add(i);
                map.put(nums[i],list);
            }
        }
        int [] ans=new int[2];
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                List<Integer> list=map.get(target-nums[i]);
                if(!list.contains(i)){
                    ans[0]=i;
                    ans[1]=list.get(0);
                    break;
                }else{
                    if(list.size()==1){
                        continue;
                    }else{
                        ans[0]=i;
                        ans[1]=list.get(list.size()-1);
                        break;
                    }
                }
            }
        }
        return ans;
    }

}
