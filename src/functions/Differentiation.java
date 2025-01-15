package functions;

import calculations.Calculations;
import menu.MenuManager;

public class Differentiation extends Functions {

    // Default constructor
    public Differentiation() {
        super();
    }

    // Constructor to inherit input, range, and step size
    public Differentiation(String functionInput, double startRange, double endRange, double stepSize) {
        super(functionInput, startRange, endRange, stepSize);
    }

    // Forward Difference Method
    public double forwardDifference(double x, double h) {
        double f_x = subIn(functionInput, x);          // f(x)
        double f_x_plus_h = subIn(functionInput, x + h); // f(x + h)
        return (f_x_plus_h - f_x) / h;
    }

    // Central Difference Method
    public double centralDifference(double x, double h) {
        double f_x_plus_h = subIn(functionInput, x + h); // f(x + h)
        double f_x_minus_h = subIn(functionInput, x - h); // f(x - h)
        return (f_x_plus_h - f_x_minus_h) / (2 * h);
    }

    // Numerical Differentiation Table
    public void differentiationTable() {
        System.out.println("╔══════════════════════╦══════════════════════╗");
        System.out.printf("║ %-20s ║ %-20s ║\n", "x", "f'(x)");
        System.out.println("╠══════════════════════╬══════════════════════╣");

        for (double x = startRange; x <= endRange; x += stepSize) {
            double derivative = centralDifference(x, stepSize); // Use central difference
            System.out.printf("║ %-20.10f ║ %-20.10f ║\n", x, derivative);
        }

        System.out.println("╚══════════════════════╩══════════════════════╝");
    }

    // Gradient Descent Method
    public static void gradientDescent() {
        Differentiation diff = new Differentiation();
        String functionInput = diff.promptFunctionInput("Enter a function to minimize (e.g., x^2 - 4x + 4): ");
        diff.functionInput = functionInput; // Set functionInput for this instance

        double x = getValidDouble("Enter initial guess: ");
        double learningRate = diff.greaterThan0("Enter learning rate (e.g., 0.01): ");
        System.out.print("Enter the number of iterations: ");
        int iterations = scanner.nextInt();
        double gradient;
        System.out.println("");//skip a line

        try {
            for (int i = 0; i < iterations; i++) {
                gradient = diff.centralDifference(x, 1e-5); // Use central difference
                x = x - learningRate * gradient; // Gradient descent update
                System.out.printf("Iteration %d: x = %.6f\t\t Gradient = %.6f\n", i + 1, x, gradient);

                if (gradient == 0) {
                    System.out.printf("\nThe minimum has been found at iteration %d", i);
                    i = iterations;
                }
            }

            System.out.printf("\nThe minimum occurs at approximately x = %.6f\n", x);
        } catch(IllegalStateException | ArithmeticException e) {
            System.out.println("An error occurred during the algorithm, please ensure the function is valid");
            scanner.nextLine();
        }

        scanner.nextLine();
        scanner.nextLine();
        MenuManager.clearScreen();
        FunctionsManager.start();
    }

    // Newton's Root Finding Method
    public static void newtonMethod() {
        Differentiation diff = new Differentiation();
        String functionInput = diff.promptFunctionInput("Enter a function (e.g., x^2 - 4): ");
        diff.functionInput = functionInput; // Set functionInput for this instance

        double x = getValidDouble("Enter initial guess: ");
        System.out.print("Enter the number of iterations: ");
        int iterations = scanner.nextInt();
        double fX;
        double fPrimeX;
        System.out.println("");

        try {
            for (int i = 0; i < iterations; i++) {
                fX = diff.subIn(functionInput, x); // Compute f(x)
                fPrimeX = diff.centralDifference(x, 1e-5); // Use central difference for f'(x)

                if (Math.abs(fPrimeX) < 1e-10) {
                    System.out.println("Derivative is too close to zero. Method fails.");
                    return;
                }

                x = x - (fX / fPrimeX); // Newton's method update
                System.out.printf("Iteration %d: x = %.6f\t\t f(x) = %.6f\n", i + 1, x, fX);

                if (fX == 0) {
                    System.out.printf("\nThe root has been found at iteration %d", i);
                    i = iterations;
                }
            }
            System.out.printf("\nThe root is approximately x = %.6f\n", x);
        } catch(IllegalStateException | ArithmeticException e) {
            System.out.println("An error occurred during the algorithm, please ensure the function is valid");
        }
        scanner.nextLine();
        scanner.nextLine();
        MenuManager.clearScreen();
        FunctionsManager.start();
    }



    // Test Differentiation Implementation
    public static void numericDifferentiation() {
        Differentiation diff = new Differentiation();

        // Prompt user for function input and range
        String functionInput = diff.promptFunctionInput("Enter a function (e.g., x^2 + 3x): ");
        double startRange = diff.promptStartRange();
        double endRange = diff.promptEndRange(startRange);
        double stepSize = diff.greaterThan0("Enter step size: ");

        // Set values
        try {
            diff.functionInput = functionInput;
            diff.startRange = startRange;
            diff.endRange = endRange;
            diff.stepSize = stepSize;

            // Display numerical differentiation table
            diff.differentiationTable();
        } catch(IllegalStateException e){
            System.out.println("\nIllegal input, please ensure the function is valid and the domain is valid for it's range (e.g. log(x) is undefined for x  = -1)\n");
        } catch (ArithmeticException e) {
            System.out.println("\nArithmetic error, you cannot divide by zero\n");
        } catch (Exception e) {
            System.out.println("\nAn error occurred while attempting to compute " + functionInput);
        }
        // Wait for user to hit enter then clear the screen
        scanner.nextLine();
        MenuManager.clearScreen();
        FunctionsManager.start();
    }
}
