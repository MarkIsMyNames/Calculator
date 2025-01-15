package matrices;


import menu.MenuManager;
import menu.MenuText;
import menu.Start;

// Class imports
import java.util.InputMismatchException;
import java.util.Scanner;


import static menu.Colours.*;

public class MatricesManager implements Start {

    public static void start() {
        MenuManager.clearScreen();
        Scanner scanner = new Scanner(System.in);
        boolean currentlySelecting = true;
        while (currentlySelecting) {

            try {


                MenuText.matricesText();
                // select function using a text block
                System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(CYAN + "║" + WHITE + "                     Matrix Operations Menu                     " + CYAN + "║" + RESET);
                System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (1) for Addition                                     " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (2) for Subtraction                                  " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (3) for Multiplication                               " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (4) for Transpose                                    " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_PURPLE + "    Enter (5) for Power                                        " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (6) for LU Factorisation                             " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_GREEN + "    Enter (7) for det(A)                                       " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_YELLOW + "    Enter (8) for Dominant Eigenvalues                        " + CYAN + "  ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_CYAN + "    Enter (9) to Solve Simultaneous Equations                 " + CYAN + "  ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_PURPLE + "    Enter (10) to Multiply by a Number                         " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║" + BRIGHT_BLUE + "    Enter (11) for Dimensionality Reduction with PCA          " + CYAN + "  ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "║" + BRIGHT_RED + "    Enter (0) to return to the menu                            " + CYAN + " ║" + RESET);
                System.out.println(CYAN + "║                                                                ║");
                System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);
                System.out.print(CYAN + "Enter a choice: " + RESET);



                // checking is a valid function selected
                int selectorNum = scanner.nextInt();


                // Checks which function is selected
                switch (selectorNum) {
                    case 1:
                        // Calls Addition method
                        MatrixAdder.addition();
                        break;
                    case 2:
                        // Calls Subtraction method
                        MatrixAdder.subtraction();
                        break;
                    case 3:
                        // Calls Matrix Multiplication method
                        MatrixMultiplication.multiplicationStart();
                        break;
                    case 4:
                        // Calls Transpose of a Matrix method
                        TransposeOfAMatrix.transposeStart();
                        break;
                    case 5:
                        // Calls Matrix Power calculation method
                        MatrixPower.powerCalculationStart();
                        break;
                    case 6:
                        // Calls LU Factorisation method
                        LUFactorisation.factoriser();
                        break;
                    case 7:
                        // Calls Determinant calculation method
                        Determinant.getDeterminantOfAMatrix();
                        break;
                    case 8:
                        // Calls Dominant Eigenvalue calculation method
                        EigenvalueCalculator.getDominantEigenvalue();
                        break;
                    case 9:
                        // Calls Solve Simulations Equations method
                        SolveSimulationsEquations.solveEquation();
                        break;
                    case 10:
                        // Calls MultiplyByANumber method
                        MultiplyByANumber.multiplyStart();
                        break;
                    case 11:
                        // Calls MultiplyByANumber method
                        PCA.pcaStart();
                        break;
                    case 0:
                        // Returns to menu
                        MenuManager.clearScreen();
                        MenuManager.callMenu();
                        break;
                    default:
                        System.out.println("Invalid function selected.");
                        break;
                }

            } catch(InputMismatchException e){
                System.out.println("Please enter a valid number!\n");
                scanner.nextLine(); // Clear invalid input from the scanner
            }
        }
        MenuManager.clearScreen();
        MatricesManager.start();
    }
}