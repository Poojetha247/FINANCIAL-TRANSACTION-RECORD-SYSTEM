package com.wipro.ftrs.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.wipro.ftrs.entity.*;
import com.wipro.ftrs.service.FinancialTransactionService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<TransactionRecord> transactions = new ArrayList<>();

        FinancialTransactionService service =
                new FinancialTransactionService(accounts, transactions);

        int choice;

        do {
            System.out.println("\n===== Financial Transaction Record System =====");
            System.out.println("1. Add Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Account Summary");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter Account ID: ");
                        String accId = sc.next();

                        System.out.print("Enter Holder Name: ");
                        sc.nextLine(); // consume newline
                        String name = sc.nextLine();

                        System.out.print("Enter Initial Balance: ");
                        double bal = sc.nextDouble();

                        service.addAccount(new Account(accId, name, bal));
                        System.out.println("Account created successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Account ID: ");
                        accId = sc.next();

                        System.out.print("Enter Amount: ");
                        double depAmt = sc.nextDouble();

                        sc.nextLine();
                        System.out.print("Enter Remarks: ");
                        String depRemarks = sc.nextLine();

                        service.deposit(accId, depAmt, depRemarks);
                        System.out.println("Deposit successful.");
                        break;

                    case 3:
                        System.out.print("Enter Account ID: ");
                        accId = sc.next();

                        System.out.print("Enter Amount: ");
                        double withAmt = sc.nextDouble();

                        sc.nextLine();
                        System.out.print("Enter Remarks: ");
                        String withRemarks = sc.nextLine();

                        service.withdraw(accId, withAmt, withRemarks);
                        System.out.println("Withdrawal successful.");
                        break;

                    case 4:
                        System.out.print("Enter From Account ID: ");
                        String fromId = sc.next();

                        System.out.print("Enter To Account ID: ");
                        String toId = sc.next();

                        System.out.print("Enter Amount: ");
                        double transAmt = sc.nextDouble();

                        sc.nextLine();
                        System.out.print("Enter Remarks: ");
                        String transRemarks = sc.nextLine();

                        service.transfer(fromId, toId, transAmt, transRemarks);
                        System.out.println("Transfer successful.");
                        break;

                    case 5:
                        System.out.print("Enter Account ID: ");
                        accId = sc.next();

                        System.out.println("--- Transaction History ---");
                        for (TransactionRecord tr :
                                service.getTransactionHistory(accId)) {
                            System.out.println(
                                tr.getType() + " : " + tr.getAmount()
                            );
                        }
                        break;

                    case 6:
                        System.out.print("Enter Account ID: ");
                        accId = sc.next();

                        System.out.println("--- Account Summary ---");
                        System.out.println(
                                service.generateAccountSummary(accId)
                        );
                        break;

                    case 0:
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }
}

