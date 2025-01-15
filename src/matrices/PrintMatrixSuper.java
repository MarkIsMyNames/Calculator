package matrices;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public interface PrintMatrixSuper {

    public static void printMatrix(double[][] matrixToPrint) {

        //Print the out the matrix
        for (double[] row : matrixToPrint){

            for(int colomnCounter = 0; colomnCounter < matrixToPrint[0].length; colomnCounter++){

                double numberToRound = row[colomnCounter];
                // Rounds the number
                BigDecimal numberToRoundBigDecimal = BigDecimal.valueOf(numberToRound);


                // Determine the number of digits before the decimal point
                // it uses Math.log10 then adds 1 (log 10 counts digits around the first decimal place e.g 20 would be 1 and 0.2 would be -1)
                // Math.abs to make it positive
                int digits = (int) Math.floor(Math.log10(Math.abs(numberToRound))) + 1;

                // Calculate the scale to round to
                int scale = 14 - digits;

                // Create a BigDecimal object to handle rounding
                BigDecimal roundedNumber  = numberToRoundBigDecimal.setScale(scale, RoundingMode.HALF_UP);// Rounds to 14 significant figures
                row[colomnCounter] = roundedNumber.doubleValue();



            }

            //prints each row
            System.out.println(Arrays.toString(row));
        }
    }
}