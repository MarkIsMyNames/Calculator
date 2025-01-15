package matrices;

public class CreateFirstMatrix extends Matrix {


    @Override
    public double[][] createMatrixAndIncludeSize() {

        //input no of rows
        System.out.print("Enter Number of Rows: ");

        // checks if the number of rows is valid
        boolean check = true;
        while(check){
            try{
                setNoOfRowsInMatrixA(intSizeInt());
                check = false;
            }catch(IllegalArgumentException ignored){

            }
        }

        //input no of columns
        System.out.print("Enter Number of Columns: ");

        // checks if the number of columns is valid
        check = true;
        while(check){
            try{
                setNoOfColumnsMatrixA(intSizeInt());
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
