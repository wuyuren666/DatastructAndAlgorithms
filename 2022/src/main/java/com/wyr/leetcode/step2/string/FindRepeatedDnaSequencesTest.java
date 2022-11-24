package com.wyr.leetcode.step2.string;

import java.util.*;

public class FindRepeatedDnaSequencesTest {
    /**
     * DNA序列由一系列核苷酸组成，缩写为'A','C','G'和'T'.。
     *
     * 例如，"ACGAATTCCG"是一个 DNA序列 。
     * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
     *
     * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的长度为10的序列(子字符串)。你可以按 任意顺序 返回答案。
     *
     * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * 输出：["AAAAACCCCC","CCCCCAAAAA"]
     *
     * 输入：s = "AAAAAAAAAAAAA"
     * 输出：["AAAAAAAAAA"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/repeated-dna-sequences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length()<=10){
            return new ArrayList<>();
        }
        List<String> list=new ArrayList<>();
        Set<String> set=new HashSet<>(); //去重
        Set<String> map=new HashSet<>(); //记录出现过的string
        for(int i=0;i<=s.length()-10;i++){
            String tempStr=s.substring(i,i+10);
            if(!map.contains(tempStr)){ //不存在map中就记录
                map.add(tempStr);
            }else{//已经存在于map中
                set.add(tempStr);
            }
        }
        return new ArrayList<>(set);
    }
}
