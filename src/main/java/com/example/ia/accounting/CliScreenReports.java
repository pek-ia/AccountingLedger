package com.example.ia.accounting;

import java.time.LocalDate;

public class CliScreenReports extends CliScreen {

    public CliScreenReports(Ledger ledger){
        super(ledger);

        heading = """
                        REPORTS MENU
                        ------------
            """;
        menu = """
                1    Month to Date
                2    Previous Month
                3    Year to Date
                4    Previous Year
                5    Search by Payer/Payee
                0    Back to Ledger Menu
            """;
        menu_prompt = """
            Select an option (by number):  \s
            """;

        status = "Ready";

    }


    @Override
    protected boolean doInput() {
        String input = requestStringInput();
        switch (input) {
            case "1" -> displayMonthToDate();
            case "2" -> displayPreviousMonth();
            case "3" -> displayYearToDate();
            case "4" -> displayPreviousYear();
            case "5" -> displayByPayee();
            case "0" -> {
                return true;
            }
            default -> badInput();
        }
        return false;

    }

    private void displayByPayee() {
        String payee = requestStringInput("Enter name of payer/payee:  ");
        displayTransactions(ledger.findLikePayee(payee));
    }

    private void displayPreviousYear() {
        displayTransactions( ledger.findByYear(LocalDate.now().getYear() - 1));
    }

    private void displayYearToDate() {
        displayTransactions( ledger.findByYear(LocalDate.now().getYear()));
    }

    private void displayPreviousMonth() {
        displayTransactions(ledger.findByMonth(LocalDate.now().getMonth().minus(1)));
    }

    private void displayMonthToDate() {
        displayTransactions(ledger.findByMonth(LocalDate.now().getMonth()));
    }

}
