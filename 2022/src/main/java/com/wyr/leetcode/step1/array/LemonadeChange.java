package com.wyr.leetcode.step1.array;

public class LemonadeChange {

    /**
     * 在柠檬水摊上，每一杯柠檬水的售价为5美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     *
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。
     * 你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     *
     * 注意，一开始你手头没有任何零钱。
     *
     * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回true，否则返回 false。
     *
     * 输入：bills = [5,5,5,10,20]
     * 输出：true
     * 解释：
     * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
     * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
     * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
     * 由于所有客户都得到了正确的找零，所以我们输出 true。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/lemonade-change
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean lemonadeChange(int[] bills) {
        if(bills[0]!=5)
            return false;
        int [] map=new int[2];//map[0]代表5元张数，map[1]代表1元张数
        for(int i=0;i<bills.length;i++){
            if(bills[i]==5){
                map[0]++;
            }else if(bills[i]==10){
                map[1]++;
                if(map[0]>0){
                    map[0]--;
                }else{
                    return false;
                }
            }else{
                if(map[0]>0&&map[1]>0){//至少有一张5元和10元
                    map[0]--;
                    map[1]--;
                }else if(map[0]>=3){ //至少有三张5元
                    map[0]-=3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
}
