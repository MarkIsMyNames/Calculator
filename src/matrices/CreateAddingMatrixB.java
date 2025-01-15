package matrices;

public class CreateAddingMatrixB extends Matrix {

    public double[][] createMatrixAndIncludeSize() {

        // Call the super method to create the matrix with the new inputted size
        double[][] matrixFirst = super.createMatrixNoSize(getNoOfRowsInMatrixA(),getNoOfColumnsMatrixA());


        //Used for ensuring correct matrix
        matrixChecker(matrixFirst);

        return getMatrixOne();
    }

    @Override
    public void matrixChecker(double[][] matrixFirst) {
        do {
            setChecker(super.YorN(matrixFirst));

            if(getChecker() == 'N' || getChecker() == 'n') {
                createMatrixAndIncludeSize();
            }
        }while (getChecker() == 'N' || getChecker() == 'n');
    }
}
