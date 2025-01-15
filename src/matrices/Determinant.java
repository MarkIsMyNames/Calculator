package matrices;

import menu.MenuManager;

import java.util.Scanner;

public class Determinant extends LUFactorisation{

    // Function to compute the determinant using LU decomposition
    protected static double computeDeterminant(double[][] inputMatrix) {
        int matrixSize = inputMatrix.length;  // Get the size of the matrix (n x n)

        // Create the lower and upper triangular matrices to store the LU decomposition result
        double[][] lowerMatrix = new double[matrixSize][matrixSize];
        double[][] upperMatrix = new double[matrixSize][matrixSize];


        // Perform LU factorisation
        if (LUFactorisation.luFactorisation(inputMatrix, lowerMatrix, upperMatrix)) {

            // The total determinant is the product of the diagonal elements of the upper matrix
            double determinant = 1.0;

            // Cycle through the diagonal elements of the matrix
            for (int diagonalIndex = 0; diagonalIndex < matrixSize; diagonalIndex++) {

                determinant *= upperMatrix[diagonalIndex][diagonalIndex];  // Multiply diagonal elements of the upper triangular matrix
            }
            return determinant;  // Return the computed determinant
        }

        // If LU decomposition fails, return 0 (determinant of singular matrix)
        return 0.0;
    }


    protected static void getDeterminantOfAMatrix(){
        // Create a Square matrix
        Matrix squareMatrix = new CreateSquareMatrix();
        double[][] inputMatrix = squareMatrix.createMatrixAndIncludeSize();

        // Compute and print the determinant using LU decomposition
        double determinant = computeDeterminant(inputMatrix);
        System.out.println("Determinant of the matrix: " + determinant);

        //Wait for input
        Scanner input = new Scanner(System.in);
        input.nextLine();
        MenuManager.clearScreen();
    }
}