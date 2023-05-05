package com.example.ia.accounting;

public class AccountingLedgerApp {

    private static String defaultLedgerFile = "./src/main/resources/transactions.csv";

    static CliScreen mainScreen;
    static CliScreen ledgerScreen;
    static CliScreen reportsScreen;
    public static void main(String[] args) {
        Ledger ledger = new Ledger(defaultLedgerFile);

        mainScreen = new CliScreenMain(ledger);
        ledgerScreen = new CliScreenLedger(ledger);
        reportsScreen = new CliScreenReports(ledger);

        mainScreen.show();
    }

}
