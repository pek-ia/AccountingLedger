package com.example.ia.accounting;

public class CliScreenLedger extends CliScreen {
    public CliScreenLedger(Ledger ledger) {
        super(ledger);

        heading = """
                            LEDGER MENU
                            -----------
                """;
        menu = """
                    A    Show All transactions
                    D    Show Deposits
                    P    Show Payments
                    R    Reports Menu
                    H    Return to Home
                """;
        menuPrompt = """
                Select an option (by letter):  \s
                """;

        status = "Ready";

    }


    @Override
    protected boolean doInputUntilDone() {
        String input = requestStringInput();
        switch (input.toUpperCase()) {
            case "A" -> displayAllTransactions();
            case "D" -> displayDeposits();
            case "P" -> displayPayments();
            case "R" -> showReportsScreen();
            case "H" -> {
                return true;
            }
            default -> badInput();
        }
        return false;

    }

    private void showReportsScreen() {
        AccountingLedgerApp.reportsScreen.show();
    }

    private void displayPayments() {
        System.out.println("Listing your Payments:\n");
        displayTransactions(ledger.findDebits());
    }

    private void displayDeposits() {
        System.out.println("Listing your Deposits:\n");
        displayTransactions(ledger.findCredits());
    }

    private void displayAllTransactions() {
        displayTransactions(ledger.findAll());
    }

}
