package com.wyr.leetcode.step2.huisu;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumTest {
    /**
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，
     * 找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
     *
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    /**
     *    这个题还可以这样问：
     *    我有7块钱，现在有2，3，6，7这四种零钱，每一种无数张，给出返回能够兑换的所有方案数
     */
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        process(candidates,0,target,new ArrayList<>());
        return res;
    }

    // 3ms
    public void process(int[] candidates, int index, int rest,  List<Integer> tempList){
        //都没数了，你搞定没有？
        if(index==candidates.length){
            if(rest==0){
                res.add(new ArrayList<>(tempList));
            }
            return;
        }
        //从一个不拿，到rest/candidates[index] 一一去尝试
        for(int i=0;i<=rest/candidates[index];i++){
            for(int j=0;j<i;j++){
                tempList.add(candidates[index]);
            }
            process(candidates,index+1,rest-candidates[index]*i,tempList);
            for(int j=0;j<i;j++){
                tempList.remove(tempList.size()-1);
            }
        }
    }

    //6ms
    public void process2(int[] candidates, int index, int rest,  List<Integer> tempList){
        //都没数了，你搞定没有？
        if(index==candidates.length){
            if(rest==0){
                res.add(tempList);
            }
            return;
        }

        //从一个不拿，到rest/candidates[index] 一一去尝试
        for(int i=0;i<=rest/candidates[index];i++){
            //复制一份
            List<Integer> copy=new ArrayList<>(tempList);
            for(int j=0;j<i;j++){
                copy.add(candidates[index]);
            }
            process2(candidates,index+1,rest-candidates[index]*i,copy);
        }
    }
}
