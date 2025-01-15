package algebra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AlgebraMultiplicationAndDivision extends Division {

    // This method processes the entire mathematical expression, identifying and handling multiplication and division
    protected static String multiplyTerms(String expression) {
        // This regular expression splits the expression into individual terms while keeping the operators ('+' or '-')
        String[] terms = expression.split("(?=(?<!\\^)\\+|(?<!\\^)-)");

        // StringBuilder to accumulate the resulting expression after processing multiplication terms
        StringBuilder resultExpression = new StringBuilder();

        // Iterate through each term in the expression
        for (String term : terms) {
            term = term.trim();  // Remove any leading or trailing whitespace for cleaner processing
            // Check if the term contains multiplication ('*')
            if (term.contains("*")) {
                resultExpression.append(processMultiplication(term));  // Process the multiplication for this term
            } else {
                // If there's no multiplication, append the term as-is (with the sign if present)
                resultExpression.append(term);
            }
        }

        // Return the final expression after processing all terms and handling multiplication
        return resultExpression.toString();
    }

    // This method processes multiplication of terms, combining like terms and handling coefficients and exponents
    private static String processMultiplication(String term) {
        // Regex pattern to match terms like '6x', '4y', '2x^2', '3x^3', etc.
        // The pattern captures three parts:
        // 1. The coefficient (optional) - could be a number or empty (default to 1 if not present)
        // 2. The variable(s) - could be one or more variables (e.g., 'x', 'xy', 'xyz')
        // 3. The exponent (optional) - could be a power (e.g., '^2', '^3') or absent (default to exponent 1)
        Pattern multiplicationPattern = Pattern.compile("([+-]?\\d*\\.?\\d*)([a-zA-Z]+)(\\^[-+]?\\d*)?");
        Matcher matcher = multiplicationPattern.matcher(term);  // Matcher to apply the regex to the current term

        // Map to store the accumulated exponents of each variable (e.g., 'x^3', 'y^2')
        Map<String, Integer> variableExponentMap = new HashMap<>();
        double totalCoefficient = 1;  // Initialize the overall coefficient to 1 (to be multiplied)

        // Process each match found by the regex in the term (there could be multiple parts to multiply)
        while (matcher.find()) {
            // Parse the coefficient (if not found, default to 1)
            double coefficient = matcher.group(1).isEmpty() ? 1 : Double.parseDouble(matcher.group(1));

            // Extract the variables (e.g., 'x', 'y', 'xy') from the term
            String variables = matcher.group(2);

            // Parse the exponent (if not found, default to 1)
            int exponent = matcher.group(3) == null ? 1 : Integer.parseInt(matcher.group(3).substring(1));

            // Multiply the coefficient of this match with the overall coefficient so far
            totalCoefficient *= coefficient;

            // Process each individual variable (such as 'x', 'y', 'z' in the term)
            for (char variable : variables.toCharArray()) {
                String variableStr = String.valueOf(variable);  // Convert the character to a string
                // Update the exponent for this variable by adding the current exponent
                // If the variable already exists in the map, its exponent is increased, otherwise it's set to the current exponent
                variableExponentMap.put(variableStr, variableExponentMap.getOrDefault(variableStr, 0) + exponent);
            }
        }

        // StringBuilder to build the resulting expression after multiplication
        StringBuilder resultTerm = new StringBuilder();

        // If the total coefficient is not 1, append it to the result
        if (totalCoefficient != 1) {
            resultTerm.append(totalCoefficient);  // Add the coefficient to the result term
        }

        // Separate variables into two lists: one for variables without exponents, and one for variables with exponents
        List<Map.Entry<String, Integer>> variablesWithoutExponent = new ArrayList<>();
        List<Map.Entry<String, Integer>> variablesWithExponent = new ArrayList<>();

        // Separate variables based on whether they have an exponent
        for (Map.Entry<String, Integer> entry : variableExponentMap.entrySet()) {
            if (entry.getValue() == 1) {
                variablesWithoutExponent.add(entry);  // No exponent
            } else {
                variablesWithExponent.add(entry);  // With exponent
            }
        }

        // Sort variables without exponents alphabetically
        variablesWithoutExponent.sort(Map.Entry.comparingByKey());

        // Sort variables with exponents first by exponent (ascending), then alphabetically
        variablesWithExponent.sort((entry1, entry2) -> {
            int exponentComparison = Integer.compare(entry1.getValue(), entry2.getValue());  // Compare exponents in ascending order
            if (exponentComparison == 0) {
                return entry1.getKey().compareTo(entry2.getKey());  // If exponents are equal, sort alphabetically by variable
            }
            return exponentComparison;  // Return the result of exponent comparison
        });

        // Append variables without exponents first
        for (Map.Entry<String, Integer> entry : variablesWithoutExponent) {
            resultTerm.append(entry.getKey());
        }

        // Append variables with exponents next
        for (Map.Entry<String, Integer> entry : variablesWithExponent) {
            resultTerm.append(entry.getKey());  // Variable itself
            resultTerm.append("^").append(entry.getValue());  // Exponent
        }

        // Return the final processed term after handling multiplication
        return resultTerm.toString();
    }
}
