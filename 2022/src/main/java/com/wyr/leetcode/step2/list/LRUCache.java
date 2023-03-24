package com.wyr.leetcode.step2.list;

import java.util.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class LRUCache {
    /**
     * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     *
     * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；
     * 如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，
     * 则应该 逐出 最久未使用的关键字。
     *
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/lru-cache
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private int cap;
    //链表尾部是最近最多才使用的，链表头部是最近最少使用的
    private LinkedHashMap<Integer,Integer> cache=new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.cap=capacity;
    }

    public int get(int key) {
        if(cache.keySet().contains(key)){ //缓存中有，就拿出来，并且放到链表的最后

            Integer value=cache.get(key);
            cache.remove(key);
            cache.put(key,value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.keySet().contains(key)){ //缓存中有，此时的put需要将缓存中原来的值替换成新值，并且要放到链表尾部
            cache.remove(key); //先删除
        }else{
            if(cache.size()==cap){//容量满了
                //拿到LinkedHashMap中的头部的key
                Iterator<Integer> iterator = cache.keySet().iterator();
                Integer head = iterator.next();
                cache.remove(head);//删除最近最少使用的元素
            }
        }
        cache.put(key,value);
    }


    public static  <K, V> Map.Entry<K, V> getHead(LinkedHashMap<K, V> map) {
        return map.entrySet().iterator().next();
    }

    public static  <K, V> Map.Entry<K, V> getTail(LinkedHashMap<K, V> map) {
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        Map.Entry<K, V> tail = null;
        while (iterator.hasNext()) {
            tail = iterator.next();
        }
        return tail;
    }

    public static void main(String[] args) {
    }
}
