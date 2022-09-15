package com.wyr.bigFactory.date9_1;

import java.util.*;

public class WeiMaoTest {
    /**
     * 小美在回家路上碰见很多缠人的可爱猫猫！因为猫猫太可爱了以及小美十分有爱心，
     * 每遇到一只猫猫，小美忍不住停下来花费T的时间抚摸猫猫，让猫猫不在缠着小美。
     * 而小美一路上能捡到很多亮闪闪的小玩具，这里我们给每一个小玩具的种类都编了号
     * ，从1～k，一共k种小玩具，对于每个所属类别的小玩具，小美可以选择将它送给遇到
     * 的小猫玩，这样的话可以只花费ti的时间就可以让这只猫猫心满意足的离开。小美想知道
     * 在她以最佳的对小玩具的用法下，她最少耗费多少时间在打发猫猫（即只考虑摸猫时间
     * 以及用小玩具打发猫的时间）。
     * 注意，每个捡到的小玩具只能用一次
     *
     * 输入描述：
     * 第一行三个正整数n、k和T，分别表示小美回家遇见的事件数、小玩具种类总数以及摸猫时间！
     * 接下来一行，是k个数，代表每一种小玩具打发猫所用的时间
     * 接下来一行n个数，表示发生的n此事件，
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N=scanner.nextInt();//事件数
        int K=scanner.nextInt();//玩具种类
        int T=scanner.nextInt();//摸猫时间
        scanner.nextLine();
        //优先级队列，按照玩具打发猫的时间作为排序的标准，从小到大
        PriorityQueue<Toy> toys=new PriorityQueue<>((o1,o2)->o1.time- o2.time);
        int [] toyTypeTime=new int[K+1]; //玩具种类对应的打发时间
        for(int i=1;i<=K;i++){
            int time=scanner.nextInt();
            toyTypeTime[i]=time;
        }
        scanner.nextLine();
        int [] thing=new int[N];
        for(int i=0;i<N;i++){
            thing[i]=scanner.nextInt();
        }
        int allConsumeTime=0;
        for(int i=0;i<thing.length;i++){
            //碰到猫
            if(thing[i]==0){
                //没玩具
                if(toys.size()==0){
                    allConsumeTime+=T;
                }else{//有玩具
                    //先拿哄猫时间最短的玩具
                    Toy cur = toys.poll();
                    allConsumeTime+= cur.time;
                }
            }else{//碰到玩具
                toys.add(new Toy(thing[i],toyTypeTime[thing[i]]));
            }
        }
        System.out.println(allConsumeTime);
        scanner.close();
    }

    public static class Toy{
        public int type;
        public int time;

        public Toy(int type, int time) {
            this.type = type;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Toy toy = (Toy) o;
            return type == toy.type && time == toy.time;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, time);
        }
    }
}
