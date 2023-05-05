package com.example.ia.accounting;

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
                5    Search by Payee
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
            case "5" -> searchByPayee();
            case "0" -> {
                return true;
            }
            default -> badInput();
        }
        return false;

    }

    private void searchByPayee() {
        status = "OK";
    }

    private void displayPreviousYear() {
        status = "OK";
    }

    private void displayYearToDate() {
        status = "OK";
    }

    private void displayPreviousMonth() {
        status = "OK";
    }

    private void displayMonthToDate() {
        status = "OK";
    }


}
