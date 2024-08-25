package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.util.BasicLogger;
import com.techelevator.util.ConsoleColors;

import java.math.BigDecimal;
import java.text.NumberFormat;


public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AccountService accountService = new AccountService();
    private final TransferService transferService = new TransferService();

    private final UserService userService = new UserService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;
    private NumberFormat currency = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection(ConsoleColors.DARK_BLUE + "Please choose an option: " + ConsoleColors.RESET);
            switch (menuSelection) {
                case 1:
                    handleRegister();
                    break;
                case 2:
                    handleLogin();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid Selection" + ConsoleColors.RESET);
                    consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage("An error occured check the log for details");
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid user credentials please try again" + ConsoleColors.RESET);
            handleLogin();
        } else {
            String token = currentUser.getToken();
            accountService.setAuthToken(token);
            transferService.setAuthToken(token);
            userService.setAuthToken(token);
            BasicLogger.log("User logged in");
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection(ConsoleColors.DARK_BLUE + "Please choose an option: " + ConsoleColors.RESET);
            switch (menuSelection) {
                case 1:
                    viewCurrentBalance();
                    break;
                case 2:
                    viewTransferHistory();
                    break;
                case 3:
                    viewPendingRequests();
                    break;
                case 4:
                    sendBucks();
                    break;
                case 5:
                    requestBucks();
                    break;
                case 0:
                    System.out.println(ConsoleColors.ORANGE + "Thank you for using Tenmo. Goodbye! \uD83D\uDC4B " + ConsoleColors.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid Selection" + ConsoleColors.RESET);
            }
            consoleService.pause();
        }
    }

    private void viewCurrentBalance() {

        BigDecimal balance = accountService.getBalance();
        BasicLogger.log(String.format("%s checked their balance. Balance: %s",
                currentUser.getUser().getUsername(), currency.format(balance)));
        System.out.println(ConsoleColors.GREEN + "Your current account balance is: " + currency.format(balance) + ConsoleColors.RESET);
    }

    private void viewTransferHistory() {
        TransferDto[] transfers = transferService.getTransfers();
        String currentUsername = currentUser.getUser().getUsername();
        consoleService.printTransfers(transfers, currentUsername);
        int choice;
        while ((choice = consoleService.promptForInt(ConsoleColors.DARK_BLUE + "Please enter transfer ID to view details (0 to cancel): " + ConsoleColors.RESET)) != 0) {
            if (isValidTransferId(choice, transfers)) {
                consoleService.printTransferDetails(transferService.getTransfersById(choice));
            } else {
                consoleService.printMessage(ConsoleColors.RED_BOLD_BRIGHT + "Invalid transfer ID. Please try again." + ConsoleColors.RESET);
            }
        }
    }

    private void viewPendingRequests() {
        TransferPendingDto[] pendingTransfers = transferService.getPending();
        consoleService.printPendingTransfers(pendingTransfers);

        if (pendingTransfers.length == 0) {
            return;
        }

        int choice;
        while ((choice = consoleService.promptForInt(ConsoleColors.DARK_BLUE + "Please enter transfer ID to approve/reject (0 to cancel): " + ConsoleColors.RESET)) != 0) {
            if (isValidTransferId(choice, pendingTransfers)) {
                handleTransferApprovalOrRejection(choice);
            } else {
                consoleService.printMessage(ConsoleColors.RED_BOLD_BRIGHT + "Invalid transfer ID. Please try again." + ConsoleColors.RESET);
            }
        }
    }

    private void sendBucks() {
        User[] users = userService.getUsers();
        consoleService.printUsers(users);
        int choice;
        while ((choice = consoleService.promptForInt(ConsoleColors.DARK_BLUE + "Enter ID of user you are sending to (0 to cancel): " + ConsoleColors.RESET)) != 0) {
            if (!(choice == currentUser.getUser().getId())) {
                int amount = consoleService.promptForInt(ConsoleColors.DARK_BLUE + "Enter amount: " + ConsoleColors.RESET);
                String response = transferService.sendTeBucks(choice, amount);
                // Print the response only if it's not null
                if (response != null) {
                    System.out.println(ConsoleColors.GREEN + response + ConsoleColors.RESET);
                }
            } else {
                consoleService.printErrorMessage("You are not allowed to send money to your self please try again");
            }
        }
    }

    private void requestBucks() {
        User[] users = userService.getUsers();
        consoleService.printUsers(users);
        int userId = consoleService.promptForInt(ConsoleColors.DARK_BLUE + "Enter ID of user you are requesting from (0 to cancel): " + ConsoleColors.RESET);
        if (userId == 0) {
            return;
        }
        int amount = consoleService.promptForInt(ConsoleColors.DARK_BLUE + "Enter amount: " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + transferService.requestTeBucks(userId, amount) + ConsoleColors.RESET);
    }

    private boolean isValidTransferId(int transferId, TransferPendingDto[] pendingTransfers) {
        for (TransferPendingDto transfer : pendingTransfers) {
            if (transfer.getTransferId() == transferId) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidTransferId(int transferId, TransferDto[] transfers) {
        for (TransferDto transfer : transfers) {
            if (transfer.getTransferId() == transferId) {
                return true;
            }
        }
        return false;
    }

    private void handleTransferApprovalOrRejection(int transferId) {
        consoleService.printApproveRejectMenu();
        int option = consoleService.promptForInt(ConsoleColors.DARK_BLUE + "Please choose an option: 1 to approve, 2 to reject (0 to cancel):" + ConsoleColors.RESET);
        switch (option) {
            case 1:
                TransferStatus approveStatus = new TransferStatus(2, "Approved");
                String approvedResponse = transferService.approveOrRejectTransfer(transferId, approveStatus);
                if (approvedResponse.contains("Insufficient")) {
                    consoleService.printMessage(ConsoleColors.RED_BOLD_BRIGHT + approvedResponse + ConsoleColors.RESET);
                } else {
                    consoleService.printMessage(ConsoleColors.GREEN + approvedResponse + ConsoleColors.RESET);
                }


                break;
            case 2:
                TransferStatus rejectStatus = new TransferStatus(3, "Rejected");
                String rejectResponse = transferService.approveOrRejectTransfer(transferId, rejectStatus);
                consoleService.printMessage(ConsoleColors.GREEN + rejectResponse + ConsoleColors.RESET);

                break;
            case 0:
                consoleService.printMessage(ConsoleColors.RED_BOLD_BRIGHT + "Operation canceled." + ConsoleColors.RESET);
                break;
            default:
                consoleService.printMessage(ConsoleColors.RED_BOLD_BRIGHT + "Invalid option. Please try again." + ConsoleColors.RESET);
        }
    }
}