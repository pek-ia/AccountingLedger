package com.example.ia.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;


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
        }
        Collections.sort(transactions);
        return transactions.size();
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
        return transactions.size();
    }

    public void add(Transaction t) {
        transactions.add(t);
    }


    public ArrayList<Transaction> findAll(){
        return transactions;
    }

    public ArrayList<Transaction> findCredits(){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getAmount() > 0.0) values.add(t);
        }
        Collections.sort(values);
        return values;
    }

    public ArrayList<Transaction> findDebits(){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getAmount() <= 0.0) values.add(t);
        }
        Collections.sort(values);
        return values;
    }

    public ArrayList<Transaction> findLikePayee(String payee){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getPayee().contains(payee)) values.add(t);
        }
        Collections.sort(values);
        return values;
    }
    public ArrayList<Transaction> findByMonth(Month month){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getDate().getMonth().equals(month)) values.add(t);
        }
        Collections.sort(values);
        return values;
    }
    public ArrayList<Transaction> findByYear(int year){
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t: transactions){
            if (t.getDate().getYear() == year) values.add(t);
        }
        Collections.sort(values);
        return values;
    }
}
