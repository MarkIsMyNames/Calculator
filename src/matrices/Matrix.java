package matrices;

import calculations.Calculations;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class Matrix extends Calculations implements CreatingMatrixInterface, PrintMatrixSuper {
    
    //Store matrix size
    private static int noOfRowsInMatrixA;
    private static int noOfColumnsMatrixA;

    protected static int getNoOfColumnsMatrixA() {
        return noOfColumnsMatrixA;
    }

    protected static int getNoOfRowsInMatrixA() {
        return noOfRowsInMatrixA;
    }

    protected static void setNoOfColumnsMatrixA(int noOfColumnsMatrixA) {
        if(noOfColumnsMatrixA > 0) {
            Matrix.noOfColumnsMatrixA = noOfColumnsMatrixA;
        }else{
            System.err.print("Error please enter a number between 1-2147483647: ");
            throw new IllegalArgumentException();
        }
    }

    protected void setNoOfRowsInMatrixA(int noOfRowsInMatrixA) {
        if(noOfRowsInMatrixA > 0) {
            Matrix.noOfRowsInMatrixA = noOfRowsInMatrixA;
        }else{
            System.err.print("Error please enter a number between 1-2147483647: ");
            throw new IllegalArgumentException();
        }
    }



    private static char checker;

    protected static char getChecker() {
        return checker;
    }

    protected static void setChecker(char checker) {
        Matrix.checker = checker;
    }



    private double[][] matrixOne;

    // getters and setters for the matrix
    protected double[][] getMatrixOne() {
        return matrixOne;
    }

    protected void setMatrixOne(double[][] matrixOne) {
        this.matrixOne = matrixOne;
    }



    @Override
    // input the size of the matrix (no of rows and coloms)
    public abstract double[][] createMatrixAndIncludeSize();

    @Override
    //Ensures the Matrix is correct
    public abstract void matrixChecker(double[][] matrix);

    @Override
    //Used to prevent errors e.g. someone entering 2 numbers in a row in a 3*3 matrix
    public double getNumberInMatrix(String rowListWithSpace, int positionCounter) {
        String numberRowCounterInMatrix;
        double numberInMatrix;
        // Ensures there is a number in each coloum or enters zero
        try{
            numberRowCounterInMatrix = rowListWithSpace.split(",")[positionCounter];

            numberInMatrix = super.evaluate(String.valueOf(numberRowCounterInMatrix));
            //NumberFormatException is an exception that occours when trying to convert a string to a number when string doesn't have an appropriate format
            //Prevents ArrayIndexOutOfBoundsException when trying to add something out of bounds to an array
        }catch(Exception e){
            //If no number detected it inputs 0
            numberInMatrix = 0;
        }
        return numberInMatrix;
    }

    @Override
    //Used to ensure Matrix entered is correct
    public char YorN(double[][]matrixToBeChecked){
        PrintMatrixSuper.printMatrix(matrixToBeChecked);

        System.out.print("Is matrix correct Y/N: ");

        Scanner input = new Scanner(System.in);

        return input.next().charAt(0);
    }

    // Ensures an int of size byte is inputted
    protected static int intSizeInt() {
        Scanner input = new Scanner(System.in);
        try {
            return input.nextInt();
        } catch (InputMismatchException e) {
            // This block catches InputMismatchException
            // if patterns don't match e.g. a letter or a number too big
            return 0;
        }
    }


    @Override
    // used to create a matrix
    public double[][] createMatrixNoSize(int noOfRowsInMatrixA, int noOfColumnsMatrixA) {

        //Create a 2D array or Matrix
        double[][]matrixCreated = new double[noOfRowsInMatrixA][noOfColumnsMatrixA];

        // using scanner to find the numbers
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first row ( Numbers / Sums should be separated by a \",\" ):\t");

        // iterate through the rows
        for(int rowCounter = 0; rowCounter< noOfRowsInMatrixA; rowCounter++){
            int positionCounter;

            // Input each row
            // uses regex and trim to remove tabs and multiple spaces
            String rowListWithSpace = input.nextLine().replaceAll("\\s+", " ").trim();

            // iterate through the columns in each row and adding them to the matrix
            for(positionCounter = 0; positionCounter< noOfColumnsMatrixA; positionCounter++){

                //Gets the actual number
                double numberRowCounterInMatrix = getNumberInMatrix(rowListWithSpace, positionCounter);
                matrixCreated[rowCounter][positionCounter] = numberRowCounterInMatrix;
            }

            // for printing out next row before you enter each row
            if(noOfRowsInMatrixA != (rowCounter+1)){
                System.out.print("Enter next row ( Numbers / Sums should be separated by a \",\" ):\t\t");
            }
        }
        setMatrixOne(matrixCreated);
        return getMatrixOne();
    }
}
