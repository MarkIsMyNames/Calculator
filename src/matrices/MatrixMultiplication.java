package matrices;

public class MatrixMultiplication implements PrintMatrixFinal {

    protected static void multiplicationStart(){
        //Calls matrixFirstCreator and sets matrixA to answer
        //Initilize matrices
        //creates a 2D matrix
        Matrix createFirstMatrix = new CreateFirstMatrix();
        double[][] matrixA = createFirstMatrix.createMatrixAndIncludeSize();

        //Calls multiplyingMatrixB and sets matrixB to answer
        Matrix createMultiplyingMatrixB = new CreateMultiplyingMatrixB();
        double[][] matrixB = createMultiplyingMatrixB.createMatrixAndIncludeSize();

        double[][]matrixFinal = multiplication(matrixA,matrixB);

        PrintMatrixFinal.printFinal(matrixFinal);
    }

    //Method for multiplying matrixes
    protected static double[][] multiplication(double[][] matrixA, double[][] matrixB){

        //Get the number of rows for matrix
        int rowsMultMatrixA =  matrixA.length;

        // Get the number of columns
        int columnsMultMatrixA = matrixA[0].length;
        int columnsMultMatrixB = matrixB[0].length;

        // Create final Matrix
        double[][]matrixFinal = new double[rowsMultMatrixA][columnsMultMatrixB];

        // Perform matrix multiplication
        for (int rowMatrixA = 0; rowMatrixA < rowsMultMatrixA; rowMatrixA++) {  // Loop over each row of matrix A

            for (int colMatrixB = 0; colMatrixB < columnsMultMatrixB; colMatrixB++) {  // Loop over each column of matrix B

                // Multiply corresponding elements from the row of matrix A and the column of matrix B
                // The loop variable "columnMatrixA" refers to the shared dimension (columns of A, rows of B)
                for (int columnMatrixA = 0; columnMatrixA < columnsMultMatrixA; columnMatrixA++) { // Iterate over the columns of A (or rows of B)

                    // Multiply the element from matrix A (at rowMatrixA, columnMatrixA)
                    // with the element from matrix B (at columnMatrixA, colMatrixB)
                    // Then add them to the existing value in the matrixFinal
                    matrixFinal[rowMatrixA][colMatrixB] += matrixA[rowMatrixA][columnMatrixA] * matrixB[columnMatrixA][colMatrixB];
                }
            }
        }
        return matrixFinal;
    }
}
