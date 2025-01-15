package functions;
import calculations.Calculations;
import menu.MenuManager;
import menu.MenuText;
import menu.Start;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import static menu.Colours.*;

public class FunctionsManager implements Start{

    // Initializing variables
    public static String substitution = "";
    public static String substitutedExpression = "";
    public static List<String> substitutedExpressions = new ArrayList<>();

    public static void start() {
        MenuManager.clearScreen();

        MenuText.functionsText();
        Scanner scanner = new Scanner(System.in);

        boolean currentlySelecting = true;
        while (currentlySelecting) {
            try {

                // Prompt user to select a choice for functions
                System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "║" + WHITE + "                     Welcome to Functions                       " + CYAN + "║" + RESET);
                System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to evaluate a single-variate function            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to evaluate a multivariate function              " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) to compose a function                            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (4) for the bisection method                         " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_PURPLE + "    Enter (5) for the secant method                            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (6) to solve polynomials                             " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (7) for differentiation                              " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (8) for the gradient descent method                  " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (9) for Newton's method                              " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to return to the menu                            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
                System.out.print(CYAN + "Enter a choice: " + RESET);




                // Scanner object scans for user input
                int selectorNum = scanner.nextInt();
                System.out.println();

                // Switch case for choosing an option
                switch (selectorNum) {
                    case 1 -> {
                        Functions.singleVariableFunction();
                        currentlySelecting = false;
                        break;
                    }
                    case 2 -> {
                        Functions.multiVariateFunction();
                        currentlySelecting = false;
                        break;
                    }
                    case 3 -> {
                        Functions.composeFunctions();
                        currentlySelecting = false;
                        break;
                    }
                    case 4 -> {
                        Functions.bisectionMethod();
                        currentlySelecting = false;
                        break;
                    }
                    case 5 -> {
                        Functions.secantMethod();
                        currentlySelecting = false;
                        break;
                    }
                    case 6 -> {
                        Polynomial.promptDegree();
                        currentlySelecting = false;
                        break;
                    }
                    case 7 -> {
                        Differentiation.numericDifferentiation();
                        currentlySelecting = false;
                        break;
                    }
                    case 8 -> {
                        Differentiation.gradientDescent();
                        currentlySelecting = false;
                        break;
                    }
                    case 9 -> {
                        Differentiation.newtonMethod();
                        currentlySelecting = false;
                        break;
                    }
                    case 0 -> {
                        MenuManager.clearScreen();
                        MenuManager.callMenu();
                        currentlySelecting = false;
                        break;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
            MenuManager.clearScreen();
            FunctionsManager.start();
        }
    }

}
