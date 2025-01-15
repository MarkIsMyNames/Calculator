package matrices;

public class CreateSolutionVector extends Matrix {
    private final int numberOfRows;

    CreateSolutionVector(double [][] matrix){
        numberOfRows = matrix.length;
    }

    @Override
    public double[][] createMatrixAndIncludeSize() {

        // using scanner to find the numbers
        System.out.println("Enter values for the solution vector");

        // Call the super method to create the matrix with the new inputted size
        //Create an array or Matrix
        double[][] vectorSolution = super.createMatrixNoSize(numberOfRows,1);


        //Used for ensuring correct matrix
        matrixChecker(vectorSolution);

        return getMatrixOne();
    }

    @Override
    public void matrixChecker(double[][] vectorSolution) {
        do {
            setChecker(super.YorN(vectorSolution));

            if(getChecker() == 'N' || getChecker() == 'n') {
                createMatrixAndIncludeSize();
            }
        }while (getChecker() == 'N' || getChecker() == 'n');
    }
}
