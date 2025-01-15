package matrices;

import menu.MenuManager;

import java.util.Scanner;

// LU = A
public class LUFactorisation implements PrintMatrixFinal {

    private static double[][] lowerTriangularMatrix;
    private static double[][] upperTriangularMatrix;

    // Getters and setters for upper and lower triangular matrices
    public static double[][] getLowerTriangularMatrix(){
        return lowerTriangularMatrix;
    }

    public static double[][] getUpperTriangularMatrix() {
        return upperTriangularMatrix;
    }

    // Method to perform LU Decomposition
    protected static boolean luFactorisation(double[][] inputMatrix, double[][] lowerTriangularMatrix, double[][] upperTriangularMatrix) {
        int matrixSize = inputMatrix.length;  // Get the size of the matrix (assuming inputMatrix is square)

        // Initialize lowerTriangularMatrix as the identity matrix (for L)
        for (int currentRow = 0; currentRow < matrixSize; currentRow++) {
            for (int currentCol = 0; currentCol < matrixSize; currentCol++) {
                if (currentRow == currentCol) {
                    lowerTriangularMatrix[currentRow][currentCol] = 1.0;  // Set diagonal elements of L to 1
                } else {
                    lowerTriangularMatrix[currentRow][currentCol] = 0.0;  // Set non-diagonal elements of L to 0
                }
                upperTriangularMatrix[currentRow][currentCol] = inputMatrix[currentRow][currentCol];  // Copy elements of inputMatrix to U
            }
        }

        // Perform the LU decomposition using Gaussian elimination
        for (int pivotRow = 0; pivotRow < matrixSize; pivotRow++) {

            // Eliminate the elements below the pivot (in column pivotRow) to update the upper triangular matrix (U)
            for (int currentRow = pivotRow + 1; currentRow < matrixSize; currentRow++) {

                // Check if the pivot element (upperTriangularMatrix[pivotRow][pivotRow]) is zero
                // If it's zero, LU decomposition cannot proceed without pivoting
                if (upperTriangularMatrix[pivotRow][pivotRow] == 0) {
                    System.out.println("Pivot element is zero, LU Factorisation cannot be performed.");
                    return false;  // Return false indicating failure
                }

                // Calculate the multiplier to eliminate the element upperTriangularMatrix[currentRow][pivotRow]
                double multiplier = upperTriangularMatrix[currentRow][pivotRow] / upperTriangularMatrix[pivotRow][pivotRow];


                lowerTriangularMatrix[currentRow][pivotRow] = multiplier;  // Store the multiplier in the corresponding L matrix element

                // Update the upperTriangularMatrix by subtracting the appropriate multiple of row pivotRow
                for (int currentCol = pivotRow; currentCol < matrixSize; currentCol++) {
                    upperTriangularMatrix[currentRow][currentCol] -= multiplier * upperTriangularMatrix[pivotRow][currentCol];  // Perform the row operation to eliminate upperTriangularMatrix[currentRow][pivotRow]
                }

            }
        }

        // Sets all elements below the diagonal of a square 2D array to zero. Prevent some minor inacuracies
        // Elements below the diagonal have row index > column index.
        // Iterate through each row of the matrix, starting from the second row (index 1)
        for (int currentRow = 1; currentRow < matrixSize; currentRow++) {
            // Iterate through the columns for the current row, up to the diagonal

            for (int currentCol = 0; currentCol < currentRow; currentCol++) {
                // Set all elements below the diagonal to zero (i.e., where row > col)
                upperTriangularMatrix[currentRow][currentCol] = 0;
            }
        }
        LUFactorisation.lowerTriangularMatrix = lowerTriangularMatrix;
        LUFactorisation.upperTriangularMatrix = upperTriangularMatrix;
        return true;  // Return true indicating successful LU decomposition
    }

    protected static void factoriser(){
        // Define the input matrix for LU Factorisation
        Matrix createSquareMatrix = new CreateSquareMatrix();
        double[][] inputMatrix = createSquareMatrix.createMatrixAndIncludeSize();

        // Find the size of it
        int matrixSize = inputMatrix.length;

        // Create matrices for lower and upper triangular matrices
        lowerTriangularMatrix = new double[matrixSize][matrixSize]; // Declare lowerTriangularMatrix as a n x n matrix for lower triangular matrix (L)
        upperTriangularMatrix = new double[matrixSize][matrixSize]; // Declare upperTriangularMatrix as a n x n matrix for upper triangular matrix (U)

        // Perform LU Factorisation
        if (luFactorisation(inputMatrix, lowerTriangularMatrix, upperTriangularMatrix)) {

            // If LU decomposition is successful, print the L and U matrices
            System.out.println("\nLower Triangular Matrix (L):");
            PrintMatrixSuper.printMatrix(lowerTriangularMatrix);

            System.out.println("\nUpper Triangular Matrix (U):");
            PrintMatrixSuper.printMatrix(upperTriangularMatrix);

            //Just to wait to hit enter
            Scanner input = new Scanner(System.in);
            input.nextLine();
            MenuManager.clearScreen();
        } else {
            System.out.println("LU Decomposition failed.");
            PrintMatrixFinal.printFinal(inputMatrix);
        }
    }
}
