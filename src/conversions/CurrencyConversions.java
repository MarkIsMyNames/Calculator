package conversions;

import menu.MenuManager;

import java.util.Scanner;

public class CurrencyConversions {

    public static void main(String[] args) {
        currencyConversions();
    }

    public static void currencyConversions() {
        Scanner scanner = new Scanner(System.in);

        // Ask what the user is converting from
        int convertingFrom = getConversionChoice(scanner, "\nWhat are you converting from?");

        // Ask what the user is converting to
        int convertingTo = getConversionChoice(scanner, "\nWhat are you converting to?");

        // Make sure that convertingFrom and convertingTo are not the same
        while (convertingFrom == convertingTo) {
            System.out.println("Conversion from and to cannot be the same. Please select a different conversion type.");
            convertingTo = getConversionChoice(scanner, "\nWhat are you converting to?");
        }

        // Ask for the amount to convert
        System.out.print("\nEnter the amount to convert:");
        String inputAmount = scanner.nextLine();

        // Convert using polymorphism
        CurrencyConverter converter = getConverter(convertingFrom, convertingTo);
        try {
            String result = converter.convert(inputAmount);
            System.out.println("\nConverted value: " + result);
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid amount entered.");
        }

        scanner.nextLine(); //Wait for user to hit enter then clear screen and re prompt menu
        MenuManager.clearScreen();
        ConversionsManager.start();
    }

    private static int getConversionChoice(Scanner scanner, String prompt) {
        int choice = 0;

        while (true) {
            System.out.println(prompt);
            System.out.println("Enter (1) for Euro");
            System.out.println("Enter (2) for American Dollar");
            System.out.println("Enter (3) for British Pound");
            System.out.println("Enter (4) for Japanese Yen");
            System.out.println("Enter (5) for Australian Dollar");

            System.out.print("Enter a choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                if (choice >= 1 && choice <= 5) {
                    return choice; // Valid choice
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear buffer of invalid input
            }
        }
    }

    // Decide which converter to use based on what we're converting from and to
    private static CurrencyConverter getConverter(int from, int to) {
        if (from == 1 && to == 2) return new EuroToDollarConverter();
        if (from == 2 && to == 1) return new DollarToEuroConverter();
        if (from == 1 && to == 3) return new EuroToPoundConverter();
        if (from == 3 && to == 1) return new PoundToEuroConverter();
        if (from == 2 && to == 3) return new DollarToPoundConverter();
        if (from == 3 && to == 2) return new PoundToDollarConverter();
        if (from == 1 && to == 4) return new EuroToYenConverter();
        if (from == 4 && to == 1) return new YenToEuroConverter();
        if (from == 1 && to == 5) return new EuroToAUDConverter();
        if (from == 5 && to == 1) return new AUDToEuroConverter();
        if (from == 2 && to == 4) return new DollarToYenConverter();
        if (from == 4 && to == 2) return new YenToDollarConverter();
        if (from == 2 && to == 5) return new DollarToAUDConverter();
        if (from == 5 && to == 2) return new AUDToDollarConverter();
        if (from == 3 && to == 4) return new PoundToYenConverter();
        if (from == 4 && to == 3) return new YenToPoundConverter();
        if (from == 3 && to == 5) return new PoundToAUDConverter();
        if (from == 5 && to == 3) return new AUDToPoundConverter();
        if (from == 4 && to == 5) return new YenToAUDConverter();
        if (from == 5 && to == 4) return new AUDToYenConverter();

        throw new IllegalArgumentException("Unsupported conversion choice.");
    }
}

interface CurrencyConverter {
    String convert(String input) throws NumberFormatException;
}

// Example conversion rates
class EuroToDollarConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 1.1); // 1 Euro = 1.1 USD
    }
}

class DollarToEuroConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.91); // 1 USD = 0.91 Euro
    }
}

class EuroToPoundConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.86); // 1 Euro = 0.86 GBP
    }
}

class PoundToEuroConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 1.16); // 1 GBP = 1.16 Euro
    }
}

class DollarToPoundConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.78); // 1 USD = 0.78 GBP
    }
}

class PoundToDollarConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 1.28); // 1 GBP = 1.28 USD
    }
}

class EuroToYenConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 129.53); // 1 Euro = 129.53 JPY
    }
}

class YenToEuroConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.0077); // 1 JPY = 0.0077 Euro
    }
}

class EuroToAUDConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 1.61); // 1 Euro = 1.61 AUD
    }
}

class AUDToEuroConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.62); // 1 AUD = 0.62 Euro
    }
}

class DollarToYenConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 115.21); // 1 USD = 115.21 JPY
    }
}

class YenToDollarConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.0087); // 1 JPY = 0.0087 USD
    }
}

class DollarToAUDConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 1.39); // 1 USD = 1.39 AUD
    }
}

class AUDToDollarConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.72); // 1 AUD = 0.72 USD
    }
}

class PoundToYenConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 151.61); // 1 GBP = 151.61 JPY
    }
}

class YenToPoundConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.0066); // 1 JPY = 0.0066 GBP
    }
}

class PoundToAUDConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 1.88); // 1 GBP = 1.88 AUD
    }
}

class AUDToPoundConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.53); // 1 AUD = 0.53 GBP
    }
}

class YenToAUDConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 0.012); // 1 JPY = 0.012 AUD
    }
}

class AUDToYenConverter implements CurrencyConverter {
    @Override
    public String convert(String input) {
        double amount = Double.parseDouble(input);
        return String.format("%.2f", amount * 83.15); // 1 AUD = 83.15 JPY
    }
}
