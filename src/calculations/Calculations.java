package calculations;


import java.util.*;

public class Calculations{

    protected String calculationInput;
    protected double answer;
    protected String originalInput;


    public Calculations(String calculationInput) {

        originalInput = calculationInput; //Just saving the original input to output to terminal later as it looks cleaner than the version with the regex

        //Regex to replace certain Strings with constants e.g. g=9.81
        this.calculationInput = CalculationsProcessor.calculationProcess(calculationInput);
    }


    public Calculations(){} //Default constructor

    public double evaluate(String calculationInput){

        // Shunting Yard Algorithm implementation (https://brilliant.org/wiki/shunting-yard-algorithm/)

        //Tokenize the expression
        List<String> tokens = tokenize(calculationInput);

        //Convert to RPN
        List<String> rpn = toRPN(tokens);

        //Evaluate the expression in RPN
        answer = evaluateRPN(rpn);

        return answer;
    }

    private List<String> tokenize(String calculationInput){

        this.calculationInput = calculationInput;
        List<String> tokens = new ArrayList<>(); //initialising tokens list for return statement
        StringBuilder currentToken = new StringBuilder(); //initialising string builder to store information

        for(int i = 0; i < calculationInput.length(); i++){ // Corrected loop condition
            char ch = calculationInput.charAt(i);

            //Adding all characters to the currentToken list
            if(Character.isWhitespace(ch)){
                continue; //ignore the white space if there is any
            }
            else if(Character.isDigit(ch) || ch == '.'){
                currentToken.append(ch); //if digit or decimal, add to the current token
            }
            else if (Character.isLetter(ch)) {
                currentToken.append(ch); // Add letters to the current token
            } else if (Character.isDigit(ch) && !currentToken.isEmpty() && Character.isLetter(currentToken.charAt(0))) {
                // Handle cases like "root3" or "log5" by appending the digit to the function token
                currentToken.append(ch);
            } else {
                if (!currentToken.isEmpty()) {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                tokens.add(Character.toString(ch)); // Add operator/parentheses as a seperate token
            }

        }

        //Add the last token (if there is any)
        if(!currentToken.isEmpty()){
            tokens.add(currentToken.toString());
        }
        return tokens;
    }

    //Convert tokens to Reverse Polish Notation (This is the notation needed for the shunting yard algorithm to work)
    //https://brilliant.org/wiki/shunting-yard-algorithm/#reverse-polish

    private List<String> toRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        Map<String, Integer> precedence = Map.of(
                "+", 1, "-", 1,
                "*", 2, "/", 2,
                "^", 3, "%", 3,
                "!", 4
        );

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isFunction(token)) {
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                operators.pop(); // Remove "("
                if (!operators.isEmpty() && isFunction(operators.peek())) {
                    output.add(operators.pop()); // Add function
                }
            } else { // Operator
                while (!operators.isEmpty() && precedence.getOrDefault(operators.peek(), 0) >= precedence.getOrDefault(token, 0)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            }
        }
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }
        return output;
    }


    //Evaluate the RPN expression
    private double evaluateRPN(List<String> rpn) {
        Stack<Double> stack = new Stack<>();
        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isFunction(token)) {
                if (token.startsWith("root") || token.startsWith("log")) {
                    if (stack.isEmpty()) { // Check if there's at least one argument
                        throw new IllegalStateException("Invalid expression. Not enough values for double-argument function: " + token);
                    }
                    double value = stack.pop();
                    double base = Double.parseDouble(token.replaceAll("[^0-9]", "")); // Extract the base
                    String functionName = token.replaceAll("[0-9]", ""); // Extract function name
                    stack.push(applyDoubleFunction(functionName, base, value));
                } else {
                    if (stack.isEmpty()) {
                        throw new IllegalStateException("Invalid expression. Not enough values for function: " + token);
                    }
                    double a = stack.pop();
                    stack.push(applyFunction(token, a));
                }
            } else {
                if (stack.size() < 2) {
                    throw new IllegalStateException("Invalid expression. Not enough values for operator: " + token);
                }
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token));
            }
        }
        if (stack.size() != 1) {
            throw new IllegalStateException("Invalid RPN expression. Stack size should be 1 but is: " + stack.size());
        }
        return stack.pop();
    }



    private boolean isNumber(String token){
        try {
            Double.parseDouble(token); //Tests if the string can be converted to a double
            return true;
        } catch (NumberFormatException e) {
            return false; //Returns false if an error occurs trying to convert to a double i.e. not a number
        }
    }

    private boolean isFunction(String token){
        return token.equals("sin") || token.equals("cos") || token.equals("ln") || token.equals("sqrt") || token.equals("tan")
                || token.equals("sinh") || token.equals("cosh") || token.equals("tanh") //As many functions can be added to this as needed
                || token.startsWith("root") || token.startsWith("log"); //Using startsWith() method to handle functions that take 2 numbers
    }


    private double applyOperator(double a, double b, String operator) {
        return switch (operator) { //Returns one of the options depending on the case
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) { //Error handling for divide by 0

                    throw new ArithmeticException("Division by zero is not allowed!");
                }
                yield a / b;
            }
            case "%" -> a%b;
            case "^" -> Math.pow(a, b);
            case "!" ->{
                double factorialA;
                // checks if a is zero
                if(a == 0){
                    yield 1;

                    //checks if a is a decimal
                } else if (a%1==0) {
                    factorialA = a;

                    // for loop to create X!
                    for (int factorialCounter = 1; factorialCounter < a; factorialCounter++) {
                        factorialA *= factorialCounter;
                    }
                    yield factorialA; //returns a!
                }else {
                    throw new IllegalArgumentException("Can't get the factorial of a decimal " + operator);
                }
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }


    private double applyFunction(String function, double a) {
        return switch (function) {
            case "sin" -> Math.sin(Math.toRadians(a));
            case "cos" -> Math.cos(Math.toRadians(a));
            case "tan" -> Math.tan(Math.toRadians(a));
            case "sinh" -> Math.sinh(Math.toRadians(a));
            case "cosh" -> Math.cosh(Math.toRadians(a));
            case "tanh" -> Math.tanh(Math.toRadians(a));
            case "sqrt" -> Math.sqrt(a);
            case "ln" -> Math.log(a);
            //Room to add more functions here if needed
            default -> throw new IllegalArgumentException("Unknown function: " + function);
        };
    }

    private double applyDoubleFunction(String function, double base, double value) {
        return switch (function) {
            case "root" -> Math.pow(value, 1.0 / base); // Compute nth root
            case "log" -> Math.log(value) / Math.log(base); // Compute log with base
            default -> throw new IllegalArgumentException("Unknown double-argument function: " + function);
        };
    }


    public String getInput(){
        return calculationInput;
    }

    public double getAnswer(){
        answer = evaluate(calculationInput);
        return answer;
    }

    public String getOriginalInput(){
        return originalInput;
    }

}
