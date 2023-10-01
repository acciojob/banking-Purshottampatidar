package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;


    public BankAccount(String name, double balance, double minBalance) {
                     this.name=name;
                     this.balance=balance;
                     this.minBalance=minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if(sum>9*digits){
            throw new Exception("Account Number can not be generated");
        }

        int quotient=sum/9;
        int remainder=sum%9;

        String AccNo="";
        while(quotient-->0){
            AccNo+="9";
        }

        if(remainder!=0){
            AccNo+=remainder;
        }
        int remainingDigits=digits-AccNo.length();

        while(remainingDigits-->0){
            AccNo+="0";
        }

        return AccNo;
    }

    public void deposit(double amount) {
        //add amount to balance
        balance+=amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
          if((balance-amount)>=minBalance){
              balance-=amount;
          }
          else{
              throw new Exception("Insufficient Balance");
          }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }
}