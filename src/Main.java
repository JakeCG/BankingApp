import com.bankmanagement.Account;
import com.bankmanagement.Bank;
import com.bankmanagement.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args ) {
        Bank newBank = new Bank("MyBank");
        String initialOutput = """
                Enter an integer value from the list below
                 1 - Add customer
                 2 - Add account
                 3 - Deposit
                 4 - Withdraw
                 5 - View customers
                 6 - Exit
                 Enter your choice:\s""";

        System.out.println(initialOutput);

        try (Scanner scan = new Scanner(System.in)) {
            int choice = 0;
            while (choice != 6) {
                System.out.println(initialOutput);
                choice = getValidInt(scan);
                switch (choice) {
                    case 1 -> addCustomer(newBank, scan);
                    case 2 -> addAccount(newBank, scan);
                    case 3 -> deposit(newBank, scan);
                    case 4-> withdraw(newBank, scan);
                    case 5 -> returnCustomerList(newBank);
                    default -> System.out.println("Please enter a valid choice.");

                }
            }
            System.out.println("Exited gracefully.");
        }
    }

    private static int getValidInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Please enter a valid integer: ");
            scan.next();
        }
        return scan.nextInt();
    }

    private static double getValidDouble(Scanner scan) {
        while(!scan.hasNextDouble()) {
            System.out.println("Please enter a valid double: ");
            scan.next();
        }
        return scan.nextDouble();
    }

    private static void addCustomer(Bank bank, Scanner scan) {
        System.out.println("Enter an customer ID: ");
        String customerID = scan.next();
        System.out.println("Enter a name: ");
        String name = scan.next();
        Customer newCustomer = new Customer(customerID, name, new ArrayList<>());
        bank.addCustomer(newCustomer);
    }

    private static void addAccount(Bank bank, Scanner scan) {
        System.out.println("Enter your CustomerID: ");
        String customerID = scan.next();
        Customer customerIDValid = bank.findCustomerByID(customerID);
        if (customerIDValid == null) {
            System.out.println("Invalid customer ID");
        } else {
            System.out.println("Enter an integer account Number: ");
            int newAccountNumber = getValidInt(scan);
            for (Account account : customerIDValid.getAccountList()) {
                if (account.getAccountNumber() == newAccountNumber) {
                    System.out.println("Please enter a unique account.");
                    break;
                }
            }
            Account newAccount = new Account(newAccountNumber, 0);
            customerIDValid.addAccount(newAccount);
            System.out.println("Account added successfully");
        }
    }

    private static void deposit(Bank bank, Scanner scan) {
        System.out.println("Enter your CustomerID: ");
        String customerID = scan.next();
        System.out.println("Enter your account number: ");
        int accountNumber = getValidInt(scan);
        System.out.println("Enter the amount you wish to deposit: ");
        double amount =  getValidDouble(scan);
        bank.deposit(amount, customerID, accountNumber);
    }

    private static void withdraw(Bank bank, Scanner scan) {
        System.out.println("Enter your CustomerID: ");
        String customerID = scan.next();
        System.out.println("Enter your account number: ");
        int accountNumber = getValidInt(scan);
        System.out.println("Enter the amount you wish to withdraw: ");
        double amount = getValidDouble(scan);
        bank.withdraw(amount, customerID, accountNumber);
    }

    private static void returnCustomerList(Bank bank) {
        List<Customer> customers = bank.returnCustomerList();
        StringBuilder output = new StringBuilder();
        if (customers == null || customers.isEmpty()) {
           output.append("No customers found.");
        } else {
            output.append("This is the list of customers: ");
            for (Customer customer : customers) {
                output.append("\n ").append(customer.getName());
            }
            System.out.println(output);
        }
        System.out.println(output);
    }
}
