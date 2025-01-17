package processor;

public class InputProcessor {
    // method that takes and "String input" and returns a String
    protected InputProcessor(String input){
        //Remove all whitespace
        input = input.replaceAll("\\s+", "");
        // Add * between a number and a letter
        input = input.replaceAll("(\\d)([a-zA-Z])", "$1*$2");
        // Replace ")(" with ")*("
        input = input.replaceAll("\\)\\(", ")*(");

        //Replace "- -" with "+" , "- (" with "-1 (" , etc.
        //This fixes a lot of issues with minus signs
        input = minusFixer(input);

        // Replacing factorial with factorial number needed for the 2 argument constructor
        input = input.replaceAll("!", "!0");

        // Add * between a number and a parenthesis unless the number is preceded by 't' or 'g' for log and root
        input = input.replaceAll("(?<![tg\\d])(\\d)(\\()", "$1*$2");

        //Constant Replacer
        input = constantReplacer(input);

        calculations.CalculationsProcessor.setProcessedString(input);
    }

    private String minusFixer(String input){
        input = input.replaceAll("--", "+");
        input = input.replaceAll("-\\(", "-1(");
        input = input.replaceAll("-([a-zA-Z])", "-1*$1");
        input = input.replaceFirst("^-", "0-");
        input = input.replaceAll("\\(-", "(0-");

        return input;
    }

    private String constantReplacer(String input){
        input = input.replaceAll("\\be\\b", String.valueOf(Math.E));    //Math.E for e
        input = input.replaceAll("\\bk\\b", "1.38065050*10^(0-23)");    //Boltzmann constant for k
        input = input.replaceAll("\\bF\\b", "96485.3383");  //Faraday constant for F
        input = input.replaceAll("\\beV\\b", "1.602176530*10^(0-19)");  //Electron Volt for eV
        input = input.replaceAll("\\bG\\b", "6.6742*10^(0-11)");    //Gravitational constant for G
        input = input.replaceAll("\\bpi\\b", String.valueOf(Math.PI));  //Math.PI for pi
        input = input.replaceAll("\\bg\\b", "9.81");    //Gravity for g
        input = input.replaceAll("\\bh\\b", "6.62607015*10^(0-34)");    //Planck's constant for h
        input = input.replaceAll("\\bc\\b", "299792458");   //Speed of light in vacuo for c
        input = input.replaceAll("\\bR\\b", "8.314472");    //Universal Gas Constant for R
        input = input.replaceAll("\\bu\\b", "1.6605402*10^(0-27)"); //Unified Atomic Mass Unit for u

        return input;
    }
}
