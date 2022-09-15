package com.wyr.leetcode.step2.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PermuteTest {
    /**
     *给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * https://leetcode.cn/problems/permutations/
     */

    List<List<Integer>> res=new ArrayList<>();
    public  List<List<Integer>> permuteHuiSu(int[] nums) {
        int [] used=new int [nums.length];
        process(nums,used,new ArrayList<Integer>());
        return res;
    }
    //递归+回溯
    public void process(int [] nums, int[] used, List<Integer> tempList){
        if(tempList.size()==nums.length){//baseCase
            res.add(new ArrayList<>(tempList));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]==0){//没有使用过
                //使用当前数字
                tempList.add(nums[i]);
                //标记当前数字已经被用过
                used[i]=1;
                process(nums,used,tempList);
                //不使用当前数字
                tempList.remove(tempList.size()-1);
                //取消标记
                used[i]=0;
            }
        }
    }


    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        //先将nums数组按从小到大逆序排序
        Arrays.sort(nums);
        int length=nums.length; //记录数组的长度
        List<Integer> temp=new ArrayList<>();

        for(int i=0;i<length;i++){
            temp.add(nums[i]);
        }
        result.add(temp); //先将最小的全排列数放进结果集合中

        int index;//保存逆序区域第一个数的下标
        while((index=findNiXuPreIndex(nums))!=0){
            temp=new ArrayList<>();
            exchange(nums,index);
            for(int i=0;i<length;i++){
                temp.add(nums[i]);
            }
            result.add(temp);
        }

        return result;
    }

    //寻找逆序区域的第一个数的下标
    public static int findNiXuPreIndex(int []nums){
        for(int i=nums.length-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                return i;
            }
        }
        return 0;//这个数是全排列中的最大的那个数
    }

    //将逆序区域前一个数和逆序区域中的大于它的最小的数进行交换
    //beginIndex：逆序区域开始的下标，结束的下标肯定是nums.length-1
    public static void exchange(int []nums, int beginIndex){
        int index=beginIndex-1;//逆序区前一个需要交换的数的下标
        int minBiggerIndex=-1; //记录大于nums[index]这个数的最小数的下标
        boolean flag=true;
        for(int i=beginIndex;i<nums.length;i++){
            if(nums[i]>nums[index]){
                //满足这个就是逆序数中的数当前大于nums[index]
                if(flag){ //只会做一次
                    minBiggerIndex=i;
                    flag=false;
                }else{ //接下来的数，如果满足比nums[i]大，并且比nums[minBiggerIndex]小,记录
                    if(nums[i]<nums[minBiggerIndex]){
                        minBiggerIndex=i;
                    }
                }
            }
        }
        swap(nums,index,minBiggerIndex);
        //再将逆序区域改为顺序
        Arrays.sort(nums,beginIndex,nums.length);
    }


    public static void swap(int [] nums, int i, int j){
        nums[i]=nums[i]^nums[j];
        nums[j]=nums[i]^nums[j];
        nums[i]=nums[i]^nums[j];
    }

    public static void main(String[] args) {
        int [] a={3,2,1};
        List<List<Integer>> permute = permute(a);
        System.out.println("aaaa");
    }
}
