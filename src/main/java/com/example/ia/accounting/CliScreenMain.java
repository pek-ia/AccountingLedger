package com.example.ia.accounting;

import java.time.LocalDate;
import java.time.LocalTime;

public class CliScreenMain extends CliScreen {

    public CliScreenMain(Ledger ledger){
        super(ledger);

        heading = """
                        HOME MENU
                        ---------
            """;
        menu = """
                D    Enter a Deposit
                P    Enter a Payment
                L    Show the Ledger
                X    Exit
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
            case "D":
                enterDeposit();
                break;
            case "P":
                enterPayment();
                break;
            case "L":
                showLedgerScreen();
                break;
            case "X":
                return true;
            default:
                badInput();
        }
        return false;

    }

    private void showLedgerScreen() {
        System.out.println("Showing Ledger screen");
        AccountingLedgerApp.ledgerScreen.show();
    }



    private void enterPayment() {

        System.out.println("Entering a new PAYMENT! ");

        double amount  = requestDoubleInput("Enter the Amount: ");
        if (amount > 0.0) amount = -amount;
        String payee = requestStringInput("Enter the Payee: ");
        String description = requestStringInput("Enter a Description: ");
        LocalDate date = requestDateInput("Enter the date (or return to use Today):");
        LocalTime time = requestTimeInput("Enter the time (or return to use Now): ");

        Transaction t = new Transaction(date, time, description, payee, amount);
        ledger.add(t);
    }

    private void enterDeposit() {
        System.out.println("Entering a new DEPOSIT! ");

        double amount  = requestDoubleInput("Enter the Amount: ");
        if (amount < 0.0) amount = -amount;
        String payer = requestStringInput("Enter the Payer: ");
        String description = requestStringInput("Enter a Description: ");
        LocalDate date = requestDateInput("Enter the date (or return to use Today):");
        LocalTime time = requestTimeInput("Enter the time (or return to use Now): ");

        Transaction t = new Transaction(date, time, description, payer, amount);
        ledger.add(t);

    }

}
