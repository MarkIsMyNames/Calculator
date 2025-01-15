package factors;

import java.util.ArrayList;
import java.util.List;

public class Factors {

    private long n; // Instance variable for the number
    private List<Long> factorList; // List to store factors

    // Constructor to initialize the number and compute factors
    public Factors(long n) {
        this.n = n;
        this.factorList = new ArrayList<>();
        calculateFactors();
    }

    // Default constructor
    public Factors() {
        this.factorList = new ArrayList<>();
    }

    // Method to calculate factors (optimized)
    private void calculateFactors() {
        List<Long> largerFactors = new ArrayList<>();
        long sqrt = (long) Math.sqrt(n);

        //Only iterate up to the square root of n so that program runs at O(sqrt n) time complexity
        //This is possible due to the rule that any factor has a corresponding factor, and any factor above the sqrt has a factor below the sqrt
        for (long i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                factorList.add(i); // Smaller factor
                if (i != n / i) {
                    largerFactors.add(0, n / i); // Larger factor (added at the factorPrompt of the list)
                }
            }
        }
        factorList.addAll(largerFactors); // Merge smaller and larger factors
    }

    // Method to return factors as a formatted string
    public String getFactors() {
        if (factorList.isEmpty() || (factorList.size() == 2 && n != 1)) {
            return n + " is a prime number!";
        } else {
            StringBuilder result = new StringBuilder();
            result.append(n).append(" has ").append(factorList.size()).append(" factors! They are ");
            for (int i = 0; i < factorList.size(); i++) {
                result.append(factorList.get(i));
                if (i < factorList.size() - 1) {
                    result.append(", ");
                }
            }
            return result.toString();
        }
    }

    // Return the number of factors
    public int factorNum() {
        return factorList.size();
    }
}
