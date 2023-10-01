package com.driver;

import java.lang.*;
import java.util.*;
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
         super(name,balance,5000);
         this.tradeLicenseId=tradeLicenseId;
         if(balance<5000){
             throw new Exception("Insufficient Balance");
         }

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if( !isValidLicenseId(tradeLicenseId) ){
            String rearrangeString=rearrange(tradeLicenseId);
            if(rearrangeString.equals("")){
                throw new Exception("Valid License can not be generated");
            }
            else{
                this.tradeLicenseId=rearrangeString;
            }
        }
        else{
            this.tradeLicenseId=tradeLicenseId;
        }
    }

    public boolean isValidLicenseId(String tradeLicenseId){
        for(int i=0;i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)) {
                return false;
            }
        }
        return true;
    }

    public class Pair{
        int val;
        char ch;
        Pair(int val,char ch){
            this.val=val;
            this.ch=ch;
        }
    }
    public String rearrange(String tradeLicenseId){
        int[] freq=new int[26];
        for(int i=0;i<tradeLicenseId.length();i++){
            freq[tradeLicenseId.charAt(i)-'A']++;
        }

        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
            return a.val-b.val;
        });

        for(char c='A';c<='Z';c++){
            int value=c-'A';
            if(freq[value]>0){
                pq.add(new Pair(freq[value],c));
            }
        }

        String str="";
        Pair prev=new Pair(-1,'#');

        while(pq.size()>0){
            Pair curr= pq.remove();
            str=str+curr.ch;

            if(prev.val>0){
                pq.add(prev);
            }
            curr.val--;
            prev=curr;
        }

        if(tradeLicenseId.length()!=str.length()){
            return "";
        }
        return str;
    }

}
