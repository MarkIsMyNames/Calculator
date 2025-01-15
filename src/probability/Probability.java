package probability;

import java.util.Scanner;

import calculations.Calculations;

import java.util.InputMismatchException;

public class Probability {
    public static void main(String[] args) {
        bayesTheorem();
    }

    // validates probability is a number between 0 and 1
    public static double probabilityValidator(String message) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                Calculations calculations = new Calculations(input);
                double probability = calculations.getAnswer();
                if (probability >= 0 && probability <= 1) {
                    return probability; // Valid probability
                } else {
                    System.out.println("Invalid probability.");
                }
            } catch (IllegalStateException | ArithmeticException e) {
                System.out.println("Invalid probability");
            }
        }
    }

    public static void bayesTheorem() {
        Scanner scanner = new Scanner(System.in);
        double pA = probabilityValidator("Enter P(A): ");
        double pNotA = 1 - pA;
        double pBGivenA = probabilityValidator("Enter P(B|A): ");
        double pBGivenNotA = probabilityValidator("Enter P(B|not A): ");

        // (P(B|A)*P(A)) / (P(B|A)*P(A)+P(B|not A)P(not A))
        double result = (pBGivenA * pA) / (pBGivenA * pA + pBGivenNotA * pNotA);
        System.out.printf("P(A|B)= %.4f", result);
        scanner.nextLine();
    }

    public static void independenceChecker() {
        Scanner scanner = new Scanner(System.in);
        double pA = probabilityValidator("Enter P(A): ");
        double pB = probabilityValidator("Enter P(B): ");
        double pAIntersectionB = probabilityValidator("Enter P(A intersection B): ");

        if (pA * pB == pAIntersectionB) {
            System.out.println("These events are independent.");
        } else {
            System.out.printf("These events are not independent as P(A)xP(B) = %.4f which does not equal P(A intersection B).\n", pA * pB);
        }
        scanner.nextLine();
    }

    public static void aGivenB() {
        Scanner scanner = new Scanner(System.in);
        double aIntersectionB = probabilityValidator("Enter P(A intersection B): ");
        double pB = probabilityValidator("Enter P(B): ");

        double result = aIntersectionB / pB;
        System.out.printf("P(A|B)= %.4f", result);
        scanner.nextLine();
    }
}

