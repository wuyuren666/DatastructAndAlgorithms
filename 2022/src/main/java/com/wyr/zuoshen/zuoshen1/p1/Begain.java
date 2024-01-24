package com.wyr.zuoshen.zuoshen1.p1;

/**
 * b站链接：https://www.bilibili.com/video/BV13g41157hK?spm_id_from=333.337.search-card.all.click
 */
public class Begain {
    public static void main(String[] args) {

       System.out.println(getOneNumber510(1,2,3,2,3,3,1,1,3));
       getTwoNumber510(1,2,3,2,3,3,1,1);
    }


    /**
     * 常见面试题1
     * https://leetcode.cn/problems/single-element-in-a-sorted-array/
     * 传入一个数组，只有一个数出现了奇数次，其他的数都出现了偶数次，求出出现奇数次的这个数
     * 比如[1,2,3,2,3,3,1,1,3]==>[1,1,1,2,2,3,3,3,3]；因为异或不管顺序，所以偶数的两个2去异或为0，偶数的四个3去异或为0，奇数的三个1异或为1。
     * 使用异或很简单！
     */
    //5.10练习
    public static int getOneNumber510(int ...arr){
        int eor=0;
        for (int i : arr) {
            eor=eor^i;
        }
        return eor;
    }

















    //5.5练习
    public static int getOneNumber55(int ... arr){
        int eor=0;
        for (int i : arr) {
            eor=eor^i;
        }
        return eor;
    }








    //4.30练习
    public static int getOneNumber430(int ...arr){
        int eor=0;
        for (int i : arr) {
            eor=eor^i;
        }
        return eor;
    }





    public static int getOneNumber(int...a){
        int eor=0;
        for (int i : a) {
            eor=eor^i;
        }
        return eor;
    }



    /**
     * 常见面试题2
     * 传入一个数组，只有两个数出现了奇数次，其他的数都出现了偶数次，求出出现奇数次的这两个数
     * 比如[1,2,3,2,3,3,1,1]==>[1,1,1,2,2,3,3,3]；
     * 假设我们的a,b出现的是奇数次，剩下的数出现的都是偶数次
     */
    //5.10练习
    public static void getTwoNumber510(int...arr){
        int eor1=0;
        int eor2=0;
        for (int i : arr) {
            eor1=eor1^i;
        }
        //for循环结束后eor1=a^b
        int rightOne=eor1&(~eor1+1);//最低位为1的位置
        for (int i : arr) {
            if((rightOne&i)==0){
                eor2=eor2^i;
            }
        }
        System.out.println(eor2);
        System.out.println(eor1^eor2);
    }




    public static void getTwoNumber214(int ...arr){
        int eor1=0;
        int eor2=0;
        for(int i: arr){
            eor1^=i;
        }
        //此时eor1的结果就是a^b
        int rightOne=eor1&(~eor1+1);//找出最右边的1
        for(int i:arr){
            if((rightOne&i)==0){
                eor2^=i;
            }
        }
        System.out.println(eor2);
        System.out.println(eor1^eor2);
    }






    //5.5练习
    public static void getTwoNumber55(int ... arr){
        int eor1=0;
        int eor2=0;
        for (int i : arr) {
            eor1=eor1^i;
        }
        //结束时，eor1=a^b；a，b就是那两个出现奇数次的数
        //并且eor1!=0,因为题目说，a，b不相同。
        //既然，不相同，那么我们找出最右边，那个为1的位置
        int rightOne=eor1&(~eor1+1);//00010
        for (int i : arr) {
            if((rightOne&i)==0){
                eor2=eor2^i;
            }
        }
        System.out.println(eor2);
        System.out.println(eor1^eor2);
    }











    //4.30练习
    public static void getTwoNumber430(int ...arr){
        int eor1=0;
        int eor2=0;
        for (int i : arr) {
            eor1=eor1^i;
        }
        //for循环结束后，eor1就是a^b，即这两个出现奇数次且不想等的两个数
        //eor1!=0,很显然，二进制数中肯定至少有一位不为0,那么我们找出最右边不为0的这一位
        int right=eor1&(~eor1+1);
        for (int i : arr) {
            if((i&right)==0){
                eor2=eor2^i;
            }
        }
        //for循环结束，此时eor2要么就是a，要么就是b
        System.out.println(eor2);
        System.out.println(eor1^eor2);
    }













    public static void getTwoNumber428(int ...a){
        int eor1=0;
        int eor2=0;
        for (int i : a) {
            eor1=eor1^i;//eor1与每个数异或，最后的结果肯定是a^b，也就是两个出现奇数次的数异或的结果
        }
        //因为这两个奇数次出现的数不同，所以eor1！=0
        //我们求出这个eor1最右边第一个非0的位
        int right=eor1&(~eor1+1);
        for (int i : a) {
            if((i&right)==0){
                eor2=eor2^i;
            }
        }
        System.out.println("第一个出现奇数次的数位:"+eor2+",第二个出现奇数次的数为:"+(eor1^eor2));

    }






    public static void getTwoNumber(int...a){
        int eor1=0;
        int eor2=0;
        for (int i : a) {
            eor1=eor1^i;
        }
        //此时的eor1就是a^b
        //eor因为不想等，所以eor1！=0
        //eor1必然有一位为1
        int rightOne=eor1&(~eor1+1);//这个就是得到最右边不为1的那一位:10101010==>00000010
        for (int i : a) {
            if((i&rightOne)==0){//这个i要么就是出现奇数次的一个数和出现偶数次的数，要么就是出现奇数次的第二个数和出现偶数次的数
                //if的意思是让a数组中分为两组，要一组是只有a或者只有b，
                // 然后剩下的就是出现了偶数次的其他数；另一组同理。
                // 这个时候，偶数的异或全是0，和剩下出现奇数次的a或者b异或就只剩下了a或者b、
                eor2=eor2^i; //循环结束时的eor2要么是a,要么就是b.
            }
        }
        System.out.println("第一个出现奇数次的数位:"+eor2+",第二个出现奇数次的数为:"+(eor1^eor2));
    }
}
