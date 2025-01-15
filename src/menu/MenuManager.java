package menu;

import factors.FactorManager;
import algebra.AlgebraManager;
import calculations.CalculationsManager;
import complex.ComplexManager;
import conversions.ConversionsManager;
import matrices.MatricesManager;
import functions.*;
import probability.ProbabilityManager;

import java.util.InputMismatchException;
import java.util.Scanner;

import static menu.Colours.*;

public class MenuManager {

    public static void callMenu()  {

        MenuText.calculatorText();

        // Initialising objects
        Scanner scanner = new Scanner(System.in);

        // Boolean variable is true for when user in choosing an option
        boolean currentlySelecting = true;

        while (currentlySelecting) {
            try {

                // Prompt user to select a calculator function
                // Prompt user to select a calculator function
                System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "║" + WHITE + "                   Welcome to the Calculator Menu!              " + CYAN + "║" + RESET);
                System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) for calculations                                 " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) for functions                                    " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) for matrices                                     " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (4) for algebra                                      " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_PURPLE + "    Enter (5) for complex numbers                              " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (6) to find factors and primes                       " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (7) for conversions                                  " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (8) for probability                                  " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to exit the application                          " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
                System.out.print(CYAN + "Enter a choice: " + RESET);

                // Scanner object scans for user input
                int selectorNum = scanner.nextInt();
                System.out.println();

                // Switch case for choosing a calculator function to use
                switch (selectorNum) {
                    case 1 -> {
                        currentlySelecting = false;
                        CalculationsManager.start();
                        MenuManager.clearScreen();
                        break;
                    }
                    case 2 -> {
                        currentlySelecting = false;
                        FunctionsManager.start();
                        MenuManager.clearScreen();
                        break;
                    }
                    case 3 -> {
                        currentlySelecting = false;
                        MatricesManager.start();
                        MenuManager.clearScreen();
                        break;
                    }
                    case 4 -> {
                        currentlySelecting = false;
                        AlgebraManager.start();
                        MenuManager.clearScreen();
                        break;
                    }
                    case 5 -> {
                        currentlySelecting = false;
                        ComplexManager.start();
                        break;
                    }
                    case 6 -> {
                        currentlySelecting = false;
                        FactorManager.start();
                        MenuManager.clearScreen();
                        break;
                    }
                    case 7 -> {
                        currentlySelecting = false;
                        ConversionsManager.start();
                        MenuManager.clearScreen();
                        break;
                    }
                    case 8 -> {
                        currentlySelecting = false;
                        ProbabilityManager.start();
                        MenuManager.clearScreen();
                        break;
                    }
                    case 0 -> {
                        currentlySelecting = false;
                        MenuManager.clearScreen();
                        System.out.println("Goodbye!");
                        scanner.nextLine();
                        System.exit(0);
                        break;
                    }
                    default -> {
                        System.out.println("Please select a valid option!\n");
                        MenuManager.clearScreen();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }
    }

    public static void clearScreen() {
        Scanner scanner = new Scanner(System.in);
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Manual screen clear in case of errors
            System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n");
        }
    }
}
