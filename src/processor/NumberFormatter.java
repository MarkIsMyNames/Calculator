package processor;

public class NumberFormatter {

    public static String addCommas(String number) { //Adds commas to make long numbers more readable e.g. "1000000" -> "1,000,000"
        // Check for non-numeric characters excluding decimal points
        if (!number.matches("[0-9.]+")) {
            return number; // Return the input value if it contains invalid characters
        }

        // Split the number into the integer and decimal parts
        String[] parts = number.split("\\.");
        String integerPart = parts[0];
        String decimalPart = parts.length > 1 ? parts[1] : "";

        // Add commas to the integer part
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = integerPart.length() - 1; i >= 0; i--) {
            result.insert(0, integerPart.charAt(i));
            count++;
            if (count == 3 && i != 0) { // Add a comma every 3 digits, except at the start
                result.insert(0, ',');
                count = 0;
            }
        }

        // Append the decimal part if it exists
        if (!decimalPart.isEmpty()) {
            result.append('.').append(decimalPart);
        }

        return result.toString();
    }

    public static String removeCommas(String number){
        return number.replaceAll(",", ""); // Remove commas from number
    }

    public static String nthNumber(String number) {

        // Get the last digit of the number
        int lastDigit = Character.getNumericValue(number.charAt(number.length() - 1));

        // Determine the suffix based on the last digit
        String suffix;
        if (number.endsWith("11") || number.endsWith("12") || number.endsWith("13")) {
            suffix = "th"; // Special case for 11th, 12th, 13th
        } else {
            switch (lastDigit) {
                case 1:
                    suffix = "st";
                    break;
                case 2:
                    suffix = "nd";
                    break;
                case 3:
                    suffix = "rd";
                    break;
                default:
                    suffix = "th";
            }
        }

        // Return the number with its suffix
        return number + suffix;
    }



}
