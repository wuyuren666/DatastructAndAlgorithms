package com.wyr.zuoshen.zuoshen1.p9;

import java.util.HashMap;

public class RandomPool {
    /**
     * 题目
     * 设计一种结构，在改结构中有如下三个功能：
     * insert(key)：将某个key加入到改结构中，做到不重复加入
     * delete(key)：将原本在结构中的某个key移除
     * getRandom()：等概率随机返回结构中的任何一个key
     */
    public static class Pool<K>{
        public HashMap<K,Integer> keyIndexMap;
        public HashMap<Integer,K> indexKeyMap;
        public int size;

        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key){
            if(!keyIndexMap.containsKey(key)){ //做到不重复加入
                 keyIndexMap.put(key,size);
                 indexKeyMap.put(size++,key);
            }
        }

        public void delete(K key){
            if(keyIndexMap.containsKey(key)){
                //获得需要删除的key所对应的Index
                Integer delKeyIndex = keyIndexMap.get(key);
                //获得最后一个index所对应的key
                K lastKey=indexKeyMap.get(size-1);

                keyIndexMap.put(lastKey,delKeyIndex);
                indexKeyMap.put(delKeyIndex,lastKey);

                keyIndexMap.remove(key);
                indexKeyMap.remove(--size);
            }
        }

        public K getRandom(){
            if(size==0){
                return null;
            }
            //等概率返回0~size-1范围的整数
            return indexKeyMap.get((int) (Math.random()*size));
        }
    }

    public static void main(String[] args) {

    }

}
