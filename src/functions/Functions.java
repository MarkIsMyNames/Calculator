package functions;

//import algebra.Algebra;
import calculations.Calculations;
import functions.FunctionsManager.*;
import menu.MenuManager;
import processor.InputProcessor;

import java.util.*;
import java.util.stream.Collectors;
import static functions.FunctionsManager.substitutedExpression;
import static functions.FunctionsManager.substitution;


public class Functions extends FunctionTemplate {
    static Scanner scanner = new Scanner(System.in);

    public Functions(){}

    // Constructor taking input, range, step size
    public Functions(String functionInput, double startRange, double endRange, double stepSize){
        super();
    }

    // Helper method to validate double input
    protected static double getValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine()); // Parse user input as a double
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public String promptFunctionInput(String message){
        System.out.print(message);
        functionInput = scanner.nextLine();
        return functionInput;
    }

    public double promptStartRange(){
        startRange = getValidDouble("Enter the start of the range: ");
        return startRange;
    }

    public double promptEndRange(double startRange){
        double endRange = getValidDouble("Enter the end of the range: ");
        while (endRange < startRange) {
            System.out.printf("End of range must be greater than %.2f.\n", startRange);
            endRange = getValidDouble("Enter the end of the range: ");
        }
        return endRange;
    }

    public double greaterThan0(String message){
        // Error handling for step size
        double greaterThan0;
        while (true) {
            Functions f = new Functions();
            greaterThan0 = getValidDouble(message);
            if (greaterThan0 > 0) {
                break; // valid double, exit the loop
            } else {
                System.out.println("Must be greater than 0. Please enter a valid number.");
            }
        }
        return greaterThan0;
    }

    // method to sub a variable into a function
    protected static double subIn(String function, double variable) {
        String substitution = "(" + variable + ")";
        String substitutedExpression = function.replaceAll("x", substitution);
        Calculations calculations = new Calculations(substitutedExpression);
        return calculations.getAnswer();
    }

    public double getStartRange(){
        return startRange;
    }

    public double getEndRange(){
        return endRange;
    }

    public double getStepSize(){
        return stepSize;
    }

    public String getFunctionInput(){
        return functionInput;
    }

    public void setStartRange(double startRange){
        this.startRange = startRange;
    }

    public void setEndRange(double endRange){
        this.endRange = endRange;
    }

    public void setStepSize(double stepSize){
        this.stepSize = stepSize;
    }

    public static void singleVariableFunction() {

        Functions f = new Functions();
        String functionInput = f.promptFunctionInput("Enter a function (e.g. x^2 + 3x + 4): ");

        double startRange = f.promptStartRange();
        double endRange = f.promptEndRange(startRange);
        double stepSize = f.greaterThan0("Enter step size: ");

        try {
            // Header of the table with enhanced style
            System.out.println("╔══════════════════════╦══════════════════════╗");
            System.out.printf("║ %-20s ║ %-20s ║\n", "x", "f(x)");
            System.out.println("╠══════════════════════╬══════════════════════╣");

            // Subbing into the function and printing each row
            for (double i = startRange; i <= endRange; i += stepSize) {
                double result = subIn(functionInput, i);
                System.out.printf("║ %-20.10f ║ %-20.10f ║\n", i, result);
            }

            // Closing the table
            System.out.println("╚══════════════════════╩══════════════════════╝");
        }
        catch(IllegalStateException e){
            System.out.println("\nIllegal input, please ensure the function is valid and the domain is valid for it's range (e.g. log(x) is undefined for x  = -1)\n");
        } catch (ArithmeticException e) {
            System.out.println("\nArithmetic error, you cannot divide by zero\n");
        } catch (Exception e) {
            System.out.println("\nAn error occured while attempting to compute " + functionInput);
        }
        System.out.println();
        scanner.nextLine();
    }


    public static void multiVariateFunction() {
        //Initialising variable
        Functions f = new Functions();
        String functionInput = "";
        //Using a set here to prevent duplicates and to utilise Collections methods
        Set<String> variables = new HashSet<>();

        try {

            functionInput = f.promptFunctionInput("Enter a function (e.g. a^2 + 2b + c): ");

            // Check if characters are part of the Alphabet enum
            for (int i = 0; i < functionInput.length(); i++) {
                char currentChar = functionInput.charAt(i);
                if (Alphabet.contains(currentChar)) {
                    variables.add(String.valueOf(currentChar));
                }
            }

            // subbing into function
            substitutedExpression = functionInput;
            for (String variable : variables) {
                System.out.printf("%s = ", variable);
                double value = scanner.nextDouble();
                substitution = "(" + value + ")";
                substitutedExpression = substitutedExpression.replaceAll(variable, substitution);
            }

            Calculations calculations = new Calculations(substitutedExpression);
            String variablesInputted = "";
            for (String uniqueVariable : variables) {
                variablesInputted += uniqueVariable;
            }

            System.out.println(variablesInputted);
            System.out.printf("f(%s) = %.2f\n", variablesInputted, calculations.getAnswer());
        } catch (IllegalStateException e){
            System.out.println("\nInvalid Input: " + functionInput + "\n");
            System.out.println("\nPlease ensure the function is valid and the domain is valid for it's range\n");
            scanner.nextLine();
        } catch (ArithmeticException e) {
            System.out.println("\nArithmetic error, you cannot divide by zero\n");
            scanner.nextLine();
        }
        System.out.println();
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void composeFunctions() {
        Functions f = new Functions();
        String fX = f.promptFunctionInput("\nEnter f(x) (e.g. 2x + 5): "); // f(x)
        String gX = f.promptFunctionInput("\nEnter g(x) (e.g. 3x + 11): "); // g(x)
        double result = 0;

        double x = getValidDouble("\nEnter a value for x: ");

        try {
            // finding g(variable)
            double gVariable = subIn(gX, x);

            // finding f(g(x))
            result = subIn(fX, gVariable);
        } catch (IllegalStateException e){
            System.out.println("\nIllegal input, please ensure the function is valid and the domain is valid for it's range (e.g. log(x) is undefined for x  = -1)\n");
        } catch (ArithmeticException e){
            System.out.println("\nArithmetic error, you cannot divide by zero\n");
        }

        System.out.print("\nf(g(x)) = ");
        System.out.println(result);
        scanner.nextLine();
    }

    public static void bisectionMethod() {
        Functions f = new Functions();

        String functionInput;
        double a = 0; // a, factorPrompt of range
        double b = 0; // b, end of range
        double fA; // f(a)
        double fB; // f(b)

        try {
            while (true) {
                functionInput = f.promptFunctionInput("Enter a function (e.g. x^2 -4): "); // gets function
                a = f.promptStartRange();
                b = f.promptEndRange(a);

                fA = subIn(functionInput, a);
                fB = subIn(functionInput, b);

                if ((fA * fB) < 0) {
                    break;
                } else {
                    System.out.println("There is no root in this interval.");
                }
            }

            double tolerance = f.greaterThan0("Enter tolerance: ");

            double c;
            double fC;
            while ((Math.abs(b - a)) / 2 > tolerance) {
                c = (a + b) / 2;
                fC = subIn(functionInput, c);
                if (fC == 0) {
                    break;
                }
                fA = subIn(functionInput, a);
                if ((fA * fC) < 0) {
                    b = c;
                } else {
                    a = c;
                }
            }
        } catch(IllegalStateException | ArithmeticException e){
            System.out.println("An error occurred during the algorithm, please ensure the function is valid");
            scanner.nextLine();
            return;
        }

        double root = (a+b) /2;
        System.out.printf("The root is approximately %.2f\n", root);
        scanner.nextLine();
    }

    public static void secantMethod() {
        Functions f = new Functions();
        String functionInput = f.promptFunctionInput("Enter a function (e.g. x^2 -4): "); // enter f(x)

        double x0 = getValidDouble("Enter first guess, x0: ");
        double x1 = getValidDouble("Enter second guess, x1: ");
        double x2 = 0;
        double tolerance = f.greaterThan0("Enter tolerance: ");
        double fX0 = 0;
        double fX1 = 0;

        System.out.println("");

        try {
            int counter = 1;
            while (true) {
                fX0 = subIn(functionInput, x0);
                fX1 = subIn(functionInput, x1);

                x2 = x1 - (fX1 * (x1 - x0) / (fX1 - fX0));
                if (Math.abs((x2 - x1)) < tolerance) {
                    break; // within tolerance, exit loop
                }
                x0 = x1;
                x1 = x2;

                counter += 1;
                System.out.printf("Iteration %d: x = %.6f\t\t f(x) = %.6f\n", counter, x2, subIn(functionInput, x2));
            }
            System.out.printf("\nThe root is approximately %.2f\n", x2);
            scanner.nextLine();
        } catch (IllegalStateException | ArithmeticException e){
            System.out.println("An error occurred during the algorithm, please ensure the function is valid");
            scanner.nextLine();
        }
    }

    @Override //The toString method can be used for debugging e.g. print(f.toString)
    public String toString(){
        return String.format("Function: %s %nStart Range: %f %nEnd Range: %f %nStep Size: %f %n", getFunctionInput(), getStartRange(), getEndRange(), getStepSize());
    }

}
