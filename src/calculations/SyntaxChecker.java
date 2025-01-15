package calculations;

public class SyntaxChecker {

    public String calculationInput;

    public SyntaxChecker(String calculationInput){
        this.calculationInput = calculationInput;
    }

    public void validSyntax() {
        this.calculationInput = calculationInput;

        // Check for consecutive operators (e.g., ++, --, **, etc.)
        if (calculationInput.matches(".*[+\\-*/^!%]{2,}.*")) {
            throw new IllegalArgumentException("Invalid Syntax: " + calculationInput + " has multiple consecutive operators");
        }

        // Check for unmatched parentheses
        int openParentheses = 0;
        for (char c : calculationInput.toCharArray()) {
            if (c == '(') openParentheses++;
            if (c == ')') openParentheses--;
            if (openParentheses < 0){
                throw new IllegalArgumentException("Invalid Syntax: " + calculationInput + " has mismatched parentheses");
            }
        }
        if (openParentheses != 0) throw new IllegalArgumentException("Invalid Syntax: " + calculationInput + " has mismatched parentheses");

        // Check for two numbers separated by spaces (e.g., "1 1 + 2")
        if (calculationInput.matches(".*\\d+\\s+\\d+.*")) {
            throw new IllegalArgumentException("Invalid Syntax: " + calculationInput + " has mismatched parentheses");
        }
    }


}
