package com.example.ia.accounting;

import java.time.LocalDate;
import java.time.LocalTime;

// This class represents a ledger transaction
//   If a credit, amount is positive
//   If a debit, amount is negative

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String payee;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String payee, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.payee = payee;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getPayee() {
        return payee;
    }

    public double getAmount() {
        return amount;
    }

}


