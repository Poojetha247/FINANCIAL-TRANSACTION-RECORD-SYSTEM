package com.wipro.ftrs.entity;

public class TransactionRecord {

    private String transactionId;
    private String accountId;
    private String type;
    private double amount;
    private String date;
    private String remarks;

    public TransactionRecord(String transactionId, String accountId,
                             String type, double amount,
                             String date, String remarks) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.remarks = remarks;
    }

    // 🔴 THESE WERE MISSING
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountId() {
        return accountId;
    }
}
