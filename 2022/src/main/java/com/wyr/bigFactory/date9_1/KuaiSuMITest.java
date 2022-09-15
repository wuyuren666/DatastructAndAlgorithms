package com.wyr.bigFactory.date9_1;

import java.math.BigInteger;

public class KuaiSuMITest {

    public static void main(String[] args) {
        System.out.println(Decrypt(4296,1601,4757));
    }

    //返回值是：(encryptedNumber^decryption)%number
    public static int Decrypt (int encryptedNumber, int decryption, int number) {
        BigInteger ans=null;
        if((decryption&1)==0){//幂是偶数
            int mi=decryption;
            BigInteger di = BigInteger.valueOf(encryptedNumber);
            while((mi&1)==0){//为了让幂尽量小
                mi=mi/2;
                di=di.multiply(di);
            }
            ans=di.pow(mi);
        }else{//幂是奇数
            int mi=decryption-1;
            BigInteger di = BigInteger.valueOf(encryptedNumber);
            while((mi&1)==0){//为了让幂尽量小
                mi=mi/2;
                di=di.multiply(di);
            }
            ans=BigInteger.valueOf(encryptedNumber).multiply(di.pow(mi));
        }
        return ans.mod(BigInteger.valueOf(number)).intValue();
    }
}
