package com.example.ia.accounting;
import java.time.LocalDate;
import java.time.LocalTime;
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
        prompt = """
            Select an option (by number):  \s
            """;

        status = "Ready";

    }


    @Override
    protected boolean doInput() {
        String input = requestStringInput();
        switch (input){
            case "1":
                displayMonthToDate();
                break;
            case "2":
                displayPreviousMonth();
                break;
            case "3":
                displayYearToDate();
                break;
            case "4":
                displayPreviousYear();
                break;
            case "5":
                searchByPayee();
                break;
            case "0":
                return true;
            default:
                badInput();
        }
        return false;

    }

    private void searchByPayee() {
    }

    private void displayPreviousYear() {
    }

    private void displayYearToDate() {
    }

    private void displayPreviousMonth() {
    }

    private void displayMonthToDate() {
        
    }


}
