package com.example.ia.accounting;

public class AccountingLedgerApp {

    private static String defaultLedgerFile = "./src/main/resources/transactions.csv";
    public static void main(String[] args) {
        Ledger ledger = new Ledger(defaultLedgerFile);
        CliScreen screen = new CliScreen(ledger);
        screen.show();
    }

}
