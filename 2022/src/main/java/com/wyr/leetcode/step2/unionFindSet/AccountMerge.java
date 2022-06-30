package com.wyr.leetcode.step2.unionFindSet;

import java.util.*;
import java.util.stream.Collectors;

public class AccountMerge {
    /**
     * 账户合并
     *
     * https://leetcode.cn/problems/accounts-merge/
     */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Integer N=accounts.size();
        if(N==1){
            return accounts;
        }
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(i);
        }
        UnionFindSet<Integer> ufs=new UnionFindSet<>(list);
        for(int i=0;i<accounts.size();i++){
            for(int j=i+1;j<accounts.size();j++){
                for(int k=1;k<accounts.get(j).size();k++){ //list.get(0)放的是名字
                    if(accounts.get(i).contains(accounts.get(j).get(k))){
                        //是同一个集合
                        if(ufs.isSameSet(i,j)){
                            break; //就不需要在去看剩下的邮箱名了
                        }else{ //不是同一个集合
                            ufs.union(i,j);
                            break; //就不需要在去看剩下的邮箱名了
                        }
                    }
                }

            }
        }

        List<List<String>> res=new ArrayList<>();
        for(Element<Integer> fatherElement: ufs.sizeMap.keySet()){//拿出所有的父代表元素
            List<String> temp=new ArrayList<>();
            temp.add(accounts.get(fatherElement.value).get(0));//下标为0的位置放的是姓名
            Set<String> emails=new HashSet<>(); //存放邮箱的set集合为了去重
            //注意要把父代表元素的emails也要加上
            for(int i=1;i<accounts.get(fatherElement.value).size();i++){
                emails.add(accounts.get(fatherElement.value).get(i));
            }
            for(Map.Entry<Element<Integer>,Element<Integer>> entry:  ufs.fatherMap.entrySet()){
                Element<Integer> child=entry.getKey();
                //大部分元素都扁平化了，可能存在极少的没有扁平化，所以这里还是找到最顶部的代表元素为好
                Element<Integer> father=ufs.findHead(child);
                if(father==fatherElement){
                    for(int i=1;i<accounts.get(child.value).size();i++){
                        emails.add(accounts.get(child.value).get(i));
                    }
                }
            }
            List<String> sortEmails=new ArrayList<>();
            for(String s: emails){
                sortEmails.add(s);
            }
            Collections.sort(sortEmails,(o1, o2)->o1.compareTo(o2)); //排序
            for(String s : sortEmails){
                temp.add(s);
            }
            res.add(temp);
        }
        return res;
    }


    //MAP<List<String>,Integer> 会将保存相同String的list看作是同一个！,我们封装一层
    public static class HelpClass{
        public List<String> value;

        public HelpClass(List<String> list){
            this.value=list;
        }
    }

    /**
     * 元素封装类
     */
    public static class Element<V>{
        public V value;
        public Element(V value){
            this.value= value;
        }
    }

    /**
     * 并查集结构
     */
    public static class UnionFindSet<V>{
        public HashMap<V,Element<V>> elementMap;
        public HashMap<Element<V>,Element<V>> fatherMap;
        public HashMap<Element<V>,Integer> sizeMap;

        public UnionFindSet(List<V> list){
            elementMap=new HashMap<>();
            fatherMap=new HashMap<>();
            sizeMap=new HashMap<>();
            for(V value: list){
                Element<V> element=new Element<>(value);
                elementMap.put(value,element);
                fatherMap.put(element,element);
                sizeMap.put(element,1);
            }
        }

        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> stack=new Stack<>();
            while(element!=fatherMap.get(element)){
                stack.push(element);
                element=fatherMap.get(element);
            }
            //扁平化
            while(!stack.isEmpty()){
                fatherMap.put(stack.pop(),element);
            }
            return element;
        }

        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                return findHead(elementMap.get(a))==findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b){
            if(elementMap.containsKey(a)&&elementMap.containsKey(b)){
                Element<V> headA=findHead(elementMap.get(a));
                Element<V> headB=findHead(elementMap.get(b));
                if(headA!=headB){
                    Element<V> big=sizeMap.get(headA)>sizeMap.get(headB)?headA:headB;
                    Element<V> small=big==headA?headB:headA;
                    //将size较小的代表元素挂到size较大的代表元素下
                    fatherMap.put(small,big);
                    //更新sizeMap
                    sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
                    sizeMap.remove(small);
                }
            }
        }
    }





    public static List<List<String>> accountsMerge1(List<List<String>> accounts) {
        // 将list变为account:name的map
        Map<String,String> accountMap = new HashMap<>();
        for (int j = 0; j < accounts.size(); j++){
            List<String> account = accounts.get(j);
            String name = account.get(0)+"|"+j;
        // System.out.println(name + "=" + account.get(0));

            List<String> names = new ArrayList<>();
            for(int i = 1; i < account.size(); i++){
                if(accountMap.containsKey(account.get(i))) {
                    names.add(accountMap.get(account.get(i)));
                }
            }
            for (Map.Entry<String,String> entry: accountMap.entrySet()) {
                if(names.contains(entry.getValue())){
                    accountMap.replace(entry.getKey(),name);
                }
            }

            for(int i = 1; i < account.size(); i++){
                if(!accountMap.containsKey(account.get(i))) { //去重
                    accountMap.put(account.get(i),name);
                }
            }
        }

        // 转为maplist
        Map<String,List<String>> newAccountMap = new HashMap<>();
        for (Map.Entry<String,String> entry: accountMap.entrySet()) {
            String key = entry.getValue();
            List<String> values;
            if(!newAccountMap.containsKey(key)){
                values = new ArrayList<>();
            }else {
                values = newAccountMap.get(key);
            }
            values.add(entry.getKey());
            newAccountMap.put(key,values);
        }
        //System.out.print(newAccountMap.toString());
        List<List<String>> newAccounts = new ArrayList<>();
        // 转为ListList
        for (Map.Entry<String,List<String>> entry: newAccountMap.entrySet()) {
            List<String> accountList = new ArrayList<>();
            accountList.add(entry.getKey().split("\\|")[0]);
            accountList.addAll(entry.getValue().stream().sorted().collect(Collectors.toList()));
            newAccounts.add(accountList);
        }
        return newAccounts;

    }



    public static void main(String[] args) {
        List<List<String>> accounts=new ArrayList<>();
        List temp1=new ArrayList();
        temp1.add("David");
        temp1.add("David0@m.co");
        temp1.add("David0@m.co");
        temp1.add("David1@m.co");
        temp1.add("David2@m.co");
        temp1.add("David3@m.co");
        temp1.add("David4@m.co");
        temp1.add("David5@m.co");
        temp1.add("David6@m.co");
        temp1.add("David7@m.co");
        List temp2=new ArrayList();
        temp2.add("David");
        temp2.add("David0@m.co");
        temp2.add("David0@m.co");
        temp2.add("David1@m.co");
        temp2.add("David2@m.co");
        temp2.add("David3@m.co");
        temp2.add("David4@m.co");
        temp2.add("David5@m.co");
        temp2.add("David6@m.co");
        temp2.add("David7@m.co");
        List temp3=new ArrayList();
        temp3.add("David");
        temp3.add("David2@m.co");
        temp3.add("David18@m.co");
        temp3.add("David19@m.co");
        temp3.add("David20@m.co");
        temp3.add("David21@m.co");
        temp3.add("David22@m.co");
        temp3.add("David23@m.co");
        temp3.add("David24@m.co");
        temp3.add("David25@m.co");

        List temp4=new ArrayList();
        temp4.add("David");
        temp4.add("David3@m.co");
        temp4.add("David27@m.co");
        temp4.add("David28@m.co");
        temp4.add("David29@m.co");
        temp4.add("David30@m.co");
        temp4.add("David31@m.co");
        temp4.add("David32@m.co");
        temp4.add("David33@m.co");
        temp4.add("David34@m.co");
        List temp5=new ArrayList();
        temp5.add("David");
        temp5.add("David1@m.co");
        temp5.add("David9@m.co");
        temp5.add("David10@m.co");
        temp5.add("David11@m.co");
        temp5.add("David12@m.co");
        temp5.add("David13@m.co");
        temp5.add("David14@m.co");
        temp5.add("David15@m.co");
        temp5.add("David16@m.co");


        accounts.add(temp1);
        accounts.add(temp2);
        accounts.add(temp3);
        accounts.add(temp4);
        accounts.add(temp5);

        List<List<String>> lists = accountsMerge(accounts);
        System.out.println("aaa");
    }
}
