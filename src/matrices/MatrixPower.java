package matrices;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixPower extends MatrixMultiplication implements PrintMatrixFinal {

    // Function to perform matrix exponentiation using the Exponentiation by Squaring method
    protected static double[][] matrixExponentiation(double[][] inputMatrix, int exponent) {

        int matrixSize = inputMatrix.length;  // Size of the square matrix (assuming matrix is square)

        // Step 1: Initialize the result matrix as the same size as the input matrix
        double [][] resultMatrix = new double[matrixSize][matrixSize];

        // Step 2: check if exponent is zero and if it is return the Identity matrix
        if(exponent == 0) { // checks if the exponent is 0

            for (int diagonalOfIdentityMatrix = 0; diagonalOfIdentityMatrix < matrixSize; diagonalOfIdentityMatrix++) {

                resultMatrix[diagonalOfIdentityMatrix][diagonalOfIdentityMatrix] = 1.0;  // Identity matrix: 1s on the diagonal

            }
            return resultMatrix; // returns identity matrix
        }

        // Step 3: Initialize the result matrix as the input Matrix
        resultMatrix = inputMatrix;

        // Step 4: Check if the exponent is less than zero and if so find the inverse of the matrix
        if(exponent < 0){
            // Call the calculateInverse function to compute the inverse matrix
            if (MatrixInverse.calculateInverse(inputMatrix, resultMatrix)) {

                // If the inverse was successfully calculated, print the inverse matrix
                exponent = Math.abs(exponent);

            } else {
                // If the matrix is not invertible (singular), print a message indicating this
                System.out.println("This matrix is not invertible.");
                return inputMatrix;
            }
        }

        // Step 3a: Reduce the exponent by 1
        exponent--;

        // Step 4: Set the base matrix for exponentiation
        double[][] currentBase = inputMatrix; // Start with the given matrix as the base for exponentiation

        // Step 5: Exponentiation by Squaring
        while (exponent > 0) {
            if (exponent % 2 == 1) {  // If the exponent is odd
                resultMatrix = multiplication(resultMatrix, currentBase);  // Multiply result by current base
            }

            // Square the current base matrix (this effectively reduces the exponent)
            currentBase = multiplication(currentBase, currentBase);

            // Halve the exponent
            exponent /= 2;  // Integer division by 2
        }

        return resultMatrix;  // Return the final result matrix after exponentiation
    }


    // Main method to test matrix exponentiation
    protected static void powerCalculationStart(){

        // To get a matrix to the power the matrix must be square
        Matrix squareMatrix = new CreateSquareMatrix();
        double[][] matrix = squareMatrix.createMatrixAndIncludeSize();

        // Define the exponent to which the matrix will be raised
        int exponent = getExponent();

        // Compute the matrix raised to the power of the given exponent
        double[][] result = matrixExponentiation(matrix, exponent);

        // Output the result matrix=
        PrintMatrixFinal.printFinal(result);
    }

    private static int getExponent() {
        System.out.print("Enter Exponent: ");

        Scanner input = new Scanner(System.in);

        //Ensures exponent is an int

        try{
            return input.nextInt();

        }catch (InputMismatchException e) {
            System.out.println("Please enter a number between 1-2147483647");
            getExponent(); // loop if failure
        }

        return 0; // loop should never get to here
    }
}
