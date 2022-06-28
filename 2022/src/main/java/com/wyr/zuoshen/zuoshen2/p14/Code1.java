package com.wyr.zuoshen.zuoshen2.p14;

/**
 * 假设我们有一个f函数可以等概率的返回1~5中的这5个整数。
 *
 * 那么如何利用这个f函数在1～6上等概率的返回这6个数
 *
 *
 * 推广：其实随便给一个f无论他是a~b(不论a是什么，b是什么)上等概率返回几个数
 * 都能利用这个f构造成一个g函数等概率的返回c~d上的这些数字
 */
public class Code1 {
    //f函数不能改
    public static int f(){
        //Math.random()：等概率返回[0,1)上的小数
        //(Math.random()*5)等概率返回[0,5)上的小数
        //(int)(Math.random()*5)等概率返回0,1,2,3,4这5个数
        //(int)(Math.random()*5)+1等概率返回1,2,3,4,5这5个整数
        return (int)(Math.random()*5)+1;
    }

    //我们利用这个f函数,等概率返回0或者1
    //0，1 等概率发生器
    public static int help(){
        int a=0;
        do{
            a=f();
        }while (a==3);
        // 1,2 :0
        // 4,5 :1
        return a<3?0:1;
    }


    //在000~111(二进制)上等概率的获取0~7这8个数
    public static int g(){
        return (help()<<2)+(help()<<1)+(help()<<0);
    }


    //1~6上等概率的返回这6个数
    public static int g2(){
        int a=0;
        do{
            a=g();
        }while (a==7||a==0);
        return a;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////

    //推广1：
    //现在这个f他能够在10~17上等概率的返回这些整数
    public static int f1(){
       return  (int)(Math.random()*8)+10;
    }

    //利用f1做等概率0/1产生器
    public static int help2(){
        //10,11,12,13代表0
        //14，15，16，17代表1
        return f1()<=13?0:1;
    }

    //现在我们想利用f1等概率返回17～56这些数
    //首先我们等概率返回0~39，然后+17不就行了
    //怎么样等概率返回0~39呢？首先要6位二进制数可以表示0~39
    public static int g3(){
        int res=0;
        do {
            res=(help2()<<5)+(help2()<<4)+(help2()<<3)+(help2()<<2)+(help2()<<1)+(help2()<<0);
        }while (res>39);

        return res+17;
    }


///////////////////////////////////////////////////////////////////////////////////////////////
    //推广二
    //你只能知道,x函数会以固定的概率返回0或者1,但是x的内容你看不到
    public static int x(){
        //84%概率返回0
        return Math.random()<0.84?0:1;
    }

    //利用这个x获得一个0/1的等概率发生器
    public static int y(){
        int res=0;
        do{
            res=x();
        }while (res==x());//当两次返回是一样的，你重做去吧
        return res;
    }








    public static void main(String[] args) {
       /* //0~7这8个数统计出现的次数
        int[] counts=new int[8];

        for(int i=0;i<100000;i++){
            int num=g2();
            counts[num]++;
        }

        for (int i=0;i<8;i++){
            System.out.println(i+"这个数出现了"+counts[i]+"次");
        }
        */


        //验证推广
        int [] counts2=new int[40];
        for(int i=0;i<100000;i++){
            int num=g3();
            counts2[num-17]++;
        }

        for (int i=0;i<40;i++){
            System.out.println(i+17+"这个数出现了"+counts2[i]+"次");
        }
    }


}
