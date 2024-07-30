package com.bankmanagement;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Customer> customers;

    public Bank(String name) {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        try {
            if (customer == null) {
                throw new IllegalArgumentException("Customer cannot be null");
            }
            customers.add(customer);
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to add customer" + e.getMessage());
            throw e;
        }
    }

    public Customer findCustomerByID(String queryID) {
        for (Customer customer : customers) {
            if (queryID.equals(customer.getCustomerID())) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> returnCustomerList() {
        return customers.isEmpty() ? null : customers;
    }

    public void deposit(double amount, String customerID, int accountNumber) {
        Customer customer = findCustomerByID(customerID);
        if (customer != null) {
            for (Account account : customer.getAccountList()) {
                if (account.getAccountNumber() == accountNumber) {
                    try {
                        account.deposit(amount);
                        System.out.println(amount + " added to your account");
                        System.out.println(account.getBalance() + " in your account");
                    } catch (ValueException e) {
                        System.err.println("Invalid deposit " + e);
                    }
                    return;
                }
            }
        }
        System.out.println("Invalid details.");
    }

    public void withdraw(double amount, String customerID, int accountNumber) {
        Customer customer = findCustomerByID(customerID);
        if (customer != null) {
            for (Account account : customer.getAccountList()) {
                if (account.getAccountNumber() == accountNumber) {
                    try {
                        account.withdraw(amount);
                        System.out.println(amount + " withdrawn from your account");
                        System.out.println(account.getBalance() + " left in your account");
                    } catch (ValueException e) {
                        System.err.println("Invalid withdrawal" + e);
                    }
                    return;
                }
            }
        }
        System.out.println("Invalid transaction.");
    }
}
