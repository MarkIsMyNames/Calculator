package calculations;

import java.util.Scanner;

import menu.MenuManager;
import menu.MenuText;
import menu.Start;


public class CalculationsManager implements Start {

    public static void start()  {
        MenuManager.clearScreen();
        MenuText.calculationsText(); //Prints "Calculations"

        String calculationInput; //Initialising calculation input string
        Scanner scanner = new Scanner(System.in); //Creating instance of the scanner class
        Calculations calculations = new Calculations(); //Creating object using default constructor

        //Error handling for incorrect expressions
        boolean validInput = false;
        while(!validInput){
            try {
                System.out.print("Please enter a sum: ");
                calculationInput = scanner.nextLine();
                SyntaxChecker sc = new SyntaxChecker(calculationInput); //Creating SyntaxChecker object to check for syntax errors
                sc.validSyntax(); //Check for syntax errors
                calculations = new Calculations(calculationInput); //Updating object with the functioning constructor
                System.out.println("\n" + calculations.getOriginalInput() + " = " + calculations.getAnswer());
                validInput = true;
            } catch (IllegalArgumentException e) { //Custom exception messages as defined in SyntaxChecker, most specific exception gets caught first
                System.out.println("\n " + e.getMessage() + "\n");
                scanner.nextLine();
                MenuManager.clearScreen();
                CalculationsManager.start();
            } catch (ArithmeticException e) {
                System.out.println("\nInvalid sum: Division by zero is not allowed!\n");
                scanner.nextLine();
                MenuManager.clearScreen();
                CalculationsManager.start();
            } catch (IllegalStateException e) {
                System.out.println("\nInvalid sum: Improper expression format!\n");
                scanner.nextLine();
                MenuManager.clearScreen();
                CalculationsManager.start();
            } catch (Exception e) {
                System.out.println("\nInvalid sum!\n"); //Message for default exception
                scanner.nextLine();
                MenuManager.clearScreen();
                CalculationsManager.start();
            }
        }
        scanner.nextLine(); //Wait for user to hit enter before clearing screen
        MenuManager.clearScreen();
        MenuManager.callMenu();
    }
}
