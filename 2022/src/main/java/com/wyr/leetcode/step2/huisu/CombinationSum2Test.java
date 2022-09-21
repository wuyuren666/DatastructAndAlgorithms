package com.wyr.leetcode.step2.huisu;

import java.util.*;

public class CombinationSum2Test {
    /**
     * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     *
     * candidates中的每个数字在每个组合中只能使用一次。
     *
     * 注意：解集不能包含重复的组合。
     *
     * 输入: candidates =[10,1,2,7,6,1,5], target =8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    //没过超时间了
    Set<List<Integer>> helpSet=new HashSet<>();//对于相同的list会去重（加入的顺序和元素值都一样的list）
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //先排序，保证相同的数字，前后两次加进去的结果一致
        Arrays.sort(candidates);
        process(candidates,target,0,new ArrayList<>(),0);
        List<List<Integer>> res=new ArrayList<>(helpSet);
        return res;
    }


    public void process(int [] candidates, int target, int index, List<Integer> tempList, int tempSum){
        if(index==candidates.length){
            if(tempSum==target){
                helpSet.add(new ArrayList<>(tempList));
            }
            return;
        }
        //要当前的数
        tempList.add(candidates[index]);
        process(candidates,target,index+1,tempList,tempSum+candidates[index]);
        //不要当前的数
        tempList.remove(tempList.size()-1);
        process(candidates,target,index+1,tempList,tempSum);
    }



    List<List<Integer>> list=new ArrayList<>();
    List<Integer> path=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,target,0);
        return list;
    }
    private void dfs(int[] candidates, int target,int index){
        if(target==0){
            list.add(new ArrayList<>(path));
            return;
        }
        //这里挺巧妙的
        for(int i=index;i<candidates.length;i++){
            if(candidates[i]<=target){
                if(i>index&&candidates[i]==candidates[i-1]){//重复的数字只用一次
                    continue;
                }
                path.add(candidates[i]);
                dfs(candidates,target-candidates[i],i+1);
                path.remove(path.size()-1);
            }
        }
    }
}
