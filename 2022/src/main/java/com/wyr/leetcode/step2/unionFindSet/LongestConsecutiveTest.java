package com.wyr.leetcode.step2.unionFindSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class LongestConsecutiveTest {
    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
     *
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-consecutive-sequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int longestConsecutive(int[] nums) {
        int N=nums.length;
        int result=0;
        if(N==0){
            return result;
        }
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(nums[i]);
        }
        UnionFindSet<Integer> ufs=new UnionFindSet<>(list);
        for(int i=0;i<N;i++){
            int cur=nums[i];
            int before=cur-1;
            int after=cur+1;
            //有这个key所对应的元素，且不在同一集合中
            if(ufs.elementMap.containsKey(before)&&!ufs.isSameSet(before,cur)){
                ufs.union(before,cur);
            }
            //有这个key所对应的元素，且不在同一集合中
            if(ufs.elementMap.containsKey(after)&&!ufs.isSameSet(after,cur)){
                ufs.union(after,cur);
            }
        }

        for(Integer i:ufs.sizeMap.values()){
            if(i>result){
                result=i;
            }
        }
        return result;
    }


    public static class Element<V>{
        public V value;
        public Element(V value){
            this.value=value;
        }
    }

    public static class UnionFindSet<V> {
        public HashMap<V,Element<V>> elementMap;
        public HashMap<Element<V>,Element<V>> fatherMap;
        public HashMap<Element<V>,Integer> sizeMap;

        public UnionFindSet(List<V> list){
            elementMap=new HashMap<>();
            fatherMap=new HashMap<>();
            sizeMap=new HashMap<>();
            for(V value: list){
                Element<V> element=new Element<>(value);
                elementMap.put(value,element);
                fatherMap.put(element,element);
                sizeMap.put(element,1);
            }
        }
        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> stack=new Stack<>();
            while(element!=fatherMap.get(element)){
                stack.push(element);
                element=fatherMap.get(element);
            }
            //扁平化处理
            while(!stack.isEmpty()){
                fatherMap.put(stack.pop(),element);
            }
            return element;
        }
        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                return findHead(elementMap.get(a))==findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                Element<V> headA=findHead(elementMap.get(a));
                Element<V> headB=findHead(elementMap.get(b));
                if(headA!=headB){
                    Element<V> big=sizeMap.get(headA)>sizeMap.get(headB)?headA:headB;
                    Element<V> small=big==headA?headB:headA;
                    //先将数量较少的挂到数量较多的代表元素下
                    fatherMap.put(small,big);
                    //更新sizeMap
                    sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
                    sizeMap.remove(small);
                }
            }
        }
    }
}
