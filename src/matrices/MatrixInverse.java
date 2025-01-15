package matrices;

public class MatrixInverse {


    // Function to calculate the inverse of a matrix using Gaussian elimination
    protected static boolean calculateInverse(double[][] inputMatrix, double[][] inverseMatrix) {
        // Get the size of the matrix (n x n)
        int matrixSize = inputMatrix.length; //.lenght here finds the number of rows

        // Create an augmented matrix [inputMatrix | identityMatrix]
        // The augmented matrix will have 2 * n columns: the original matrix + the identity matrix
        double[][] augmentedMatrix = new double[matrixSize][2 * matrixSize];

        // Step 1: Construct the augmented matrix [inputMatrix | identityMatrix]
        /*  | 9 8 3     1 0 0 |
            | 7 2 1     0 1 0 |
            | 3 4 4     0 0 1 |*/
        // cycles through each row
        for (int row = 0; row < matrixSize; row++) {
            //cycles throught each colunm
            for (int col = 0; col < matrixSize; col++) {


                augmentedMatrix[row][col] = inputMatrix[row][col];  // Copy input matrix to the left part of the augmented matrix
                augmentedMatrix[row][col + matrixSize] = (row == col) ? 1 : 0;  // Identity matrix on the right part
            }
        }

        // Step 2: Apply Gaussian elimination to make the left part of the augmented matrix the identity matrix
        // and the right part the inverse of the original matrix (if it's invertible).
        /*  | 1 0 0             |
            | 0 1 0     A^-1    |
            | 0 0 1             |
         */
        for (int row = 0; row < matrixSize; row++) {
            // If the diagonal element (pivot element) is 0, the matrix is singular (not invertible).
            if (augmentedMatrix[row][row] == 0) {
                return false;  // Matrix is singular, return false.
            }

            // Step 3: Normalize the pivot row to make the diagonal element 1.
            // This is done by dividing the entire row by the pivot element.
            double pivotValue = augmentedMatrix[row][row];

            for (int col = 0; col < 2 * matrixSize; col++) {
                augmentedMatrix[row][col] /= pivotValue;  // Normalize the pivot row.
            }

            // Step 4: Eliminate the elements below the pivot (make them 0) for the current column.
            double eliminationFactor;
            for (int belowRow = row + 1; belowRow < matrixSize; belowRow++) {
                // Calculate the factor to eliminate the element below the pivot.
                eliminationFactor = augmentedMatrix[belowRow][row];
                for (int col = 0; col < 2 * matrixSize; col++) {
                    augmentedMatrix[belowRow][col] -= eliminationFactor * augmentedMatrix[row][col];
                }
            }
        }

        // Step 5: Perform back substitution to eliminate the elements above the pivots, making the matrix in reduced row echelon form.
        // This will make the upper triangle of the matrix zero, leaving the left part as the identity matrix.
        for (int row = matrixSize - 1; row >= 0; row--) {
            // Eliminate the elements above the pivot (make them 0) for the current column.
            for (int aboveRow = row - 1; aboveRow >= 0; aboveRow--) {
                // Calculate the factor to eliminate the element above the pivot.
                double eliminationFactor = augmentedMatrix[aboveRow][row];
                for (int col = 0; col < 2 * matrixSize; col++) {
                    augmentedMatrix[aboveRow][col] -= eliminationFactor * augmentedMatrix[row][col];
                }
            }
        }

        // Step 6: After the Gaussian elimination process, the augmented matrix is in the form:
        // [ identityMatrix | inverseMatrix ].
        // At this point, the left part of the augmented matrix has been transformed into the identity matrix,
        // and the right part now contains the inverse of the original matrix.
        // Extract the right part (inverse matrix) and copy it into the 'inverseMatrix' array.
        for (int row = 0; row < matrixSize; row++) {
            // For each row in the augmented matrix, Copy the elements from the right part (which is the inverse matrix)
            // into the corresponding row of the 'inverseMatrix'.
            // The right part starts from index 'matrixSize' in each row, so we copy from that position to the beginning of the inverseMatrix row.
            // System.arraycopy copies a specific range of elements from one array to another
            System.arraycopy(augmentedMatrix[row], matrixSize, inverseMatrix[row], 0, matrixSize);
        }


        return true;  // The matrix was successfully inverted
    }
}
