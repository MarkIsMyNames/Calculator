package probability;

import functions.FunctionsManager;
import menu.MenuManager;
import menu.MenuText;
import menu.Start;

import java.util.InputMismatchException;
import java.util.Scanner;

import static menu.Colours.*;

public class ProbabilityManager implements Start {
    public static void main(String[] args) {
        start();
    }
    public static Scanner scanner = new Scanner(System.in);

    public static void start() {

        MenuManager.clearScreen();

        boolean currentlySelecting = true;
        while (currentlySelecting) {
            try {

                MenuText.probabilityText(); //Show ASCII art text for calculator
                // Prompt user to select a choice
                System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "║" + WHITE + "                 Welcome to Probability                         " + CYAN + "║" + RESET);
                System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to use Bayes theorem                            " + CYAN + "  ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to check if events and independent               " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) to find P(A|B)                                   " + CYAN + " ║" + RESET);
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
                        Probability.bayesTheorem();
                        break;
                    }
                    case 2 -> {
                        currentlySelecting = false;
                        Probability.independenceChecker();
                        break;
                    }
                    case 3 -> {
                        currentlySelecting = false;
                        Probability.aGivenB();
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
            MenuManager.clearScreen();
            ProbabilityManager.start();
        }
    }

}

