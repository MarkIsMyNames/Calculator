package processor;

public class InputProcessor {
    // method that takes and "String input" and returns a String
    protected InputProcessor(String input){
        // Add * between a number and a letter
        input = input.replaceAll("(\\d)([a-zA-Z])", "$1*$2");
        //Remove all whitespace
        input = input.replaceAll("\\s+", "");
        // Add * between a number and a parenthesis unless the number is preceded by 't' or 'g' for log and root
        input = input.replaceAll("(?<![tg\\d])(\\d)(\\()", "$1*$2");
        // Replace ")(" with ")*("
        input = input.replaceAll("\\)\\(", ")*(");

        //Replace "- -" with "+" , "- (" with "-1 (" , etc.
        //This fixes a lot of issues with minus signs
        input = input.replaceAll("--", "+");
        input = input.replaceAll("-\\(", "-1(");
        input = input.replaceAll("-([a-zA-Z])", "-1*$1");
        input = input.replaceFirst("^-", "0-");
        input = input.replaceAll("\\(-", "(0-");

        // Replacing factorial with factorial number needed for the 2 argument constructor
        input = input.replaceAll("!", "!0");

        // Replace e with a string conversion of Math.E
        input = input.replaceAll("\\be\\b", String.valueOf(Math.E));

        // Replace k with Boltzmann constant
        input = input.replaceAll("\\bk\\b", "1.38065050*10^(0-23)");

        // Replace F with the Faraday constant
        input = input.replaceAll("\\bF\\b", "96485.3383");

        // Replace eV with the electron volt
        input = input.replaceAll("\\beV\\b", "1.602176530*10^(0-19)");

        // Replace G with the gravitational constant
        input = input.replaceAll("\\bG\\b", "6.6742*10^(0-11)");

        // Replace pi with string conversion of Math.PI
        input = input.replaceAll("\\bpi\\b", String.valueOf(Math.PI));

        // Replacing factorial with factorial number needed for the 2 argument constructor
        input = input.replaceAll("!", "!0");

        // Replace g with gravity
        input = input.replaceAll("\\bg\\b", "9.81");

        // Replace h with Planck's Constant
        input = input.replaceAll("\\bh\\b", "6.62607015*10^(0-34)");

        // Replace c with the speed of light in vacuo
        input = input.replaceAll("\\bc\\b", "299792458");

        // Replace R with Universal Gas Constant
        input = input.replaceAll("\\bR\\b", "8.314472");

        // Replace u with Unified Atomic Mass Unit
        input = input.replaceAll("\\bu\\b", "1.6605402*10^(0-27)");

        // Ensure * between all numbers and (
        input = input.replaceAll("(?<![tg\\d])(\\d)(\\()", "$1*$2");

        calculations.CalculationsProcessor.setProcessedString(input);
    }
}
