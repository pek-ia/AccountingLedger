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

}
