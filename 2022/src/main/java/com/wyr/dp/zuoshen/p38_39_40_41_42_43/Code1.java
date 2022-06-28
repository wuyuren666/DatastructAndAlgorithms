package com.wyr.dp.zuoshen.p38_39_40_41_42_43;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示第N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台洗咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有的人拿到咖啡后立刻喝干净，
 * 返回从开始等到所有咖啡杯变干净的最短时间
 * 三个参数：int[] arr, int N, int a ,int b
 */
public class Code1 {
    public static class Machine{
        public int timePoint; //可以再次工作的时间点
        public int workTime; //工作持续时间

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    //首先，我们尝试获取每一个人喝完咖啡的最佳时间点
    public static int[] getMinTime(int [] arr,int N, int a, int b){
        //首先定义一个小根堆，按照每一台机器的可以再次工作的时间点+工作的持续时间进行从小到大排序
        PriorityQueue<Machine> minRootHeap=new PriorityQueue<>((o1,o2)->(o1.timePoint+o1.workTime)-(o2.timePoint+o2.workTime));
        for(int i=0;i<arr.length;i++){
            minRootHeap.add(new Machine(0,arr[i]));
        }
        int [] drinks=new int[N];
        for(int i=0;i<N;i++){
            Machine machine = minRootHeap.poll(); //从小顶堆中弹出最佳的机器
            machine.timePoint= machine.timePoint+ machine.workTime;//当前机器的再次可使用时间点进行更新
            drinks[i]= machine.timePoint;//记录每一个人喝完的最佳时间
            minRootHeap.add(machine);//将更新好的机器再次放入小顶堆中进行排序
        }
        return drinks;
    }

    //暴力递归
    //drinks: 每个人最短时间喝完咖啡的时间点
    //wash：洗咖啡机洗一杯所需要的时间(串行)
    //air：自己挥发干净所需要的时间(并行)
    //index：从第index号杯子开始尝试
    //workTimePoint：洗咖啡机可以再次工作的时间点
    public static int process1(int [] drinks, int wash, int air ,int index, int workTimePoint){
        if(index>=drinks.length){
            return 0;
        }

        //当前的杯子我决定用咖啡机去洗
        //selfWash1：当前杯子洗干净结束的时间点
        //依赖于洗咖啡机的可以工作时间点和当前杯子可以去洗的时间点
        int selfWash1=Math.max(drinks[index],workTimePoint)+wash;
        int rest1=process1(drinks,wash,air,index+1,selfWash1);//注意洗咖啡机可以再次工作的时间点已经变了
        //因为所有杯子是一个整体，当前杯子洗干净的时间点，和剩下杯子洗干净的时间点，我们要取较大值
        int p1=Math.max(selfWash1,rest1);


        //当前杯子我选择挥发
        int selfAir2=drinks[index]+air;
        int rest2=process1(drinks,wash,air,index+1,workTimePoint);//此时咖啡机可以再次工作的时间点不需要改变
        int p2=Math.max(selfAir2,rest2);

        //两种方案中选择较小的值
        return Math.min(p1,p2);
    }



    //暴力递归+傻缓存
    //drinks: 每个人最短时间喝完咖啡的时间点
    //wash：洗咖啡机洗一杯所需要的时间(串行)
    //air：自己挥发干净所需要的时间(并行)
    //index：从第index号杯子开始尝试
    //workTimePoint：洗咖啡机可以再次工作的时间点
    //map：傻缓存
    public static int process2(int [] drinks, int wash, int air ,int index, int workTimePoint, Map<Info,Integer> map){
        Info curKey = new Info(index,workTimePoint);
        if(map.containsKey(curKey)){
             return map.get(curKey);
        }

        if(index>=drinks.length){
            return 0;
        }

        //当前的杯子我决定用咖啡机去洗
        //selfWash1：当前杯子洗干净结束的时间点
        //依赖于洗咖啡机的可以工作时间点和当前杯子可以去洗的时间点
        int selfWash1=Math.max(drinks[index],workTimePoint)+wash;
        int rest1=process2(drinks,wash,air,index+1,selfWash1,map);//注意洗咖啡机可以再次工作的时间点已经变了
        //因为所有杯子是一个整体，当前杯子洗干净的时间点，和剩下杯子洗干净的时间点，我们要取较大值
        int p1=Math.max(selfWash1,rest1);


        //当前杯子我选择挥发
        int selfAir2=drinks[index]+air;
        int rest2=process2(drinks,wash,air,index+1,workTimePoint,map);//此时咖啡机可以再次工作的时间点不需要改变
        int p2=Math.max(selfAir2,rest2);

        //两种方案中选择较小的值
        int result=Math.min(p1,p2);
        //加入缓存
        map.put(curKey,result);

        return  result;
    }



    public static class Info{
        public int index;
        public int workTimePoint;

        public Info(int index, int workTimePoint) {
            this.index = index;
            this.workTimePoint = workTimePoint;
        }


        //傻缓存，需要重写equals和hashCode方法，因为内容相同的Info，我们认为是同一个key
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return index == info.index && workTimePoint == info.workTimePoint;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, workTimePoint);
        }
    }

    public static void main(String[] args) {
        int [] arr={5,6,8,8,4};
        int N=30;
        int a=5;
        int b=6;
        Map<Info,Integer> map=new HashMap<>();
        long l1 = System.currentTimeMillis();
        System.out.println(process1(getMinTime(arr, N, a, b), a, b, 0, 0));
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);

        long l3 = System.currentTimeMillis();
        System.out.println(process2(getMinTime(arr, N, a, b), a, b, 0, 0,map));
        long l4 = System.currentTimeMillis();
        System.out.println(l4-l3);
    }

}
