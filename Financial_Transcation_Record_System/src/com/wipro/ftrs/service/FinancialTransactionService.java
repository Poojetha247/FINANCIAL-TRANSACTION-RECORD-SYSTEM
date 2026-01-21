package com.wipro.ftrs.service;

import java.util.ArrayList;
import java.util.UUID;
import com.wipro.ftrs.entity.*;
import com.wipro.ftrs.util.*;

public class FinancialTransactionService {

    private ArrayList<Account> accounts;
    private ArrayList<TransactionRecord> transactions;

    public FinancialTransactionService(ArrayList<Account> accounts,
                                       ArrayList<TransactionRecord> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    public Account findAccount(String accountId) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountId().equals(accountId)) {
                return acc;
            }
        }
        throw new AccountNotFoundException("Account not found: " + accountId);
    }

    public void deposit(String accountId, double amount, String remarks)
            throws AccountNotFoundException, InvalidTransactionException {

        if (amount <= 0) {
            throw new InvalidTransactionException("Invalid deposit amount");
        }

        Account acc = findAccount(accountId);
        acc.setBalance(acc.getBalance() + amount);

        transactions.add(new TransactionRecord(
                UUID.randomUUID().toString(),
                accountId,
                "DEPOSIT",
                amount,
                java.time.LocalDate.now().toString(),
                remarks
        ));
    }

    public void withdraw(String accountId, double amount, String remarks)
            throws AccountNotFoundException, InvalidTransactionException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidTransactionException("Invalid withdrawal amount");
        }

        Account acc = findAccount(accountId);

        if (acc.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);

        transactions.add(new TransactionRecord(
                UUID.randomUUID().toString(),
                accountId,
                "WITHDRAW",
                amount,
                java.time.LocalDate.now().toString(),
                remarks
        ));
    }

    public void transfer(String fromId, String toId, double amount, String remarks)
            throws AccountNotFoundException, InvalidTransactionException, InsufficientBalanceException {

        withdraw(fromId, amount, "Transfer to " + toId);
        deposit(toId, amount, "Transfer from " + fromId);
    }

    public ArrayList<TransactionRecord> getTransactionHistory(String accountId)
            throws AccountNotFoundException {

        findAccount(accountId); // validation

        ArrayList<TransactionRecord> result = new ArrayList<>();
        for (TransactionRecord tr : transactions) {
            if (tr.getAccountId().equals(accountId)) {
                result.add(tr);
            }
        }
        return result;
    }

    public double calculateBalance(String accountId)
            throws AccountNotFoundException {
        return findAccount(accountId).getBalance();
    }

    public String generateAccountSummary(String accountId)
            throws AccountNotFoundException {

        Account acc = findAccount(accountId);

        return "Account ID: " + acc.getAccountId() +
               "\nHolder: " + acc.getAccountHolderName() +
               "\nBalance: " + acc.getBalance();
    }
}
