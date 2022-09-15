package com.wyr.leetcode.step2.huisu;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3Test {
    /**
     * 找出所有相加之和为n 的k个数的组合，且满足下列条件：
     *
     * 只使用数字1到9
     * 每个数字最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //0ms
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        int [] nums=new int[9];
        for(int i=0;i<9;i++){
            nums[i]=i+1;
        }
        process(nums,k,n,0,new ArrayList<Integer>(),0);
        return res;
    }

    public void process(int [] nums, int k, int n ,int index, List<Integer> tempList, int tempSum){
        if(index==9){
            if(tempList.size()==k&&tempSum==n){
                res.add(new ArrayList<>(tempList));
            }
            return;
        }
        //要当前的数
        tempList.add(nums[index]);
        process(nums,k,n,index+1,tempList,tempSum+nums[index]);
        //不要当前的数
        tempList.remove(tempList.size()-1);
        process(nums,k,n,index+1,tempList,tempSum);
    }
}
