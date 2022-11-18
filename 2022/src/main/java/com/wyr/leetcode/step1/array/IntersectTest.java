package com.wyr.leetcode.step1.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你两个整数数组nums1 和 nums2 ，请你以数组形式返回两数组的交集。
 * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致
 * （如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 * https://leetcode.cn/problems/intersection-of-two-arrays-ii/submissions/
 */


public class IntersectTest {
    public int[] intersect(int[] nums1, int[] nums2) {

        //对两个数组都先排好序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        //谁小谁移动，相等一起移动并加入到集合中
        ArrayList<Integer> list=new ArrayList<>();
        int p1=0,p2=0;
        int counts=0;
        while(p1!=nums1.length&&p2!=nums2.length){
            if(nums1[p1]<nums2[p2]){//谁小谁移动
                p1++;
            }else if(nums1[p1]>nums2[p2]){
                p2++;
            }else{
                //相同，先加入集合中，再一起移动，并计数
                list.add(nums1[p1]);
                p1++;
                p2++;
                counts++;
            }
        }
        int[] result=new int[counts];
        for(int i=0;i<counts;i++){
            result[i]=list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
