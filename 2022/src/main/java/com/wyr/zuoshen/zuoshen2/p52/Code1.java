package com.wyr.zuoshen.zuoshen2.p52;

public class Code1 {
    /**
     * 题目：
     * 现有司机N*2个人，调度中心会将所有司机平均分配给A，B两个区域
     * 第i个司机去A可以获得的收入为income[i][0]
     * 第i个司机去B可以获得的收入为income[i][1]
     * 返回所有调度方案中能使得所有司机总收入最高的方案，是多少钱
     *
     * leetcode上相似的题目
     * 公司计划面试 2n 人。给你一个数组 costs ，其中 costs[i] = [aCosti, bCosti] 。
     * 第 i 人飞往 a 市的费用为 aCosti ，飞往 b 市的费用为 bCosti 。
     * 返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/two-city-scheduling
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static int maxMoney(int[][] income){
        //income.length一定是偶数
        if(income==null||income.length<1||(income.length&1)!=0){
            return 0;
        }
        int M=income.length;//司机是偶数个人
        int rest=M>>1;//去A的一共这么多人。M/2
        return process(income,0,rest);
    }

    //返回第index...这写司机，在平均分给A和B地区相同人数的情况下，且去A的人数为rest人的情况下，能够获得的最大收入
    public static int process(int [][] income, int index, int rest){
        if(index==income.length){ //没有司机了
            return 0;
        }
        //剩下司机的人数==还需要分配去A地区的人数，那么这些剩下的司机只能全都去A，
        // 满足这个条件之后的递归行为，全部都会近这个if逻辑中
        if(income.length-index==rest){
             return income[index][0]+process(income,index+1,rest-1);
        }
        //去A地区的司机分配满了
        //剩下的司机只能都去B地区
        if(rest==0){
             return income[index][1]+process(income,index+1,rest);
        }
        //流程走到这里，说明，剩下的司机既可以去A地区，也可以去B地区
        //返回这两种可能性中的较大的
        int p1=income[index][0]+process(income,index+1,rest-1);
        int p2=income[index][1]+process(income,index+1,rest);
        return Math.max(p1,p2);
    }


    //leetCode上的题，加入傻缓存，最后过了：1ms，98.75%
    public int twoCitySchedCost(int[][] costs) {
        int M=costs.length;
        int [][] dp=new int [M+1][(M>>1)+1];
        for(int i=0;i<M+1;i++){
            for(int j=0;j<(M>>1)+1;j++){
                dp[i][j]=-1;
            }
        }
        return process(costs,0,M>>1,dp);
    }
    //rest为去往a市的人数
    public int process(int [][] costs, int index, int rest,int [][] dp){
        if(dp[index][rest]!=-1){
            return dp[index][rest];
        }
        int res=0;
        //当前没有人了
        if(index==costs.length){
            res=0;
        }else if(costs.length-index==rest){
            //剩下的人数==rest
            res=costs[index][0]+process(costs,index+1,rest-1,dp);
        }else if(rest==0){ //rest==0，剩下的人只能都去B
            res=costs[index][1]+process(costs,index+1,rest,dp);
        }else{
            //剩下的人，既可以选择去a，也可以选择去b
            int p1=costs[index][0]+process(costs,index+1,rest-1,dp);
            int p2=costs[index][1]+process(costs,index+1,rest,dp);
            res= Math.min(p1,p2);
        }
        dp[index][rest]=res;
        return res;
    }


}
