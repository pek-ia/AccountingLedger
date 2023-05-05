package com.example.ia.accounting;

import java.io.*;
import java.util.ArrayList;


// This class represents a Ledger
// A ledger holds 0 or more transactions
// A ledger is associated with a ledger file, with which it is always synchronized

public class Ledger {

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private String ledgerFile;

    public Ledger(String ledgerFile) {
        this.ledgerFile = ledgerFile;
        loadAllFromFile();
    }

    // Reads ledger file into ArrayList
    public int loadAllFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(ledgerFile))) {
            String s;
            while ((s = reader.readLine()) != null) {
                Transaction t = Transaction.fromFileText(s);
                transactions.add(t);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // Saves ledger file
    public int saveAllToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ledgerFile))) {
            for (Transaction t: transactions){
                writer.write(t.toFileText());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
