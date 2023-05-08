package com.example.ia.accounting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * CliScreen is a superclass (base class) that can be used
 * to develop menu-driven user interfaces that run on ANSI
 * standard terminals and emulators such as XTerm, MinTerm,
 * etc.
 *
 * The general appearance of a CLI menu is divided
 * into four sections:
 *
 *    heading     - A Heading, that introduces the menu
 *    menu        - A list of options, identified by numbers or letters
 *    status      - A status line that can be used to report input errors
 *    menu_prompt - That asks the user for the appropriate inputThis class is not strongly encapsulated.
 *
 * Subclasses are granted access to the following
 * protected variables to make coding simple.
 *
 *   String heading
 *   String menu
 *   String menu_prompt
 *   String status
 *   Scanner scanner
 *
 *   This superclass is not really very versatile.  It is specific to
 *   the AccountingLedger app and expects to work with
 *   the following domain classes:
 *
 *   Ledger       - it uses the ledger to search for transactions
 *   Transaction  - it formats transactions for display
 *
 * A subclass must provide its own constructor, and
 * must @Override the following methods:
 *
 *   doInputUntilDone()
 *
 */
public class CliScreen {

    // STATIC VARIABLES ------------------------------------

    // Notice that these are private, as they simply
    // provide some default values used by the
    // CliScreen constructor

    private static final String defaultHeading = """
                        DEFAULT MENU
                        ------------
            """;
    private static final String defaultMenu = """
                D   Do something fun
                X   Quit this screen
            """;
    private static final String defaultStatus = "Ready";

    private static final String defaultPrompt = """
            Select an option (by letter):  \s
            """;



    // MEMBER VARIABLES --------------------------------------
    // Notice that these are protected.  Subclasses are
    // expected to use these

    protected final Scanner scanner;

    protected final Ledger ledger;


    // A subclass should initialize these variables
    // in its own constructor

    protected String heading;
    protected String menu;
    protected String status;
    protected String menuPrompt;

    /**
     * CONSTRUCTOR
     *
     * Subclasses must call this from their own constructor, and
     * should initialize their own values for
     *
     * @param ledger is the ledger that this UI should work with
     */
    public CliScreen(Ledger ledger) {
        this.ledger = ledger;
        scanner = new Scanner(System.in);
        menuPrompt = defaultPrompt;
        heading = defaultHeading;
        menu = defaultMenu;
        status = defaultStatus;
    }


    /**
     * This method displays the screen and requests input.
     * The screen will be redisplayed repeatedly as long
     * as doInputUntilDone() returns false.
     *
     * When doInputUntilDone() returns true, this method
     * will return to the caller.
     *
     */
    public void show() {
        do {
            clearScreen();
            displayHeading();
            displayMenu();
            displayStatus();
        } while (!doInputUntilDone());
    }

    private void displayHeading() {
        System.out.print("\033[33m");  // Yellow
        System.out.println(heading);
        System.out.print("\033[37m");  // White
    }

    private void displayMenu() {
        System.out.println(menu);
    }

    private void displayStatus() {
        System.out.println(status);
    }

    private void displayMenuPrompt() {
        System.out.println(menuPrompt);
    }

    private void clearScreen() {
        // Print some ANSI escape sequence to clear terminal screen :-)
        System.out.print("\033[2J\033[H");
    }

    /**
     * This method must be overridden in subclasses
     * The implementation shown here is just for reference
     *
     * @return true if this screen is done handling input,
     * or false to redisplay the screen for input
     *
     */
    protected boolean doInputUntilDone() {
        String input = requestStringInput();
        switch (input) {
            // A menu subclass must provide its own case to deal
            // with the choices that it displays

            // Examples of usage:

            // Call a function
            case "D" -> doSomething();

            // Unrecognized input - Update status
            default -> badInput();

            // Done - return to the caller that displayed this screen
            case "X" -> { return true; }
        }
        return false;  // Not done
    }

    private void doSomething() {
        System.out.println("Something fun has been done... ");
        status = "OK";
    }



    // UTILITY METHODS FOR SUBCLASSES


    protected void badInput() {
        status = "I'm sorry, I didn't recognize that.  Please try again";
    }

    // INPUT METHODS FOR TRANSACTION-RELATED TYPES

    protected String requestStringInput() {
        displayMenuPrompt();
        return scanner.nextLine();

    }

    protected String requestStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();

    }

    protected double requestDoubleInput(String prompt) {
        String input = requestStringInput(prompt);
        return Double.parseDouble(input);
    }

    protected LocalDate requestDateInput(String prompt) {
        String input = requestStringInput(prompt);
        if (input.equals("")) {
            return LocalDate.now();
        } else {
            return LocalDate.parse(input);
        }
    }

    protected LocalTime requestTimeInput(String prompt) {
        String input = requestStringInput(prompt);
        if (input.equals("")) {
            return LocalTime.now();
        } else {
            return LocalTime.parse(input);
        }
    }


    // DISPLAY FUNCTIONS FOR TRANSACTIONS

    protected void displayTransactions(List<Transaction> list) {
        System.out.println("Listing matching Transactions: \n");
        for(Transaction t: list){
            displayTransaction(t);
        }
        status = "OK";
        requestStringInput("\nHit Enter to return to the menu:");

    }

    protected void displayTransaction(Transaction t){
        System.out.println(toScreenText(t));
    }

    /**
     * This method formats a transaction for display on the screen
     *
     * @param t a transaction
     * @return A formatted string for display on the CLI
     */
    protected String toScreenText(Transaction t){
        return String.format("%10s  %-25s %-30s %8.2f", t.getDate(), t.getPayee(), t.getDescription(), t.getAmount());
    }

}
