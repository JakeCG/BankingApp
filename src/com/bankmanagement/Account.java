package com.bankmanagement;

public class Account {

    private double balance;
    private final int accountNumber;

    public Account(double balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double value) throws ValueException {
        if (value > 0) {
            balance += value;
        } else {
            throw new ValueException("The value entered is 0 or less.");
        }
    }

    public void withdraw(double value) throws ValueException{
        if (value > 0  && value <= balance) {
            balance -= value;
        } else {
            throw new ValueException
                    ("The value entered would not alter the account balance, or reduce it below 0.");
        }
    }
}
