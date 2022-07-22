package com.wyr.zuoshen.zuoshen2.p53;

import java.awt.event.ComponentAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Code1 {
    /**
     * 设计含有setAll功能的哈希表
     */

    private long time=0; //时间戳，每次的put操作和setAll操作都会将时间增长
    private long setAllTime=Long.MAX_VALUE; //你执行setAll方法的时候的时间，系统最大代表你没有执行过setAll的行为
    private Map<Integer, Info> map=new HashMap<>();



    public void put(int key, int  value){
        //先看一下是更新操作还是新增操作
        if(map.containsKey(key)){
            Info info = map.get(key);
            info.value=value;
            info.time=time++;
        }else{
            Info info = new Info(value, time++);
            map.put(key,info);
        }
    }

    public void setAll(int value){
        //什么时候发生的setAll的行为
        setAllTime=time++;
        for(Map.Entry<Integer,Info> entry: map.entrySet()){
            Info v = entry.getValue();
            Integer key = entry.getKey();
            if(v.time<setAllTime){
                map.get(key).value=value;
            }
        }
    }


    public static class Info{
        public int value;
        public long time;

        public Info(int value, long time) {
            this.value = value;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Code1 code1 = new Code1();
        code1.put(1,2);
        code1.put(2,3);
        code1.put(3,4);
        code1.setAll(5);
        code1.put(2,3);
        code1.put(3,4);
        code1.put(7,8);
        code1.setAll(9);
    }
}
