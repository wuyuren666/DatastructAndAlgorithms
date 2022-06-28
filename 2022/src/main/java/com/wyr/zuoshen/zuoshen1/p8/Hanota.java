package com.wyr.zuoshen.zuoshen1.p8;

import java.util.ArrayList;
import java.util.List;

public class Hanota {
    /**
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
     * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
     * (1) 每次只能移动一个盘子;
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * (3) 盘子只能叠在比它大的盘子上。
     *
     * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/hanota-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        process(A.size()-1,A,C,B);
    }

    public static void process(int i, List<Integer> from, List<Integer> to, List<Integer> other){
        if(i==0){
            to.add(from.remove(from.size()-1));
        }else{
            process(i-1,from,other,to);
            to.add(from.remove(from.size()-1));
            process(i-1,other,to,from);
        }
    }




    public static void printHanotaMovePath(int n){
        process1(n,"左","右","中");
    }

    public static void process1(int i, String from, String to, String other){
        if(i==1){
            System.out.println("将"+i+"号圆盘从"+from+"移动到"+to);
        }else{
            //先将1~i-1号圆盘从from移动到other，借助to
            process1(i-1,from,other,to);
            //直接将i号圆盘从from移动到to
            System.out.println("将"+i+"号圆盘从"+from+"移动到"+to);
            //再将1~i-1号圆盘从other移动到to上，借助from
            process1(i-1,other,to,from);
        }
    }

    public static void main(String[] args) {
        printHanotaMovePath(3);
    }
}
