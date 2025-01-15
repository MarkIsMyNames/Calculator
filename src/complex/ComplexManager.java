package complex;

import functions.Functions;
import menu.MenuManager;
import menu.MenuText;
import menu.Start;

import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static menu.Colours.*;

public class ComplexManager implements Start {
        public static void start() {

            MenuManager.clearScreen();
            MenuText.complexText();
            Scanner scanner = new Scanner(System.in);
            double real1 = 0;
            double imaginary1 = 0;
            double real2 = 0;
            double imaginary2 = 0;
            Complex num1 = new Complex();
            Complex num2 = new Complex();


            try {
                System.out.print(RESET + "Enter the real and imaginary parts of the first complex number:");
                real1 = scanner.nextDouble();
                imaginary1 = scanner.nextDouble();
                num1 = new Complex(real1, imaginary1);

                System.out.print(RESET + "Enter the real and imaginary parts of the second complex number:");
                real2 = scanner.nextDouble();
                imaginary2 = scanner.nextDouble();
                num2 = new Complex(real2, imaginary2);
                System.out.println("");
            } catch (IllegalStateException | IllegalArgumentException | InputMismatchException e){
                System.out.println("Please enter valid double values\n");

            }

            System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║" + WHITE + "             Welcome to Complex Number Operations               " + CYAN + "║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) to Add                                            " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) to Subtract                                       " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) to Multiply                                       " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (4) to Divide                                         " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_PURPLE + "    Enter (5) for Polar Form of First Complex Number            " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (6) for Polar Form of Second Complex Number           " + CYAN + "║" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to return to the menu                            " + CYAN + " ║" + RESET);
            System.out.println(CYAN + "║                                                                ║");
            System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print(RESET + "Enter a choice: " + RESET);

            int choice = scanner.nextInt();
            System.out.println("");

            Complex result;
            switch (choice) {
                case 1: // Addition
                    result = num1.add(num2);
                    System.out.println("Result: " + result);
                    break;
                case 2: // Subtraction
                    result = num1.subtract(num2);
                    System.out.println("Result: " + result);
                    break;
                case 3: // Multiplication
                    result = num1.multiply(num2);
                    System.out.println("Result: " + result);
                    break;
                case 4: // Division
                    try {
                        result = num1.divide(num2);
                        System.out.println("Result: " + result);
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5: // Polar form of the first complex number
                    System.out.println(num1.toPolarForm());
                    break;
                case 6: // Polar form of the second complex number
                    System.out.println(num2.toPolarForm());
                    break;
                case 0: //Return to the menu
                    MenuManager.clearScreen();
                    MenuManager.callMenu();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            scanner.nextLine();
            scanner.nextLine();
            MenuManager.clearScreen();
            MenuManager.callMenu();
        }
    }

