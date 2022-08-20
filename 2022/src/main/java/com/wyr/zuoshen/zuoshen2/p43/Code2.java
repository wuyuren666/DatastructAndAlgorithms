package com.wyr.zuoshen.zuoshen2.p43;

import java.util.List;

public class Code2 {
    /**
     * 派对的最大快乐值
     * 公司的每一个员工都符合Employee类的描述。整个公司的员工可以看作是一颗标准的、没有环的多叉树。
     * 树的头节点是公司的唯一老板。除老板之外的每一个员工都有唯一的直接上级。
     * 叶子结点是没有任何下级的基层员工，除基层员工外，每个员工都有一个或多个直接下级
     *
     * 现在公司要举行派对
     * 你可以决定那些员工来，那些员工不来
     * 1：如果某个员工来了，那么这个员工的所有直接下级都不能来
     * 2：派对的整体快乐值是所有员工的快乐值的累加
     * 3：你的目标是让派对的整体快乐值尽量大
     * 给你一个多叉树的头节点boss，请返回派对的最大快乐值
     */
    public static class Employee{
        public int happyValue;
        public List<Employee> nexts;

        public Employee(int happyValue, List<Employee> employees) {
            this.happyValue = happyValue;
            this.nexts = employees;
        }
    }


    public static class Info{
        public int yes; //头节点在来的情况下整棵树的最大快乐值
        public int no;//头节点在不来的情况下整棵树的最大快乐值

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }




    public static Info process(Employee root){
        if(root.nexts.isEmpty()){ //baseCase，没有下级了
            return  new Info(root.happyValue,0);
        }

        int yes=root.happyValue; //当前员工来的情况下的总的最大快乐值
        int no=0; //不来情况下的总的最大快乐值
        for(Employee e: root.nexts){
            Info nextInfo = process(e);
            yes+= nextInfo.no; //root决定来，累加直接下级员工不来时的最大快乐值
            //root决定不来,累加所有直接下级员工不来/来的情况下的最大值
            no+= Math.max(nextInfo.no, nextInfo.yes);
        }
        return new Info(yes,no);
    }


}
