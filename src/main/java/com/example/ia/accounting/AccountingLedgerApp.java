package com.example.ia.accounting;

// TODO export JAR file


public class AccountingLedgerApp {

    private static final String defaultLedgerFile = "transactions.csv";

    static CliScreen mainScreen;
    static CliScreen ledgerScreen;
    static CliScreen reportsScreen;
    public static void main(String[] args) {

        String ledgerFileName = null;

        if (args.length == 2){
            ledgerFileName = args[1];
        }
        else {
            ledgerFileName = defaultLedgerFile;
        }


        Ledger ledger = new Ledger(ledgerFileName);

        mainScreen = new CliScreenMain(ledger);
        ledgerScreen = new CliScreenLedger(ledger);
        reportsScreen = new CliScreenReports(ledger);

        mainScreen.show();
    }

}
