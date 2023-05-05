package com.example.ia.accounting;

import java.util.ArrayList;


// This class represents a Ledger
// A ledger holds 0 or more transactions
// A ledger is associated with a ledger file, with which it is always synchronized

public class Ledger {

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private String ledgerFile;

    public Ledger(String ledgerFile) {
        this.ledgerFile = ledgerFile;
    }

    // Reads ledger file into ArrayList
    public int load(){
        return 0;
    }

    // Saves ledger file
    public int save(){
        return 0;
    }
}
