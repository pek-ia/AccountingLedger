package com.example.ia.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;


// This class represents a Ledger
// A ledger holds 0 or more transactions
// A ledger is associated with a ledger file, with which it is always synchronized

public class Ledger {

    private final ArrayList<Transaction> transactions = new ArrayList<>();
    private final String ledgerFileName;
    private final BufferedReader ledgerFileReader;

    public Ledger(String ledgerFile) {
        this.ledgerFileName = ledgerFile;
        try {
            ledgerFileReader = new BufferedReader(new FileReader(ledgerFileName));
            loadAllFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Transaction fromFileText(String s) {
        String[] fields = s.split("\\|");
        LocalDate date = LocalDate.parse(fields[0]);
        LocalTime time = LocalTime.parse(fields[1]);
        String description = fields[2];
        String payee = fields[3];
        double amount = Double.parseDouble(fields[4]);

        return new Transaction(date, time, description, payee, amount);
    }

    private String toFileText(Transaction t) {
        return String.format("%s|%s|%s|%s|%.2f", t.getDate(), t.getTime(), t.getDescription(), t.getPayee(), t.getAmount());
    }

    // Reads ledger file into ArrayList
    private int loadAllFromFile() throws IOException {
        String s;
        while ((s = ledgerFileReader.readLine()) != null) {
            Transaction t = fromFileText(s);
            transactions.add(t);
        }
        Collections.sort(transactions);
        return transactions.size();
    }

    // Saves ledger file
    private int saveAllToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ledgerFileName))) {
            for (Transaction t : transactions) {
                writer.write(toFileText(t));
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions.size();
    }

    private void appendToFile(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ledgerFileName, true))) {
            writer.newLine();
            writer.write(toFileText(t));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Transaction t) {

        transactions.add(t);
        appendToFile(t);

    }


    /*
     *  FINDER METHODS
     */
    public ArrayList<Transaction> findAll() {
        return transactions;
    }

    public ArrayList<Transaction> findCredits() {
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0.0) values.add(t);
        }
        Collections.sort(values);
        return values;
    }

    public ArrayList<Transaction> findDebits() {
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() <= 0.0) values.add(t);
        }
        Collections.sort(values);
        return values;
    }

    public ArrayList<Transaction> findLikePayee(String payee) {
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getPayee().contains(payee)) values.add(t);
        }
        Collections.sort(values);
        return values;
    }

    public ArrayList<Transaction> findByMonthAndYear(Month month, int year) {
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getMonth().equals(month)
                    && t.getDate().getYear() == year) values.add(t);
        }
        Collections.sort(values);
        return values;
    }

    public ArrayList<Transaction> findByYear(int year) {
        ArrayList<Transaction> values = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year ) values.add(t);
        }
        Collections.sort(values);
        return values;
    }
}
