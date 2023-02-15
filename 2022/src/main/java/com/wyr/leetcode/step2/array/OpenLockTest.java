package com.wyr.leetcode.step2.array;

import java.util.*;

public class OpenLockTest {
    /**
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     *
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     *
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     *
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/open-the-lock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //这类题，只要脑子里有一棵树，就能够写出来代码
    //同一层的节点是一个对列中的所有节点
    //因此，在便利这一层所有节点的时候，会将下一层的节点构造出来放入队列中，同时自己不会出现在队列中了
    //一般就是while套着for循环去写
    //while的逻辑就是代表层，for逻辑就是代表这一层所有节点的遍历
    public int openLock(String[] deadends, String target) {
        //放入所有的死亡数字
        Set<String> deadStr=new HashSet<>();
        for(String s: deadends)
            deadStr.add(s);
        //BFS使用的队列
        Queue<String> queue = new LinkedList<>();
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        //初始化队列
        queue.add("0000");
        visited.add("0000");
        int step=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            //遍历同一层的节点（其实就是同一层的节点）
            for(int i=0;i<size;i++){
                String curStr=queue.poll();
                //碰到死亡组合
                if(deadStr.contains(curStr)){
                    continue;
                }
                //达到目标
                if(curStr.equals(target)){
                    return step;
                }
                /* 将一个节点的未遍历相邻节点(下一层节点)加入队列 */
                for(int j=0;j<4;j++){
                    String plusOneStr = plusOne(curStr,j);
                    if(!visited.contains(plusOneStr)){
                        queue.add(plusOneStr);
                        visited.add(plusOneStr);
                    }
                    String subOneStr = subOne(curStr,j);
                    if(!visited.contains(subOneStr)){
                        queue.add(subOneStr);
                        visited.add(subOneStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 将 s[j] 向上拨动一次
     static String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    // 将 s[i] 向下拨动一次
    static String subOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

}
