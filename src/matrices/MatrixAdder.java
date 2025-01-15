package matrices;

public class MatrixAdder implements PrintMatrixFinal{

    //Initilize matrices
    //creates a 2D matrix
    private static double[][]matrixA;
    private static double[][]matrixB;
    private static double[][]matrixFinal;


    //Used to add 2 matrixs
    protected static void addition(){

        //Calls AdditionOrSubtraction Method
        additionOrSubtraction();

        //Adder for each row
        for(int matrixRowAdder = 0; matrixRowAdder < matrixA.length; matrixRowAdder++){

            //Adder for each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < matrixA[matrixRowAdder].length; matrixColoumAdder++){

                matrixFinal[matrixRowAdder][matrixColoumAdder] = matrixA[matrixRowAdder][matrixColoumAdder] + matrixB[matrixRowAdder][matrixColoumAdder];
            }
        }
        PrintMatrixFinal.printFinal(matrixFinal);
    }

    //Used to subtract 2 matrixs
    protected static void subtraction(){

        //Calls AdditionOrSubtraction Method
        additionOrSubtraction();

        //Adder for each row
        for(int matrixRowAdder = 0; matrixRowAdder< matrixA.length; matrixRowAdder++){

            //Adder for each colum
            for(int matrixColoumAdder = 0; matrixColoumAdder < matrixA[0].length; matrixColoumAdder++){

                matrixFinal[matrixRowAdder][matrixColoumAdder] = matrixA[matrixRowAdder][matrixColoumAdder] - matrixB[matrixRowAdder][matrixColoumAdder];
            }
        }
        PrintMatrixFinal.printFinal(matrixFinal);
    }


    //Used to create the matrices for addition or subtraction
    private static void additionOrSubtraction(){

        //Calls matrixFirstCreator and sets matrixA to answer
        Matrix matrixCreatorMatrixA = new CreateFirstMatrix();
        matrixA = matrixCreatorMatrixA.createMatrixAndIncludeSize();

        // Calls matrixFirstCreator and sets matrixB to answer
        Matrix matrixCreatormatrixB = new CreateAddingMatrixB();
        matrixB = matrixCreatormatrixB.createMatrixAndIncludeSize();

        // Create final Matrix
        matrixFinal = new double[matrixA.length][matrixA[0].length];
    }
}