
package conversions;

import menu.MenuManager;
import menu.MenuText;
import menu.Start;

import java.util.InputMismatchException;
import java.util.Scanner;

import static menu.Colours.*;

public class ConversionsManager implements Start {

    public static Scanner scanner = new Scanner(System.in);

    public static void start() {

        MenuManager.clearScreen();

        boolean currentlySelecting = true;
        while (currentlySelecting) {
            try {

                MenuText.conversionsText(); //Show ASCII art text for calculator
                // Prompt user to select a choice for conversions
                System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "║" + WHITE + "                 Welcome to Conversions                         " + CYAN + "║" + RESET);
                System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to to convert between decimal, binary and hex    " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to convert currencies                            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to return to the menu                            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
                System.out.print(CYAN + "Enter a choice: " + RESET);


                // Scanner object scans for user input
                int selectorNum = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                // Switch case for choosing an option
                switch (selectorNum) {
                    case 1 -> {
                        currentlySelecting = false;
                        CompSciConversions.compSciConversions();
                        break;
                    }
                    case 2 -> {
                        currentlySelecting = false;
                        CurrencyConversions.currencyConversions();
                        break;
                    }
                    case 0 -> {
                        currentlySelecting = false;
                        MenuManager.clearScreen();
                        MenuManager.callMenu();
                        return;
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }
    }

}

