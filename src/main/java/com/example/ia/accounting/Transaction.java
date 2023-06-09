package com.example.ia.accounting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

// This class represents a ledger transaction
//   If a credit, amount is positive
//   If a debit, amount is negative

public class Transaction implements Comparable<Transaction> {
    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final String payee;
    private final double amount;

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

    public LocalDateTime getTimestamp(){
        return LocalDateTime.of(date, time);
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

    @Override
    public int compareTo(Transaction o) {
        // Descending sort by comparing LocalDateTime
        return o.getTimestamp().compareTo(this.getTimestamp());
    }
}


