package com.wyr.leetcode.step2.array;

public class SheArrayTest {

    /**
     * 6 7 9
     * 2 5 8
     * 1 3 4
     *
     * 斜过来看
     *                 6
     *             2     5     7
     * 会发现：1  、   3  、 4  、 8 、 9
     *
     * 一共有多少个这种斜过来的斜列，其实发现和n有关，斜列的个数==2*n-1
     *
     * 那么我们可以考虑每次一个斜列，一个斜列这样的去填
     */
    public static void process(int [][] a){

        int curXieLieLength=1;//当前斜列所的长度（所需要填的数字的个数），从左下角开始的斜列的长度为1
        int curNum=1;//curNum当前需要填的数
        boolean flag=true;
        for(;curXieLieLength<=a.length;curXieLieLength++){ //一个一个的斜列去填，先填左下角，(带着对角线)
            if(flag){
                for(int col=curXieLieLength-1,row=a.length-1;col>=0;){
                    a[row--][col--]=curNum++;
                }
                flag=false;
            } else{
                for(int col=0,row=a.length-curXieLieLength;row<a.length;){
                    a[row++][col++]=curNum++;
                }
                flag=true;
            }
        }

        //再填右上角不带对角线
        for (curXieLieLength=curXieLieLength-2; curXieLieLength>=1; curXieLieLength--) {
            if(!flag){
                for(int col= a.length-curXieLieLength,row=0;col<a.length;){
                    a[row++][col++]=curNum++;
                }
                flag=false;
            } else{
                for(int col=a.length-1,row=curXieLieLength-1;row>=0;){
                    a[row--][col--]=curNum++;
                }
                flag=true;
            }
        }
    }

    public static void main(String[] args) {
        int[][] a=new int[3][3];
        process(a);
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
}
