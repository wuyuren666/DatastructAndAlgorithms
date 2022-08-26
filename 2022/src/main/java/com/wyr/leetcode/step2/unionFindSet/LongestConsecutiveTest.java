package com.wyr.leetcode.step2.unionFindSet;

import java.util.*;

public class LongestConsecutiveTest {
    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
     *
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-consecutive-sequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int N=nums.length;
        int [] father= new int[N];
        for(int i=0;i<N;i++){
            father[i]=i;
        }
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<N;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<N;i++){
            int curNum=nums[i];
            if(map.containsKey(curNum-1)){
                if(findFather(father,map.get(curNum-1))!=findFather(father,i)){
                    union(father,map.get(curNum-1),i);
                }
            }
            if(map.containsKey(curNum+1)){
                if(findFather(father,map.get(curNum+1))!=findFather(father,i)){
                    union(father,map.get(curNum+1),i);
                }
            }
        }
        List<Integer> list=new ArrayList<>();
        //将所有父元素的代表节点放入list中
        getNum(father,list);
        //value为set集合方便去重
        Map<Integer,Set<Integer>> resultMap=new HashMap<>();

        for(int i=0;i<list.size();i++){
            resultMap.put(list.get(i),new HashSet<>());
        }

        //resultMap中保存的是父代表元素下标和一个set集合里面放的是他的子元素
        for(int i=0;i<N;i++){
            resultMap.get(findFather(father,father[i])).add(nums[i]);
        }
        int ans=Integer.MIN_VALUE;
        for(Set<Integer> set: resultMap.values()){
            ans=Math.max(ans,set.size());
        }
        return ans;
    }
    //将所有父代表元素的下标放入list集合
    public static void getNum(int []father,List<Integer> list){
        for(int i=0;i<father.length;i++){
            if(i==father[i]){
                list.add(i);
            }
        }
    }
    //找到父元素下标
    public static int findFather(int [] father, int index){
        if(index!=father[index]){
            father[index]=findFather(father,father[index]);
        }
        return father[index];
    }
    //合并
    public static void union(int [] father, int index1, int index2){
        father[findFather(father,index1)]=findFather(father,index2);
    }
    public static void main(String[] args) {
        int [] nums={0,3,7,2,5,8,4,6,0,1};
        longestConsecutive(nums);
    }

}
