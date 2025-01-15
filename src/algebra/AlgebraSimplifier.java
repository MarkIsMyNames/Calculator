package algebra;

import java.util.*;
import java.util.regex.*;

public class AlgebraSimplifier extends AlgebraMultiplicationAndDivision implements ProcessorAlgebra{
    //Simplifies the algebraic expression by combining like terms and handling basic arithmetic operations.

    public static String simplifyExpression(String expression) {

        // Step 1: Preprocess the expression to ensure multiplication operators are inserted where missing
        expression = preprocessMissingOperators(expression);

        // Step 2: Handle multiplication and division operations in the expression
        expression = handleMultiplicationAndDivision(expression);

        // Step 3: Split the expression into individual terms based on '+' or '-' signs
        String[] terms = expression.split("(?<!\\^)(?=[+-])");

        // A map to store the coefficients of terms by variable and exponent (e.g., "x^2", "y^3")
        Map<String, Double> termMap = new HashMap<>();

        // Step 4: Process each term to extract coefficients and variables
        for (String term : terms) {

            // Use regex to extract the coefficient, variable part, and exponent of terms like "2.5x^2"
            Matcher matcher = Pattern.compile("([+-]?\\d*\\.?\\d*)([a-zA-Z]+)(\\^[-+]?\\d+)?").matcher(term);

            if (matcher.matches()) {
                // This term contains a variable and potentially an exponent, so we process it
                String coefficientStr = matcher.group(1);  // Get the coefficient part (e.g., "2.5")
                String variable = matcher.group(2);        // Get the variable part (e.g., "x")
                String exponentStr = matcher.group(3);     // Get the exponent part (e.g., "^2")

                // Step 5: Default the coefficient to 1 if it is missing or just "+" or "-"
                double coefficient = coefficientStr.isEmpty() || coefficientStr.equals("+") || coefficientStr.equals("-") ?
                        (coefficientStr.equals("-") ? -1 : 1) : Double.parseDouble(coefficientStr);

                // Step 6: Handle exponent, default to 1 if not provided
                int exponent = (exponentStr == null || exponentStr.isEmpty()) ? 1 : Integer.parseInt(exponentStr.substring(1)); // Remove the '^' symbol

                // Step 7: Sort the variables alphabetically to normalize terms like "xy" and "yx"
                char[] variableArray = variable.toCharArray();
                Arrays.sort(variableArray);
                String sortedVariables = new String(variableArray);

                // Step 8: Create a unique key using the sorted variables and exponent (e.g., "x^2")
                String key = sortedVariables + "^" + exponent;

                // Step 9: Combine terms with the same variable and exponent by adding their coefficients
                termMap.put(key, termMap.getOrDefault(key, 0.0) + coefficient);
            } else {
                // Step 10: If the term doesn't have a variable, it's a constant number (e.g., "5")
                try {
                    double constant = Double.parseDouble(term);
                    termMap.put("constant", termMap.getOrDefault("constant", 0.0) + constant);
                } catch (NumberFormatException e) {
                    // If the term is not a valid number or term, print an error and return an empty string
                    System.err.print("Invalid term: " + term);
                    return "";
                }
            }
        }

        // Step 11: Rebuild the simplified expression from the termMap
        StringBuilder result = new StringBuilder();

        // Step 12: Add the constant term if present
        double constant = termMap.getOrDefault("constant", 0.0);
        if (constant != 0) {
            result.append(constant > 0 && !result.isEmpty() ? "+" : "").append(constant);
        }

        // Step 13: Add terms with variables and exponents, sorted by variable and exponent
        termMap.entrySet().stream()  // Convert the entry set of the term map to a stream
                .filter(entry -> !entry.getKey().equals("constant"))  // Exclude the entry where the key is "constant" (handle constants separately)
                .sorted(Map.Entry.comparingByKey())  // Sort the terms alphabetically by their variable and exponent (key)
                .forEach(entry -> {  // Iterate over each entry in the sorted stream
                    double coef = entry.getValue();  // Get the coefficient of the current term
                    if (coef != 0) {  // Only process terms with a non-zero coefficient
                        // If the coefficient is positive and it's not the first term in the result, add a '+' sign
                        result.append(coef > 0 && !result.isEmpty() ? "+" : "")
                                .append(coef == 1 ? "" : (coef == -1 ? "-" : coef))  // Format the coefficient (skip 1 or -1, add '-' for -1)
                                .append(entry.getKey());  // Append the variable and exponent (e.g., "x^2", "x^3", etc.)
                    }
                });


        // Step 14: Return the final simplified expression or "0" if there are no terms
        return !result.isEmpty() ? result.toString() : "0";
    }


    //Handles multiplication and division in the expression by calling separate methods for each.
    public static String handleMultiplicationAndDivision(String expression) {
        // Step 1: First handle division (e.g., 6x^2 / 2y => 6x^2 * 0.5y^-2)
        expression = Division.handleDivision(expression);

        // Step 2: Remove all spaces in the expression to make parsing easier
        expression = expression.replaceAll("\\s+", "");
        expression = ProcessorAlgebra.processingRegex(expression);

        // Step 3: Then handle multiplication (e.g., 3x^2 * 4x => 12x^3)
        expression = AlgebraMultiplicationAndDivision.multiplyTerms(expression);
        expression = ProcessorAlgebra.processingRegex(expression);
        return expression;
    }


    //Ensures multiplication operators are inserted where missing in the expression.
    //For example, "3x" becomes "3*x", "x2" becomes "x*2", and "x" becomes "1x".
    private static String preprocessMissingOperators(String expression) {
        // Add a multiplication operator between numbers and variables, e.g., "3*x" becomes "3x"
        expression = expression.replaceAll("([+-]?\\d*\\.?\\d)*([a-zA-Z])", "$1$2");

        // Add a multiplication operator between variables and numbers, e.g., "x2" becomes "x*2"
        expression = expression.replaceAll("([a-zA-Z])([+-]?\\d*\\.?\\d)", "$1*$2");

        // Removes Spaces
        expression = expression.replaceAll("\\s", "");

        // adds a space either side of the division
        expression = expression.replaceAll("/", " / ");
        return expression;
    }
}
