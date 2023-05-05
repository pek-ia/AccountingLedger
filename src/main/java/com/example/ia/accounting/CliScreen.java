package com.example.ia.accounting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class CliScreen {


    private static final String defaultHeading = """
                        DEFAULT MENU
                        ------------
            """;
    private static final String defaultMenu = """
                X   Quit this screen
            """;
    private static final String defaultPrompt = """
            Select an option (by letter):  \s
            """;

    private static final String defaultStatus = "Ready";


    protected final Scanner scanner = new Scanner(System.in);

    protected final Ledger ledger;
    protected String menu_prompt;
    protected String menu;
    protected String heading;
    protected String status;

    public CliScreen(Ledger ledger) {
        this.ledger = ledger;
        menu_prompt = defaultPrompt;
        heading = defaultHeading;
        menu = defaultMenu;
        status = defaultStatus;
    }


    public void show() {
        String input = "";
        do {
            clearScreen();
            printHeading();
            printContents();
            printMenu();
            printStatus();
        } while (!doInput());
    }

    protected boolean doInput() {
        String input = requestStringInput();
        switch (input) {
            case "X" -> {
                // Return to previous screen
                return true;
            }
            default -> {
                // Unrecognized input - break out of loop to redisplay the menu
                badInput();
                return false;
            }
        }
    }

    protected void badInput() {
        status = "I'm sorry, I didn't recognize that.  Please try again";
    }

    private void printStatus() {
        System.out.println(status);
    }


    protected String requestStringInput() {
        System.out.print(menu_prompt);
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
