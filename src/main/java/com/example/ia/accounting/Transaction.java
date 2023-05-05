package com.example.ia.accounting;

import java.time.LocalDate;
import java.time.LocalTime;

// This class represents a ledger transaction
//   If a credit, amount is positive
//   If a debit, amount is negative

public class Transaction {
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

    public static Transaction fromFileText(String s) {
        String[] fields = s.split("\\|");
        LocalDate date = LocalDate.parse(fields[0]);
        LocalTime time = LocalTime.parse(fields[1]);
        String description = fields[2];
        String payee = fields[3];
        double amount = Double.parseDouble(fields[4]);

        return new Transaction(date, time, description, payee, amount);
    }

    public String toFileText(){
        return String.format("%s|%s|%s|%s|%8.2f", date, time, description, payee, amount);
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


