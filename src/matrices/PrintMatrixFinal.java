package matrices;

import menu.MenuManager;

import java.util.Scanner;

public interface PrintMatrixFinal extends PrintMatrixSuper {
    public static void printFinal(double[][] matrixToPrint){

        System.out.println("\n\nYour Final Matrix: ");
        PrintMatrixSuper.printMatrix(matrixToPrint);
        Scanner scanner = new Scanner(System.in);

        //To Return to the main menu
        scanner.nextLine();
        MenuManager.clearScreen();
        MatricesManager.start();
    }
}
