package com.example.ia.accounting;

import java.util.Scanner;

public class CliScreen {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String defaultHeading = """
                        DEFAULT MENU
                        ------------
            """;
    private static final String defaultMenu = """
                0   Quit
            """;
    private static final String defaultPrompt = """
            Select an option (by number):  \s
            """;

    private static final String defaultStatus = "Ready";

    public CliScreen(Ledger ledger){
        this.ledger = ledger;
        this.prompt = defaultPrompt;
        this.heading = defaultHeading;
        this.menu = defaultMenu;
        this.status = defaultStatus;
    }


    private Ledger ledger;
    private String prompt;
    private String menu;
    private String heading;

    private String status;

    public void show() {
        while(true) {
            clearScreen();
            printHeading();
            printContents();
            printMenu();
            printStatus();
            String input = requestInput();
            switch (input){
                case "0":
                    // Return to previous screen
                    return;
                default:
                    // Unrecognized input - break out of loop to redisplay the menu
                    badInput();
                    break;
            }
        }
    }

    private void badInput(){
        status = "I'm sorry, I didn't recognize that.  Please try again";
    }
    private void printStatus() {
        System.out.println(status);
    }


    private String requestInput() {
        System.out.print(prompt);
        return scanner.nextLine();

    }

    private void printHeading() {
        System.out.println(heading);
    }

    private void printMenu() {
        System.out.println(menu);

    }

    private void printContents() {

    }


    private void clearScreen() {
        // Print ANSI escape sequence to clear terminal screen
        System.out.print("\033[2J");

    }


}
