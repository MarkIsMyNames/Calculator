package matrices;

public class CreateMultiplyingMatrixB extends Matrix {

    private static int noOfColumnsInMatrixInB;

    protected static int getNoOfColumnsInMatrixInB() {
        return noOfColumnsInMatrixInB;
    }

    protected static void setNoOfColumnsInMatrixInB(int noOfColumnsInMatrixInB) {
        if(noOfColumnsInMatrixInB > 0) {
            CreateMultiplyingMatrixB.noOfColumnsInMatrixInB = noOfColumnsInMatrixInB;
        }else{
            System.err.print("Error please enter a number between 1-2147483647: ");
            throw new IllegalArgumentException();
        }
    }

    private static int noOfRowsInMatrixInB;

    protected static int getNoOfRowsInMatrixInB() {
        return noOfRowsInMatrixInB;
    }

    protected static void setNoOfRowsInMatrixInB(int noOfRowsInMatrixInB) {
        if(noOfRowsInMatrixInB > 0) {
            CreateMultiplyingMatrixB.noOfRowsInMatrixInB = noOfRowsInMatrixInB;
        }else{
            System.err.print("Error please enter a number between 1-2147483647: ");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double[][] createMatrixAndIncludeSize() {

        //input no of rows
        System.out.print("Enter Number of Rows: ");

        // checks if the number of rows is valid
        boolean check = true;
        while(check) {
            try {
                setNoOfRowsInMatrixInB(intSizeInt());
                if(getNoOfRowsInMatrixInB() != getNoOfColumnsMatrixA()){
                    System.err.println("Sorry your matrix must have the same number of rows as matrix A has columns: " + getNoOfColumnsMatrixA());
                    throw new IllegalArgumentException();
                }
                check = false;
            } catch (IllegalArgumentException ignored) {

            }
        }

        //input no of rows
        System.out.print("Enter Number of Columns: ");

        // checks if the number of colums is valid
        check = true;
        while(check) {
            try {
                setNoOfColumnsInMatrixInB(intSizeInt());
                check = false;
            } catch (IllegalArgumentException ignored) {

            }
        }

        // Call the super method to create the matrix with the new inputted size
        double[][] matrixFirst = super.createMatrixNoSize(getNoOfRowsInMatrixInB(),getNoOfColumnsInMatrixInB());


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
