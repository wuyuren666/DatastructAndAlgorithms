package com.wyr.dp.zuoshen.p19_20;


import java.io.File;
import java.net.URL;

/**
 * 规定1和A对应，2和B对应，3和C对应...26和Z对应
 * 那么一个数字字符串比如"111"就可以转化成为
 * "AAA"，"KA"，"AK"
 * 给定一个只有数字字符串组成的字符串str。返回有多少种转化结果
 */
public class Code1 {

    //8.1练习
    public static int process81(char[] chars,int index){
        //baseCase
        if(index==chars.length){
            return 1;
        }
        //单转0字符，肯定转不了
        if(chars[index]=='0'){
            return 0;
        }

        //当前字符，我自己单独转
        int p1=process81(chars,index+1);

        int p2=0;
        //当前字符我不单独转，我和下一个字符一起转
        //下一个字符存在且当前字符和下一个字符组合成的数字[10,26]
        if(index+1<chars.length&& (chars[index]-'0')*10+(chars[index]-'0')>=10&&(chars[index]-'0')*10+(chars[index]-'0')<=26){
            p2+=process81(chars,index+2);
        }
        return p1+p2;
    }







    //5.18练习
    public static int process518(char[] chars,int index){
        //如果当前转化的字符来到最后一个字符后面，代表我已经找到一种转化策略了
        if(index==chars.length) {
            return 1;
        }
        //如果你让你当前转化的字符独自来面对'0'，那么肯定转化的有问题
        if(chars[index]=='0')
            return 0;

        //独自面对的不是0字符，我可以选择单转
        int p1=process518(chars,index+1);
        //我也可以选择和后面一个字符去组合转，但是要注意在1~26，还要注意是否存在下一个字符
        int p2=0;
        if(index+1<chars.length&&(chars[index]-'0')*10+(chars[index+1]-'1')<=26){
            p2=process518(chars,index+2);
        }
        return p1+p2;
    }





    public static int solution(String str){
        if(str==null||str.length()==0)
            return 0;
        char[] s = str.toCharArray();
        return process(s,0); //我从下标为0的位置开始尝试
    }

    //s[i]...s[s.length-1]去转化，返回有多少种转化方法
    public static int process(char [] s, int index){
        //如果，能走到最后一个字符数字的后面，比如['1','0','1']最后一个字符数字的后面，说明我已经找到了一种组合策略
        if(index==s.length)
            return 1;
        //当前选择来到'0'字符，之前的决定有问题
        if(s[index]=='0')
            return 0;

        //当前数字字符不是'0'，他可以自己转成一个字母
        int p1=process(s,index+1);

        int p2=0;
        //当前不是'0'的数字字符，和下一个数字字符转成一个字母，但是要在1~27
        //首先，应该看一下，是否有下一个数字字符
        if(index+1<s.length&&(s[index]-'0')*10+(s[index+1]-'0')<27){
            p2=process81(s,index+2);
        }

        return p1+p2;
    }





    public static int solution11_21(String str){
        char[] chars = str.toCharArray();
        return process11_21(chars, 0);
    }




    public static int process11_21(char[] chars, int index){
        if(index==chars.length){
            return 1;
        }

        //如果单独碰到'0'
        if(chars[index]=='0'){
            return 0;
        }

        //在当前字符选择单转
        int p1=process(chars,index+1);
        //选择和下一个字符一起转
        int p2=0;
        //有下一个字符，且当前数字和下一个数字组合起来的范围在[1,26]
        if(index+1<=chars.length-1&&(chars[index]-'0')*10+(chars[index]-'0')>=1&&(chars[index]-'0')*10+(chars[index]-'0')<=26){
            p2=process11_21(chars,index+2);
        }
        return p1+p2;
    }



    public static void main(String[] args) {
        System.out.println(solution("234234324"));//8
        System.out.println(solution11_21("234234324"));//8
    }

}
