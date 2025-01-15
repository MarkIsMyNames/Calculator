package algebra;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Division {
    //Divides terms in the expression and returns the updated expression.
    // Handles division and adjusts the expression accordingly (e.g., "a / b" becomes "a * (1/b)")
    protected static String handleDivision(String inputExpression) {
        // Regular expression to find division patterns, e.g., "9 / 4" or "3 / 5b"
        Pattern divisionPattern = Pattern.compile("(\\S+) / (\\S+)");  // Matches non-whitespace sequences around "/"
        Matcher divisionMatcher = divisionPattern.matcher(inputExpression);

        // StringBuffer to build the result string as we modify the expression
        StringBuilder stringBuilderDivision = new StringBuilder();

        // Iterate through all matches of the division pattern
        while (divisionMatcher.find()) {
            String numerator = divisionMatcher.group(1);    // The part before the division (numerator)
            String denominator = divisionMatcher.group(2);  // The part after the division (denominator)

            // Case 1: Denominator is a single number (e.g., "9 / 5")
            if (denominator.matches("\\d+")) {

                if(Double.parseDouble(denominator)== 0){
                    throw new ArithmeticException("Error Divide by zero");
                }
                double reciprocal = 1.0 / Double.parseDouble(denominator);  // Calculate the reciprocal of the denominator

                divisionMatcher.appendReplacement(stringBuilderDivision, numerator + " * " + reciprocal);

            }
            // Case 2: Denominator contains single or multiple alphabetic characters (no powers and optional constant), (e.g., "9 / mcd") -> (9 * m^1c^1d^1)
            else if (denominator.matches("\\d*[a-zA-Z]+")) {
                double reciprocal = getReciprocal(denominator);

                String variablePart = denominator.replaceAll("\\d", "");      // Extract variable part (e.g., "b" from "5b")

                StringBuilder transformedDenominator = new StringBuilder();

                // Append "^1" for each letter in the denominator
                for (char variable : variablePart.toCharArray()) {
                    transformedDenominator.append(variable).append("^1");
                }

                transformedDenominator = new StringBuilder(handlePowers(String.valueOf(transformedDenominator)));

                divisionMatcher.appendReplacement(stringBuilderDivision, numerator + " * " + reciprocal + transformedDenominator);


                // Case 3: Denominator is a number followed by a variable with a power (e.g., "5x^4")
            } else if (denominator.matches("(\\d*[a-zA-Z](\\^([+-]?\\d+))?)+")) {
                double reciprocal = getReciprocal(denominator);


                String variablePart = addPowerOf1(denominator);
                variablePart = handlePowers(variablePart);
                divisionMatcher.appendReplacement(stringBuilderDivision, numerator + " * " + reciprocal + variablePart);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
        // Append the remaining part of the expression that wasn't matched
        divisionMatcher.appendTail(stringBuilderDivision);

        return stringBuilderDivision.toString();
    }

    private static double getReciprocal(String denominator) {
        double numberPart = 1;
        String numberPartString = denominator.replaceAll("[a-zA-Z]", "").replaceAll("\\^[+-]?\\d*","");
        try { // tries to find a number if not it ignores the exception
            numberPart = Double.parseDouble(numberPartString);  // Extract numeric part (e.g., "5" from "5b")
        }
        catch(Exception ignored){}

        if(numberPart == 0.0){
            throw new ArithmeticException("Error Divide by zero");
        }
        // Calculate the reciprocal of the numeric part
        return 1.0 / numberPart;
    }

    // Handles powers and reverses the sign of exponents (e.g., "x^4" becomes "x^-4")
    private static String handlePowers(String inputExpression) {

        // Regular expression to find exponentiation patterns, e.g., "x^4" or "z^-1"
        Pattern powerPattern = Pattern.compile("(\\w+)\\^(-?\\d+)");  // Matches variables with exponents (e.g., "x^4")
        Matcher powerMatcher = powerPattern.matcher(inputExpression);

        // StringBuffer to build the result string as we modify the expression
        StringBuilder transformedExpression = new StringBuilder();

        // Iterate through all matches of the exponentiation pattern
        while (powerMatcher.find()) {
            String variable = powerMatcher.group(1);  // The variable (e.g., "x")
            int exponent = Integer.parseInt(powerMatcher.group(2));  // The exponent (e.g., 4)

            // Reverse the sign of the exponent (e.g., "x^4" becomes "x^-4")
            powerMatcher.appendReplacement(transformedExpression, variable + "^" + (-exponent));
        }

        // Append the remaining part of the expression that wasn't matched
        powerMatcher.appendTail(transformedExpression);

        return transformedExpression.toString();
    }


    // This method transforms the input string by adding "^1" to letters that are not followed by a '^'.
    // If the letter is followed by '^' (like x^3), it remains unchanged.
    public static String addPowerOf1(String input) {

        // Check if the first character is a digit
        input = input.matches("^\\d.*") // If the string starts with a digit
                ? input.substring(1)     // Remove the first character (if it's a number)
                : input;                 // Otherwise, leave the string unchanged

        // Use StringBuilder to construct the transformed string efficiently
        StringBuilder transformedStringBuilder = new StringBuilder();

        // Iterate through each character in the input string
        for (int currentIndex = 0; currentIndex < input.length(); currentIndex++) {
            // Get the current character in the string
            char currentCharacter = input.charAt(currentIndex);

            // Check if the current character is a letter (either uppercase or lowercase)
            if (isLetter(currentCharacter)) {
                // Append the letter to the result
                transformedStringBuilder.append(currentCharacter);

                // Check if the next character is not '^' (if it's not an exponent format)
                if (currentIndex + 1 >= input.length() || input.charAt(currentIndex + 1) != '^') {
                    // If not followed by '^', append "^1" after the letter
                    transformedStringBuilder.append("^1");
                }
            } else {
                // If it's not a letter (i.e., a number, symbol, etc.), just append it to the result
                transformedStringBuilder.append(currentCharacter);
            }
        }

        // Return the final transformed string
        return transformedStringBuilder.toString();
    }
    // This helper method checks if a character is a letter (either uppercase or lowercase)
    public static boolean isLetter(char character) {
        // Check if the character is in the range 'a' to 'z' or 'A' to 'Z'
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }
}
