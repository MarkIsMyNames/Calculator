package matrices;

public class PCA implements PrintMatrixFinal{
    protected static void pcaStart() {

        //Create Dataset
        System.out.println("Enter Dataset");
        Matrix createFirstMatrix = new CreateFirstMatrix();
        double[][] dataset = createFirstMatrix.createMatrixAndIncludeSize();

        pcaCalculations(dataset);

        printPCA();
    }

    private static double[][] mean;
    private static double[][] centredMatrix;
    private static double[][] CoverienceMatrix;
    private static double[][] varience;
    private static double[][] eigenvectors;
    private static double[][] newDataset;
    private static double[][] reconstructedDataset;

    protected static void pcaCalculations(double[][]dataset){
        //Find means
        int noOfRows = dataset.length;
        int noOfColoms = dataset[0].length;

        //Array to store means
        mean = new double[noOfColoms][1];

        //Array to store centred Matrix
        centredMatrix = new double[noOfRows][noOfColoms];

        //Find Mean
        for(int colomCounter = 0; colomCounter < noOfColoms; colomCounter++){
            double colomMean = 0;

            // Increment through each variable
            for (double[] doubles : dataset) {
                colomMean += doubles[colomCounter];
            }
            mean[colomCounter][0] = (colomMean/noOfRows);
        }

        //Find Centred Matrix
        for(int colomCounter = 0; colomCounter < noOfColoms; colomCounter++){

            //Find Centred Matrix
            for (int rowCounter = 0; rowCounter < noOfRows; rowCounter++){
                centredMatrix[rowCounter][colomCounter] = dataset[rowCounter][colomCounter] - mean[colomCounter][0];
            }
        }

        //Find Coverience Matrix
        //Find the transvers, multiplys by the centered, then multiplys by 1/ number of rows
        CoverienceMatrix = MultiplyByANumber.multiply((double) 1 /noOfRows,MatrixMultiplication.multiplication(TransposeOfAMatrix.transpose(centredMatrix), centredMatrix));

        //Find Varience
        varience = new double[noOfColoms][1];
        for(int diagonalCounter = 0; diagonalCounter < noOfColoms; diagonalCounter++){
            varience[diagonalCounter][0] = CoverienceMatrix[diagonalCounter][diagonalCounter];
        }

        //Find Eigenvectors
        eigenvectors = EigenvalueCalculator.powerIteration(CoverienceMatrix, 25);

        //Find new Dataset
        newDataset = MatrixMultiplication.multiplication(centredMatrix,eigenvectors);

        //reconstruct the origional matrix
        reconstructedDataset = MatrixMultiplication.multiplication(newDataset, TransposeOfAMatrix.transpose(eigenvectors));
    }

    //Prints out the data
    private static void printPCA(){
        System.out.println("Means: ");
        PrintMatrixSuper.printMatrix(mean);
        System.out.println();

        System.out.println("Variance: ");
        PrintMatrixSuper.printMatrix(varience);
        System.out.println();

        System.out.println("Centred Matrix: ");
        PrintMatrixSuper.printMatrix(centredMatrix);
        System.out.println();

        System.out.println("Covariance Matrix: ");
        PrintMatrixSuper.printMatrix(CoverienceMatrix);
        System.out.println();

        System.out.println("Dominant Eigenvalue: "+ EigenvalueCalculator.getEigenvalue());
        System.out.println("\nDominant Eigenvectors: ");
        PrintMatrixSuper.printMatrix(eigenvectors);
        System.out.println();

        System.out.println("New Dataset: ");
        PrintMatrixSuper.printMatrix(newDataset);
        System.out.println();

        System.out.println("Reconstructed Dataset: ");
        PrintMatrixFinal.printFinal(reconstructedDataset);
    }
}
