package matrices;

public class SolveSimulationsEquations extends Determinant implements PrintMatrixFinal {

    protected static void solveEquation(){
        // Create Matrix A
        Matrix squareMatrix = new CreateSquareMatrix();
        double[][] coefficientMatrix = squareMatrix.createMatrixAndIncludeSize();

        // Create solution Vector
        Matrix solutionVector = new CreateSolutionVector(coefficientMatrix);
        double[][] rightHandSide = solutionVector.createMatrixAndIncludeSize();

        // Solve the system of equations using LU factorization
        double[][] solution = solveUsingLU(coefficientMatrix, rightHandSide);

        // Print the solution vector x (values for the variables)
        PrintMatrixFinal.printFinal(solution);
    }

    // Function to solve Ax = b using LU factorization
    private static double[][] solveUsingLU(double[][] coefficientMatrix, double[][] rightHandSide) {

        // Step 1: Perform LU factorization of the coefficient matrix
        // perform LU Factorisation
        if(Determinant.computeDeterminant(coefficientMatrix) == 0){
            System.out.println("The Determinant is 0, hence there is no solution for these Simulations Equations");
            PrintMatrixFinal.printFinal(coefficientMatrix);
        }

        // Step 2: Solve Lc = b using forward substitution
        double[][] intermediateSolution = forwardSubstitution(getLowerTriangularMatrix(), rightHandSide);

        // Step 3: Solve Ux = c using backward substitution

        return backwardSubstitution(intermediateSolution);
    }

    // Forward substitution to solve Lc = b
    public static double[][] forwardSubstitution(double[][] lowerTriangularMatrix, double[][] rightHandSide) {
        int numberOfVariables = lowerTriangularMatrix.length;

        // Step 1: Initialize the current value of c (intermediate solution) with the corresponding right-hand side element b

        // Loop through each row of the lower triangular matrix L
        for (int currentRowIndex = 0; currentRowIndex < numberOfVariables; currentRowIndex++) {


            // Step 2: Subtract the contributions from previous rows (i.e., the known values in the lower triangular matrix)
            for (int previousRowIndex = 0; previousRowIndex < currentRowIndex; previousRowIndex++) {
                // Subtract the value from the previous rows where L is non-zero (this eliminates the lower triangular part)
                rightHandSide[currentRowIndex][0] -= (getLowerTriangularMatrix()[currentRowIndex][previousRowIndex] * rightHandSide[previousRowIndex][0]);
            }

            // The value of intermediateSolution[currentRowIndex] is now ready (it represents the current value of c)
            // No need for division here, as L is a lower triangular matrix with 1's on the diagonal
        }

        // Return the intermediate solution vector c
        return rightHandSide;
    }

    // Backward substitution to solve Ux = y
    public static double[][] backwardSubstitution(double[][] intermediateSolution) {

        int numberOfVariables = getUpperTriangularMatrix().length;

        double[][] solutionVectorX = new double[numberOfVariables][1];


        // Loop through the rows of the upper triangular matrix U, starting from the last row
        for (int currentRowIndex = numberOfVariables - 1; currentRowIndex >= 0; currentRowIndex--) {
            // Step 1: Start by setting the value of x at the current row as the corresponding value from the intermediate solution y
            solutionVectorX[currentRowIndex][0] = intermediateSolution[currentRowIndex][0];

            // Step 2: Subtract the contributions from the element{4, 5, 6}s in the same row but to the right of the diagonal
            // These contributions are already calculated in the previous rows of the solution vector x
            for (int nextColumnIndex = currentRowIndex + 1; nextColumnIndex < numberOfVariables; nextColumnIndex++) {
                // Subtract the term involving the known x values from previous rows (U * x)
                solutionVectorX[currentRowIndex][0] -= getUpperTriangularMatrix()[currentRowIndex][nextColumnIndex] * solutionVectorX[nextColumnIndex][0];
            }

            // Step 3: Normalize the current value of x by dividing by the diagonal element in U
            solutionVectorX[currentRowIndex][0] /= getUpperTriangularMatrix()[currentRowIndex][currentRowIndex];
            // The diagonal element of U is guaranteed to be non-zero (because U is upper triangular and A is non-singular)
        }

        // Return the final solution vector x
        return solutionVectorX;
    }
}
