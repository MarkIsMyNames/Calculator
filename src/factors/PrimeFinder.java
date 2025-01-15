package factors;

import java.util.ArrayList;

public class PrimeFinder {

    int n;
    long nthPrime;

    public PrimeFinder(int n){
        this.n = n;
    }

    public PrimeFinder(){}

    // Method to find the nth prime
    public long findNthPrime() {

        try {
            if (n < 1 || n > 50000000) {
                throw new IllegalArgumentException("Please enter a positive integer between 1 and 50,000,000");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage() + "\n");
            factors.FactorManager.primePrompt();
        }

        // Use a rough estimate for the upper bound of nth prime
        long limit = (n < 6) ? 15 : (long) (n * Math.log(n) + n * Math.log(Math.log(n)));

        // Generate primes using the Sieve of Eratosthenes
        boolean[] isPrime = new boolean[(int) (limit + 1)];
        ArrayList<Long> primes = new ArrayList<>();

        // Initialize the sieve
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        // Sieve of Eratosthenes
        for (int p = 2; p * p <= limit; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= limit; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Collect primes
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add((long) i);
            }
        }

        // Return the nth prime
        if (primes.size() >= n) {
            nthPrime = primes.get(n - 1);
            return nthPrime;
        } else {
            throw new RuntimeException("Error with primes.size()");
        }

    }

    public long getNthPrime() {
        return nthPrime;
    }

}
