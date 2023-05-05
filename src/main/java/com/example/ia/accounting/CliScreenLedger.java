package com.example.ia.accounting;
import java.time.LocalDate;
import java.time.LocalTime;
public class CliScreenLedger extends CliScreen {
    public CliScreenLedger(Ledger ledger){
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
        prompt = """
            Select an option (by letter):  \s
            """;

        status = "Ready";

    }


    @Override
    protected boolean doInput() {
        String input = requestStringInput();
        switch (input.toUpperCase()){
            case "A":
                displayAllTransactions();
                break;
            case "D":
                displayDeposits();
                break;
            case "P":
                displayPayments();
                break;
            case "R":
                showReportsMenu();
                break;
            case "H":
                return true;
            default:
                badInput();
        }
        return false;

    }

    private void showReportsMenu() {
    }

    private void displayPayments() {
    }

    private void displayDeposits() {
    }

    private void displayAllTransactions() {
        
    }

}
