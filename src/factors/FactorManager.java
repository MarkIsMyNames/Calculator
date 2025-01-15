package factors;

import menu.MenuManager;
import menu.MenuText;
import menu.Start;
import processor.NumberFormatter;

import java.util.InputMismatchException;
import java.util.Scanner;

import static menu.Colours.*;

public class FactorManager implements Start {

    public static Scanner scanner = new Scanner(System.in);

    public static void start() {

        MenuManager.clearScreen();

        boolean currentlySelecting = true;
        while (currentlySelecting) {
            try {

                MenuText.factorsText(); //Show ASCII art text for factors
                // Prompt user to select a choice for functions
                System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "║" + WHITE + "                 Welcome to Factors & Primes                    " + CYAN + "║" + RESET);
                System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to find the factors of a number                  " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to find prime numbers                            " + CYAN + " ║" + RESET);
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
                        FactorManager.factorPrompt();
                        break;
                    }
                    case 2 -> {
                        currentlySelecting = false;
                        FactorManager.primePrompt();
                        break;
                    }
                    case 0 -> {
                        currentlySelecting = false;
                        MenuManager.clearScreen();
                        MenuManager.callMenu();
                        break;
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }
    }

    public static void primePrompt() {
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please enter the nth prime number you would like to find: ");

            try {
                String number = scanner.nextLine(); // Read input
                number = NumberFormatter.removeCommas(number);
                int n = Integer.parseInt(number);
                if (n > 0 && n <= 50000000) {
                    validInput = true; // Valid input, proceed
                    PrimeFinder primeFinder = new PrimeFinder(n);
                    primeFinder.findNthPrime();
                    System.out.println("\nThe " + NumberFormatter.nthNumber(NumberFormatter.addCommas(String.valueOf(n))) + " prime number is: " + NumberFormatter.addCommas(String.valueOf(primeFinder.getNthPrime())) + "\n");
                    scanner.nextLine();
                } else {
                    System.out.println("Please enter a positive integer between 1 and 50,000,000\n");
                    scanner.nextLine();
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input from the buffer
            }
        }

        // Call the menu after user hits enter
        MenuManager.clearScreen();
        FactorManager.start();
    }



    public static void factorPrompt() {

        factors.Factors f = new factors.Factors();
        long n = 0;

        boolean validInput = false;
        while(!validInput) {
            try {
                System.out.print("Please enter a number: ");
                String num = scanner.nextLine();
                num = NumberFormatter.removeCommas(num);
                n = Long.parseLong(num);
                f = new factors.Factors(n);
                validInput = true;

            } catch (Exception e) {
                System.out.println("\nPlease enter a number between 1 and 9,223,372,036,854,775,807\n");
                scanner.nextLine();
            }
        }

        if(f.factorNum() == 0){
            System.out.println(n + " is a prime number!");
        }
        else{System.out.println(f.getFactors());}

        //Call the menu after user hits enter
        scanner.nextLine();
        MenuManager.clearScreen();
        FactorManager.start();
    }

}
