package com.bankmanagement;

import java.util.List;

public class Customer {
    private final String customerID;
    private final String name;
    private final List<Account> accountList;

    public Customer(String customerID, String name, List<Account> accountList) {
        this.customerID = customerID;
        this.name = name;
        this.accountList = accountList;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }
}
