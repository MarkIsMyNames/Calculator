package matrices;

public class TransposeOfAMatrix implements PrintMatrixFinal {

    protected static void transposeStart(){
        Matrix matrixFirstCreator = new CreateFirstMatrix();
        double[][]matrixA = matrixFirstCreator.createMatrixAndIncludeSize();

        double[][] matrixFinal = transpose(matrixA);

        PrintMatrixFinal.printFinal(matrixFinal);
    }
    protected static double[][] transpose(double[][]matrixA){

        //Get the number of rows for matrix
        int rowsTransMatrixA = matrixA.length;

        // Get the number of columns
        int columnsTransMatrixA = matrixA[0].length;

        //Create a matrix of with row = coloms and colums equal to rows from MatrixA
        double[][]matrixFinal = new double[columnsTransMatrixA][rowsTransMatrixA];

        //Cycle through each row
        for(int matrixRowAdder = 0; matrixRowAdder< rowsTransMatrixA; matrixRowAdder++){

            //Cycle through each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < columnsTransMatrixA; matrixColoumAdder++){

                matrixFinal[matrixColoumAdder][matrixRowAdder] = matrixA[matrixRowAdder][matrixColoumAdder];
            }
        }
        return matrixFinal;
    }
}
