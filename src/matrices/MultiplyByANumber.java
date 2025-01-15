package matrices;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplyByANumber implements PrintMatrixFinal{
    protected static void multiplyStart() {
        System.out.print("Enter Number: ");

        double number = 0;

        //Repeat until a valid number is entered
        boolean noNumberEntered = true;
        while (noNumberEntered) {
            try {
                Scanner input = new Scanner(System.in);
                number = input.nextDouble();
                noNumberEntered = false;
            } catch (InputMismatchException e) {
                // This block catches InputMismatchException
                // if patterns don't match e.g. a letter
                System.err.print("Please enter a number: ");
            }
        }

        System.out.println("\nEnter Matrix");
        Matrix createFirstMatrix = new CreateFirstMatrix();
        double [][] matrix = createFirstMatrix.createMatrixAndIncludeSize();

        double [][]matrixFinal = multiply(number, matrix);

        PrintMatrixFinal.printFinal(matrixFinal);
    }

    protected static double[][] multiply(double number, double[][]matrix){

        int noOfRows = matrix.length;
        int noOfColoms = matrix[0].length;

        double[][]matrixFinal = new double[noOfRows][noOfColoms];

        //perform the actual multiplication
        for(int colomCounter = 0; colomCounter < noOfColoms; colomCounter++){

            for (int rowCounter = 0; rowCounter < noOfRows; rowCounter++) {
                matrixFinal[rowCounter][colomCounter] = number * matrix[rowCounter][colomCounter];
            }
        }

        return matrixFinal;
    }
}
