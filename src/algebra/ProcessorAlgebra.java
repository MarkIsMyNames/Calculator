package algebra;

public interface ProcessorAlgebra {
    static String processingRegex(String expression){

        // replace x^0 with nothing
        expression = expression.replaceAll("[a-zA-Z]\\^0", "");

        // replace x^1 with x
        expression = expression.replaceAll("([a-zA-Z])(\\^1)", "$1");
        return expression;
    }
}
