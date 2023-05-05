package com.example.ia.accounting;

import java.io.*;
import java.util.ArrayList;


// This class represents a Ledger
// A ledger holds 0 or more transactions
// A ledger is associated with a ledger file, with which it is always synchronized

public class Ledger {

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private String ledgerFileName;
    private BufferedReader ledgerFileReader;

    public Ledger(String ledgerFile) {
        this.ledgerFileName = ledgerFile;
        try {
            ledgerFileReader = new BufferedReader(new FileReader(ledgerFileName));
            loadAllFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reads ledger file into ArrayList
    public int loadAllFromFile() throws IOException {
        String s;
        while ((s = ledgerFileReader.readLine()) != null) {
            Transaction t = Transaction.fromFileText(s);
            transactions.add(t);
            return transactions.size();
        }
    }

    // Saves ledger file
    public int saveAllToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ledgerFileName))) {
            for (Transaction t : transactions) {
                writer.write(t.toFileText());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void add(Transaction t) {
        transactions.add(t);
    }


    public ArrayList<Transaction> findAll(){
        ArrayList<Transaction> values = transactions;
        return values;
    }

    public ArrayList<Transaction> findCredits(){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getAmount() > 0.0) values.add(t);
        }
        return values;
    }

    public ArrayList<Transaction> findDebits(){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getAmount() <= 0.0) values.add(t);
        }
        return values;
    }

    public ArrayList<Transaction> findByPayee(String payee){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getPayee().equals(payee)) values.add(t);
        }
        return values;
    }
}
