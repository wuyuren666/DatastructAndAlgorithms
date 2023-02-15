package com.wyr.leetcode.step2.list;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {
    /**
     * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
     *
     * 实现 LFUCache 类：
     *
     * LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
     * int get(int key)- 如果键key 存在于缓存中，则获取键的值，否则返回 -1 。
     * void put(int key, int value)- 如果键key 已存在，则变更其值；如果键不存在，请插入键值对。
     * 当缓存达到其容量capacity 时，则应该在插入新项之前，移除最不经常使用的项。
     * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
     * 为了确定最不常使用的键，可以为缓存中的每个键维护一个使用计数器。使用计数最小的键是最久未使用的键。
     *
     * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。
     * 对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
     *
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/lfu-cache
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static PriorityQueue<Node> queue=new PriorityQueue<>();
    private static Map<Integer,Node> cache=new HashMap<>();
    private static int ids;//全局自增id
    private static int cap;//缓存容量
    public LFUCache(int capacity) {
        this.ids=0;
        this.cap=capacity;
    }
    //获取值的方法
    public static int get(int key) {

        if(cache.keySet().contains(key)){ //存在这个key
            Node node = cache.get(key);
            node.ids=ids++; //更新访问次序
            node.fre++;//更新访问频次
            queue.remove(node);//从小根堆中删除
            queue.offer(node);//加入小根堆重新维持堆结构
            return node.value;
        }
        return -1;
    }
    //存放元素的方法
    public static void put(int key, int value) {
        if(cap==0){ //测试用例中有cap==0的情况
            return;
        }
        if(cache.keySet().contains(key)){ //缓存中有
            Node node = cache.get(key); //将缓存中的项拿出
            node.ids=ids++; //使用全局ids更新访问次序
            node.fre++; //更新访问频次
            node.value=value; //更新值
            queue.remove(node);//先从小根堆中删除
            queue.offer(node);//加入小根堆重新维持堆结构
        }else{
            Node newNode=new Node(key,value,ids++,1); //new一个新的节点
            if(cache.size()==cap){ //缓存中的容量已满
                Node poll = queue.poll();//弹出使用频次最低的项
                cache.remove(poll.key); //从小根堆中删除
            }
            cache.put(key,newNode);//缓存中加入
            queue.offer(newNode);//加入到小根堆中维持堆结构
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3,3);

    }

    static class Node implements Comparable<Node>{
        public int key;
        public int value;
        public int ids; //全局自增的id
        public int fre; //访问频次

        @Override
        public int compareTo(Node o) {
            return this.fre==o.fre?this.ids-o.ids:this.fre-o.fre;
        }

        public Node(int key, int value, int ids, int fre) {
            this.key = key;
            this.value = value;
            this.ids = ids;
            this.fre = fre;
        }
    }

}

