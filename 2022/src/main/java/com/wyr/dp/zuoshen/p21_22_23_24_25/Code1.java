package com.wyr.dp.zuoshen.p21_22_23_24_25;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文字母
 * arr每一个字符串，代表一种贴纸（每一种贴纸都无穷张），你可以把单个字符串剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这一个任务
 *
 * 例子：str="babac"，arr={"ba","c","abcd"}
 * 可以"ba"+"ba"+"c"需要三张
 * 也可以"abcd"+"abcd"只需要两张即可
 * 还可以"abcd"+"ba"也只需要两张即可
 * 所以最少只需要2张贴纸
 *
 * 就是至少需要多少张贴纸，可以将str种的字符包含全！
 */
public class Code1 {
    //target:目标字符串
    //arr：贴纸数组
    public static int theLeastCounts1(String target, String[] arr){
        //假如傻缓存
        Map<String,Integer> map=new HashMap<>();
        int process = process1(target, arr,map);
        if(process==Integer.MAX_VALUE)
            return -1;
        return process;
    }

    /**
     *
     * @param target 目标结果
     * @param arr 贴纸数组
     * @return 最少需要的贴纸数目
     */
    public static int process1(String target, String[] arr, Map<String,Integer> map) {
        //缓存中有：直接拿
        if(map.containsKey(target)){
            return map.get(target);
        }
        //baseCase
        if (target.length() == 0) {
            return 0;
        }
        //min为系统最小值
        int min = Integer.MAX_VALUE;
        //我们从每一种贴纸进行尝试
        for (String str : arr) {
            //剪枝，我们只挑选贴纸中包含目标字符串的第一个字符的贴纸
            if (str.indexOf(target.charAt(0)) != -1) {
                //得到第一张贴纸能够消除的剩余字符串
                String rest = getRestString(target, str);
                //如果根本没有消除，if就不成立
                if (rest.length() != target.length()) { //这个if可以去掉，因为上一步的剪枝的if满足的话，那么肯定这个if也成立
                    min = Math.min(min, process1(rest, arr,map));
                }
            }
        }
        //min往上返回如果是系统最大,返回0，不是系统最大返回min+1
        int result=min + (min == Integer.MAX_VALUE ? 0 : 1);
        //结果加入缓存
        map.put(target,result);
        return result;
    }
    /**
     * s1：目标字符串 假如为"abc"
     * s2：贴纸字符串 假如为"bc"
     *  函数的返回值：返回没能处理的字符串
     */
    public static String getRestString(String s1,String s2){
        char[] chars1 = s1.toCharArray(); //[a,b,c]
        char[] chars2 = s2.toCharArray(); //[b,c]
        StringBuilder sb=new StringBuilder();
        int [] counts=new int[26]; //词频统计表
        for(int i=0;i<chars1.length;i++){
            counts[chars1[i]-'a']++;
        }
        for(int i=0;i<chars2.length;i++){
            counts[chars2[i]-'a']--;
        }
        //现在词频统计表>0的就是剩下我们不能处理的字符
        for(int i=0;i<counts.length;i++){
            if(counts[i]<=0){
                continue;
            }else {
                for(int j=0;j<counts[i];j++){
                    sb.append((char)('a'+i));
                }
            }
        }
        return sb.toString();
    }





    /**
     * 这种方法，要求很高，建议会上面一种即可
     */
    public static int theLeastCounts2(String target,String[] arr){
        int N=arr.length;
        //关键优化（用词频表代替贴纸数组）
        int[][]counts=new int[N][26];
        for(int i=0;i<N;i++){
            char[]str=arr[i].toCharArray();
            for(char cha:str){
                counts[i][cha-'a']++;
            }
        }
        int process = process(counts, target);
        if(process==Integer.MAX_VALUE)
            return -1;
        return process;

    }

    public static int process(int[][]stickers,String t){
        if(t.length()==0)
            return 0;
        char[] target=t.toCharArray(); //目标字符串转为字符数组
        int[] tcounts=new int[26]; //目标字符串的词频统计表
        for(char cha:target){ //完善目标字符词频统计表
            tcounts[cha-'a']++;
        }
        int N=stickers.length;//二维数组的长度
        int min=Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            int[] sticker=stickers[i];
            //最关键的优化(重要的剪枝，我们只挑选贴纸中包含目标字符串的第一个字符的贴纸)
            if(sticker[target[0]-'a']>0){
                StringBuilder sb=new StringBuilder();
                //对目标字符的词表进行统计
                for(int j=0;j<26;j++){
                    if(tcounts[j]>0){ //目标字符串有这个字符
                        int nums=tcounts[j]-sticker[j]; //目标字符串中这个字符出现的次数-贴纸能帮我们消去的个数
                        for(int k=0;k<nums;k++){
                            sb.append((char)(j+'a'));
                        }
                    }
                }
                String rest=sb.toString();
                min=Math.min(min,process(stickers,rest));
            }
        }
        return min+(min==Integer.MAX_VALUE?0:1);
    }


    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        System.out.println(theLeastCounts1("travelbell", new String[]{"heavy","claim","seven","set","had","it","dead","jump","design","question","sugar","dress","any","special","ground","huge","use","busy","prove","there","lone","window","trip","also","hot","choose","tie","several","be","that","corn","after","excite","insect","cat","cook","glad","like","wont","gray","especially","level","when","cover","ocean","try","clean","property","root","wing"}
        ));
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        long t3 = System.currentTimeMillis();
        System.out.println(theLeastCounts2("travelbell", new String[]{"heavy", "claim", "seven", "set", "had", "it", "dead", "jump", "design", "question", "sugar", "dress", "any", "special", "ground", "huge", "use", "busy", "prove", "there", "lone", "window", "trip", "also", "hot", "choose", "tie", "several", "be", "that", "corn", "after", "excite", "insect", "cat", "cook", "glad", "like", "wont", "gray", "especially", "level", "when", "cover", "ocean", "try", "clean", "property", "root", "wing"}));
        long t4 = System.currentTimeMillis();
        System.out.println(t4-t3);
    }

}
