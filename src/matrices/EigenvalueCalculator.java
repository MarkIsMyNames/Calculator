package matrices;

import java.util.Scanner;

import static matrices.MatrixMultiplication.multiplication;

public class EigenvalueCalculator implements PrintMatrixFinal {

    private static int noOfRowsInVector;

    private static double eigenvalue;

    protected static double getEigenvalue() {
        return eigenvalue;
    }

    protected static double[][] powerIteration(double[][] origialMatrix, int noOfIterations) {

        noOfRowsInVector = origialMatrix.length; // Size of the matrix (assuming a square matrix)

        // Initial vector (randomly chosen as all ones) (Can't be 0)
        double[][] eigenVectorMatrix = new double[noOfRowsInVector][1];   // Vector to hold the result of matrix-vector multiplication
        // Fill the first column of each row with 1
        for (int defaultEveryElementTo1 = 0; defaultEveryElementTo1 < noOfRowsInVector; defaultEveryElementTo1++) {
            eigenVectorMatrix[defaultEveryElementTo1][0] = 1;  //fills every element with 1
        }

        // Step 1: Normalize vector
        normalizeVector(eigenVectorMatrix);


        // Power iteration loop
        for (int iteration = 1; iteration <= noOfIterations; iteration++) {

            // Step 2: Multiply origial Matrix by the eigenvector to get x(n+1)
            eigenVectorMatrix = multiplication(origialMatrix, eigenVectorMatrix);

            // Step 3: normalize vector again
            normalizeVector(eigenVectorMatrix);
        }

        // Step 4: Compute the eigenvalue using the formula v^T * A * v
        // 1. Find v^T
        // 2. v^T * A
        // 3. multiply by v ( * v)
        double[][] currentEigenvalueArray = multiplication(multiplication(TransposeOfAMatrix.transpose(eigenVectorMatrix), origialMatrix), eigenVectorMatrix);
        eigenvalue = currentEigenvalueArray[0][0];

        if(Double.isNaN(eigenvalue)){
            System.err.println("The Matrix's eigenvalue is NaN.\nUnable to continue.");
            Scanner input = new Scanner(System.in);
            input.nextLine();
            MatricesManager.start();
        }

        return eigenVectorMatrix;
    }

    //this normilizes the vector
    private static void normalizeVector(double[][]eigenVectorMatrix){

        // Step 1: Compute the norm of the resulting vector (for normalization)
        double sumOfSquaresToFindNorm = 0;
        for (int matrixRow = 0; matrixRow < noOfRowsInVector; matrixRow++) {
            sumOfSquaresToFindNorm += eigenVectorMatrix[matrixRow][0] * eigenVectorMatrix[matrixRow][0]; // Sum of squares
        }
        double norm = Math.sqrt(sumOfSquaresToFindNorm); // Take square root to get the norm


        // Step 2: Normalize xn (make it a unit vector)(vn)
        for (int matrixRow = 0; matrixRow < noOfRowsInVector; matrixRow++) {
            eigenVectorMatrix[matrixRow][0] /= norm;
        }

    }

    // Main function to run the program
    protected static void getDominantEigenvalue(){

        // Define a sample matrix A (e.g., 3x3 matrix)
        Matrix createSquareMatrix = new CreateSquareMatrix();
        double[][] matrix = createSquareMatrix.createMatrixAndIncludeSize();

        // Parameters for power iteration: max iterations
        int maxIterations = 25;

        // Call the power iteration method to find the largest eigenvalue and eigenvector
        double[][]eigenVectorMatrix = EigenvalueCalculator.powerIteration(matrix, maxIterations);

        // Print statements
        System.out.println("\nThe Dominant Eigenvalue is: " + getEigenvalue() + "\nAnd it's corresponding Eigenvectors are: ");
        PrintMatrixFinal.printFinal(eigenVectorMatrix);
    }
}
