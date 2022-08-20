package com.wyr.zuoshen.zuoshen1.p5;

/**
 * 前缀树
 */
public class TrieTree {
    //前缀树的节点
    public static class TrieNode{
        public int pass;
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new TrieNode[26];
        }
    }

    public static class Trie{
        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }


        //往前缀树中插入
        public void insert(String word){
            if(word==null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node=root; //辅助变量，首先指向根节点
            node.pass++; //根节点的pass首先++
            int index=0;
            for(int i=0;i<chs.length;i++){ //遍历字符数组
                index=chs[i]-'a'; //由字符确定走哪条路，即找到nexts这个路径数组的下标
                if(node.nexts[index]==null){ //这条路之前没有人走过
                    node.nexts[index]=new TrieNode();
                }
                node=node.nexts[index];//移动辅助节点
                node.pass++; //经过的路程++
            }
            node.end++; //最后结尾的路的end++
        }


        //word这个单词之前加入过几次
        public int search(String word){
            if(word==null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node=root;
            int index=0;
            for(int i=0;i<chs.length;i++){
                index=chs[i]-'0';
                if(node.nexts[index]==null){
                    return 0;
                }
                node=node.nexts[index];
            }
            return node.end;
        }


        //删除某一个单词
        public void delete(String word){
            if(search(word)!=0){//存在这个单词，才可以删除
                char[] chs = word.toCharArray();
                TrieNode node=root;//辅助变量
                node.pass--;
                int index=0;
                for(int i=0;i<chs.length;i++){
                    index=chs[i]-'a';
                    //这个if就相当于，在这个前缀树上这个单词只出现一次
                    if(--node.nexts[index].pass==0){ //当前节点的下一个节点的pass值--之后为0，直接将下级节点标空，return
                        node.nexts[index]=null;
                        return;
                    }
                    node=node.nexts[index];
                }
                //如果出现多次，将最后一个节点的end--
                node.end--;
            }
        }


        //所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre){
            if(pre==null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node=root;
            int index=0;
            for(int i=0;i<chs.length;i++){
                index=chs[i]-'a';
                if(node.nexts[index]==null){
                    return 0;
                }
                node=node.nexts[index];
            }
            return node.pass;
        }

    }
}
