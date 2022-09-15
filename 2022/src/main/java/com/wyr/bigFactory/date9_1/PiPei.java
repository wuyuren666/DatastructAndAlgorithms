package com.wyr.bigFactory.date9_1;

import java.util.Scanner;

public class PiPei {
    /**
     * 小美在摆弄她的字符串。最近小团送了小美一个特殊字符'*'，这个字符
     * 可以和其他所有的字符匹配，除了这个字符外，其他字符只能自己和自己
     * 匹配。小美先前有一个字符串S，长度n，现在她又新组合了一个可有特殊字符'*'
     * 的字符串s。长度为m。小美想知道有多少个位置i，是的S[i+j]对于1<=j<=m均
     * 能匹配上？其中X[y]代表字符串X中第y位的字符
     *
     * 输入：
     * 7  3
     * abcaacc
     * a*c
     *
     * 输出：
     * 3
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        scanner.nextLine();
        String S= scanner.nextLine();
        char[] charS = S.toCharArray();
        String s=scanner.nextLine();
        char[] chars = s.toCharArray();
        int left=0;
        int right=m-1;
        int ans=0;
        while(right<charS.length){
            int i=left;
            int j=0;
            for(;i<left+m;i++,j++){
                if(chars[j]=='*'){
                    continue;
                }
                if(charS[i]!=chars[j]){
                    break;
                }
            }
            if(i==left+m){
                ans++;
            }
            left++;
            right++;
        }
        System.out.println(ans);
        scanner.close();
    }
}
