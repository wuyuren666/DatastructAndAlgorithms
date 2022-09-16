package com.wyr.leetcode.step2.dp;

import java.util.HashMap;
import java.util.Map;

public class MakeSquareTest {

    /**
     * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i个火柴棒的长度。你要用 所有的火柴棍拼成一个正方形。
     * 你不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须使用一次 。
     *
     * 如果你能使这个正方形，则返回 true ，否则返回 false 。
     *
     * 输入: matchsticks = [1,1,2,2,2]
     * 输出: true
     * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
     *
     * 输入: matchsticks = [3,3,3,3,4]
     * 输出: false
     * 解释: 不能用所有火柴拼成一个正方形。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/matchsticks-to-square
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 这里我们使用的是visited数组来标记火柴是否用过
     * 在process中看似有3个可变参数，visited，lengthOfCurEdge，rest。
     * 其实 lengthOfCurEdge 和 rest 都是可以由visited决定的
     * 并且visited，也不是必须使用数组，用int整数也可以
     */
    public boolean makesquare(int[] matchsticks) {
        int sum=0;
        for(int i=0;i<matchsticks.length;i++){
            sum+=matchsticks[i];
        }
        if(sum%4!=0){//都不能被4整除，肯定拼不成
            return false;
        }
        int lengthOfEachEdge=sum/4;//每一条边的长度
        //visited[i]==0，对应下标火柴没用过
        //visited[i]==1，对应下标火柴用过
        int [] visited=new int[matchsticks.length];
        return process(matchsticks,visited,0,4,lengthOfEachEdge);
    }
    //rest：剩下几条边没有拼成，当前边已经拼成的长度
    public boolean process(int [] matchsticks, int [] visited , int lengthOfCurEdge, int rest ,int length){
        if(rest==0){//边已经全部拼好了
            return true;
        }
        boolean flag=false;
        //拿出每一根火柴
        for(int i=0;i<matchsticks.length;i++){
            if(visited[i]==0){//当前火柴没有用过
                visited[i]=1;//标记为已经用过
                if(lengthOfCurEdge+matchsticks[i]<length){//当前处理的边的长度+当前火柴的长度<规定的每一个边的长度
                    flag|=process(matchsticks, visited, lengthOfCurEdge+matchsticks[i],rest,length);
                }else if(lengthOfCurEdge+matchsticks[i]==length){//当前处理的边的长度+当前火柴的长度==规定的每一个边的长度
                    flag|=process(matchsticks,visited,0,rest-1,length);
                }
                //当前处理的边的长度+当前火柴的长度>规定的每一个边的长度，直接拿下一根火柴再去试
                visited[i]=0;//取消标记
            }
        }
        return flag;
    }

    /**
     * 傻缓存的写法，没超时
     */
    public static boolean makesquareBest(int[] matchsticks) {
        int sum=0;
        for(int i=0;i<matchsticks.length;i++){
            sum+=matchsticks[i];
        }
        if(sum%4!=0){//都不能被4整除，肯定拼不成
            return false;
        }
        int lengthOfEachEdge=sum/4;//每一条边的长度
        //使用傻缓存
        Map<Integer,Boolean> map=new HashMap<>();
        return process(matchsticks,map,0,0,4,lengthOfEachEdge);
    }
    //rest：剩下几条边没有拼成，当前边已经拼成的长度
    public static boolean process(int [] matchsticks, Map<Integer,Boolean> map, int visited , int lengthOfCurEdge, int rest , int length){
        if(rest==0){//边已经全部拼好了
            return true;
        }
        if(map.containsKey(visited)){
            return map.get(visited);
        }
        boolean res=false;
        //拿出每一根火柴
        for(int i=0;i<matchsticks.length;i++){
            if((visited & (1<<i)) ==0){//当前火柴没有用过
                visited |= (1<<i);//标记为已经用过
                if(lengthOfCurEdge+matchsticks[i]<length){//当前处理的边的长度+当前火柴的长度<规定的每一个边的长度
                    res|=process(matchsticks,map,visited, lengthOfCurEdge+matchsticks[i],rest,length);
                }else if(lengthOfCurEdge+matchsticks[i]==length){//当前处理的边的长度+当前火柴的长度==规定的每一个边的长度
                    res|=process(matchsticks,map,visited,0,rest-1,length);
                }
                if(res){
                    return true;
                }
                //当前处理的边的长度+当前火柴的长度>规定的每一个边的长度，直接拿下一根火柴再去试
                visited^=(1<<i);//取消标记
            }
        }
        map.put(visited,res);
        return res;
    }

    public static boolean makesquare1(int[] matchsticks) {
        int sum=0;
        for(int num: matchsticks){
            sum+=num;
        }
        if(sum%4!=0)
            return false;

        int len=sum/4;
        return f(matchsticks,0,0,len,4);
    }
    //这是暴力递归，会超时
    //edges:还剩多少条边没有搞定；len:当前边已经搞定的长度
    public static boolean f(int []arr, int status, int cur, int len, int edges){
        if(edges==0)
            return true;
        boolean ans=false;
        //arr中，还没有尝试的火柴！全部是一遍
        for(int i=0;i< arr.length;i++) {
            if ((status & (1 << i)) == 0) {
                if(cur + arr[i] > len){ //不能用

                } else if (cur + arr[i] < len) {
                    ans |= f(arr, status | (1 << i), cur + arr[i], len, edges);
                } else {//cur + arr[i]==len
                    ans |= f(arr, status | (1 << i), 0, len, edges - 1);
                }
                if (ans) {
                    return true;
                }
            }
        }
        return ans;
    }
}
