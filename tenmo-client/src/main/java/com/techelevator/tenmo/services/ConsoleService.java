package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.*;
import com.techelevator.util.ConsoleColors;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);
    private final NumberFormat currency = NumberFormat.getCurrencyInstance();

    public int promptForMenuSelection(String prompt) {
        System.out.print(ConsoleColors.DARK_BLUE + prompt + ConsoleColors.RESET);
        String input = scanner.nextLine();
        return parseInt(input, -1);
    }

    public void printGreeting() {
        System.out.println(ConsoleColors.ORANGE + "*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************" + ConsoleColors.RESET);
    }

    public void printUsers(User[] users) {
        printSeparator(22);
        System.out.println(ConsoleColors.ORANGE + "Users");
        System.out.printf("%-10s%10s %n", "ID", "Username" + ConsoleColors.RESET);
        printSeparator(22);
        for (User user : users) {
            System.out.printf(ConsoleColors.DARK_BLUE + "%-10d%10s %n", user.getId(), user.getUsername() + ConsoleColors.RESET);
        }
        printSeparator(10);
    }

    public void printTransfers(TransferDto[] transfers, String currentUsername) {
        printSeparator(37);
        System.out.println(ConsoleColors.ORANGE + "Transfers");
        System.out.printf(" %-10s%15s%15s %n", "ID", "From/To", "Amount" + ConsoleColors.RESET);
        printSeparator(37);

        for (TransferDto transfer : transfers) {
            String fromUsername = transfer.getAccountFrom();
            String toUsername = transfer.getAccountTo();
            BigDecimal amount = transfer.getAmount();
            int transferId = transfer.getTransferId();

            if (currentUsername.equals(fromUsername)) {
                System.out.printf(ConsoleColors.DARK_BLUE + " %-10d%15s%15s %n", transferId, "To: " + toUsername, currency.format(amount) + ConsoleColors.RESET);
            } else if (currentUsername.equals(toUsername)) {
                System.out.printf(ConsoleColors.DARK_BLUE + " %-10d%15s%15s %n", transferId, "From: " + fromUsername, currency.format(amount) + ConsoleColors.RESET);
            }
        }

        printSeparator(37);
    }

    public void printTransferDetails(TransferDetailsDto transferDetailsDto) {
        printSeparator(37);
        System.out.println(ConsoleColors.ORANGE + "Transfer Details" + ConsoleColors.RESET);
        printSeparator(37);
        System.out.printf(ConsoleColors.DARK_BLUE + "Id: %d%n", transferDetailsDto.getTransferId());
        System.out.printf("From: %s%n", transferDetailsDto.getAccountFrom());
        System.out.printf("To: %s%n", transferDetailsDto.getAccountTo());
        System.out.printf("Type: %s%n", transferDetailsDto.getTransferType());
        System.out.printf("Status: %s%n", transferDetailsDto.getTransferStatus());
        System.out.printf("Amount: %s%n", currency.format(transferDetailsDto.getAmount()) + ConsoleColors.RESET);
        printSeparator(37);
    }

    public void printPendingTransfers(TransferPendingDto[] pendingTransfers) {
        if (pendingTransfers == null || pendingTransfers.length == 0) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "No pending transfers." + ConsoleColors.RESET);
            return;
        }

        printSeparator(37);
        System.out.println(ConsoleColors.GREEN + "Pending Transfers");
        System.out.printf(" %-10s%10s%15s %n", "ID", "From", "Amount" + ConsoleColors.RESET);
        printSeparator(37);

        for (TransferPendingDto transfer : pendingTransfers) {
            System.out.printf(ConsoleColors.DARK_BLUE + "  %-10d%10s%15s %n",
                    transfer.getTransferId(),
                    transfer.getAccountFrom(),
                    currency.format(transfer.getAmount()) + ConsoleColors.RESET);
        }
        printSeparator(37);
    }

    public void printApproveRejectMenu() {
        System.out.println(ConsoleColors.ORANGE);
        System.out.println("1: Approve");
        System.out.println("2: Reject");
        System.out.println("0: Exit");
        System.out.println(ConsoleColors.RESET);
    }

    public void printLoginMenu() {
        System.out.println(ConsoleColors.ORANGE);
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println(ConsoleColors.RESET);
    }

    public void printMainMenu() {
        System.out.println(ConsoleColors.ORANGE);
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println(ConsoleColors.RESET);
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString(ConsoleColors.DARK_BLUE + "Username: ");
        String password = promptForString("Password: " + ConsoleColors.RESET);
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(ConsoleColors.DARK_BLUE + prompt + ConsoleColors.RESET);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a valid number." + ConsoleColors.RESET);
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(ConsoleColors.DARK_BLUE + prompt + ConsoleColors.RESET);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println(ConsoleColors.DARK_BLUE + "\nPress Enter to continue..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + errorMessage + ConsoleColors.RESET);
    }

    private void printSeparator(int length) {
        System.out.println(ConsoleColors.ORANGE + "-".repeat(length) + ConsoleColors.RESET);
    }

    private int parseInt(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}