package functions;

import menu.MenuManager;

import java.util.InputMismatchException;
import java.util.Scanner;

import static menu.Colours.*;

public class Polynomial {

    protected static void promptDegree() {
        MenuManager.clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║" + WHITE + "                  Polynomial Solver Menu                        " + CYAN + "║" + RESET);
        System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
        System.out.println(CYAN + "║                                                                ║");
        System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to find linear function roots                  " + CYAN + "   ║" + RESET);
        System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to find quadratic function roots               " + CYAN + "   ║" + RESET);
        System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) to solve cubic function roots                    " + CYAN + " ║" + RESET);
        System.out.println(CYAN + "║                                                                ║");
        System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to return to functions menu                     " + CYAN + "  ║" + RESET);
        System.out.println(CYAN + "║                                                                ║");
        System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.print(CYAN + "Enter a choice: " + RESET);




        int selectorNum = scanner.nextInt();
        System.out.println();
        String[]roots;

        // Switch case for choosing a calculator function to use
        boolean currentlySelecting = true;
        while (currentlySelecting) {
            switch (selectorNum) {
                case 1 -> {
                    try {
                        System.out.print("For ax + b = 0, please enter a values for a and b separated by spaces (e.g. 10 5):  ");

                        //take input for equation
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double[]coeffieients = {a,b};

                        //Solve and print out equation + roots
                        PolynomialSolver linear = new PolynomialSolver(a, b);
                        roots = linear.linearPolynomial();
                        System.out.println("\n" + linear.rootsString(roots, coeffieients) + "\n");

                        //Return to selection
                        scanner.nextLine();
                        currentlySelecting = false;

                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                }
                case 2 -> {
                    try {
                        System.out.print("For ax^2 + bx + c = 0, please enter a values for a, b and c separated by spaces (e.g. 2 10 5):  ");

                        //take input for equation
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double c = scanner.nextDouble();
                        double[]coeffieients = {a,b,c};

                        //Solve and print out equation + roots
                        PolynomialSolver quadratic = new PolynomialSolver(a, b, c);
                        roots = quadratic.quadraticPolynomial();
                        System.out.println(quadratic.rootsString(roots, coeffieients));

                        //Return to selection
                        scanner.nextLine();
                        currentlySelecting = false;

                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                }
                case 3 -> {
                    try {
                        System.out.print("For ax^3 + bx^2 + cx + d = 0, please enter a values for a, b, c and d separated by spaces (e.g. 1 3 -10 5):  ");

                        //take input for equation
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double c = scanner.nextDouble();
                        double d = scanner.nextDouble();
                        double[]coeffieients = {a,b,c,d};

                        //Solve and print out equation
                        PolynomialSolver cubic = new PolynomialSolver(a, b, c, d);
                        roots = cubic.cubicPolynomial();
                        System.out.println("\n" + cubic.rootsString(roots, coeffieients) + "\n");

                        //Return to selection
                        scanner.nextLine();
                        currentlySelecting = false;
                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("For ax^4 + bx^3 +cx^2 + dx + e = 0, please enter a values for a, b, c, d and e separated by spaces (e.g. -2 4 0 10 5):  ");
                        double a = scanner.nextDouble();
                        double b = scanner.nextDouble();
                        double c = scanner.nextDouble();
                        double d = scanner.nextDouble();
                        double e = scanner.nextDouble();
                        double[]coeffieients = {a,b,c,d,e};


                        PolynomialSolver quartic = new PolynomialSolver(a, b, c, d, e);
                        //System.out.println("\n" + quartic.rootsString(roots, coeffieients) + "\n");
                        scanner.nextLine();
                        currentlySelecting = false;
                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("Please enter valid double values!");
                        scanner.nextLine();
                    }
                }
                case 0 -> {
                    MenuManager.clearScreen();
                    FunctionsManager.start();
                }
            }
        }
        scanner.nextLine();
        Polynomial.promptDegree();
    }
}
