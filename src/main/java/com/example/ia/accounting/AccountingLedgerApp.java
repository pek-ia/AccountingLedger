package com.example.ia.accounting;

// TODO Write transactions to file
// TODO Implement reports - DONE
// TODO rename print/display/show methods? - DONE
// TODO sort the arraylist properly - DONE
// TODO Pause after report output before displaying menu - DONE
// TODO export JAR file
// TODO refactor the transaction formatting into Ledger and CLIScreen

public class AccountingLedgerApp {

    private static final String defaultLedgerFile = "./src/main/resources/transactions.csv";

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
