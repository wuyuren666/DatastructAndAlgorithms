package com.wyr.bidFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将n个数放到k个容器中，使得这k个容器中的数字之后最接近
 */
public class PingJunFenPei {

    //贪心的思想
    public static void allocate(int[] nums, int k) {
        Arrays.sort(nums); // 将数字按照从大到小的顺序排列
        List<List<Integer>> list =new ArrayList<>();//里面的每一个list代表容器
        for(int i=0;i<k;i++){
            list.add(new ArrayList<>());
        }
        int[] sums = new int[k]; // 容器中的数字和
        Arrays.fill(sums, 0); // 将容器中的数字和初始化为0

        // 将每个数字依次分配到容器中，从大到小
        for (int i = nums.length - 1; i >= 0; i--) {
            int minSumIndex = 0; // 容器中数字和最小的的容器下标
            for (int j = 1; j < k; j++) { //遍历找到容器中数字和最小的容器下标
                if (sums[j] < sums[minSumIndex]) {
                    minSumIndex = j;
                }
            }
            // 将数字分配到容器中
            sums[minSumIndex] += nums[i];
            list.get(minSumIndex).add(nums[i]);
        }
        //输出容器中的数字
        for (int i = 1; i <= list.size(); i++) {
            System.out.print("容器"+i+":");
            for(int j=0;j<list.get(i-1).size();j++){
                System.out.print(list.get(i-1).get(j)+" ");
            }
            System.out.println();
        }
    }

    // 测试
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9,10};
        int k = 3;
        allocate(nums, k);
    }
}
