package matrices;


public interface CreatingMatrixInterface {

    public abstract double[][] createMatrixAndIncludeSize();

    public abstract double[][] createMatrixNoSize(int noOfRowsInMatrixA, int noOfColumnsMatrixA);

    public abstract char YorN(double[][]matrixToBeChecked);

    public abstract double getNumberInMatrix(String rowListWithSpace, int positionCounter);

    public abstract void matrixChecker(double[][] matrix);
}
