package matrices;


public class CreateSquareMatrix extends Matrix {

    @Override
    public double[][] createMatrixAndIncludeSize() {

        //input no of rows
        System.out.println("The Number of Rows and Columns will be identical");
        System.out.print("Enter Number of Rows and Columns (n * n): ");

        // checks if the number of rows and coloms is valid
        boolean check = true;
        while(check){
            try{
                setNoOfRowsInMatrixA(intSizeInt());

                //sets the columns as the same size as the rows
                setNoOfColumnsMatrixA(getNoOfRowsInMatrixA());
                check = false;
            }catch(IllegalArgumentException ignored){

            }
        }

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
