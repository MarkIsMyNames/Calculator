package conversions;

import menu.MenuManager;

import java.util.Scanner;

public class CompSciConversions {

    public static void compSciConversions() {
        Scanner scanner = new Scanner(System.in);

        // Ask what the user is converting from
        int convertingFrom = getConversionChoice(scanner, "\nWhat are you converting from?");

        // Ask what the user is converting to
        int convertingTo = getConversionChoice(scanner, "\nWhat are you converting to?");

        // Make sure that convertingFrom and convertingTo are not the same
        while (convertingFrom == convertingTo) {
            System.out.println("\nConversion from and to cannot be the same. Please select a different conversion type.");
            convertingTo = getConversionChoice(scanner, "\nWhat are you converting to?");
        }

        // Ask for the number to convert
        System.out.print("Enter the number to convert:");
        String inputNumber = scanner.nextLine();

        // Convert using polymorphism
        CompSciConverter compSciConverter = getConverter(convertingFrom, convertingTo);
        try {
            String result = compSciConverter.convert(inputNumber);
            System.out.println("\nConverted value: " + result);
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid number for the chosen conversion.");
        }

        scanner.nextLine(); //Wait for user to hit enter then clear screen and re prompt menu
        MenuManager.clearScreen();
        ConversionsManager.start();
    }

    private static int getConversionChoice(Scanner scanner, String prompt) {
        int choice = 0;

        while (true) {
            System.out.println(prompt);
            System.out.println("Enter (1) for binary");
            System.out.println("Enter (2) for decimal");
            System.out.println("Enter (3) for hex");
            System.out.print("\nEnter a choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                if (choice >= 1 && choice <= 3) {
                    System.out.println("\n");
                    return choice; // Valid choice
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                scanner.nextLine(); // Clear buffer of invalid input
            }
        }
    }

    // Deciding what convertor to use used on what we're converting from and to
    private static CompSciConverter getConverter(int from, int to) {
        if (from == 1 && to == 2) {
            return new BinaryToDecimalConverter();
        }
        else if (from == 2 && to == 1) {
            return new DecimalToBinaryConverter();
        }
        else if (from == 3 && to == 2) {
            return new HexToDecimalConverter();
        }
        else if (from == 2 && to == 3) {
            return new DecimalToHexConverter();
        }
        else if (from == 1 && to == 3) {
            return new BinaryToHexConvertor();
        }
        else if (from == 3 && to == 1) {
            return new HexToBinaryConvertor();
        }
        else {
            throw new IllegalArgumentException("Unsupported conversion choice.");
        }
    }
}

interface CompSciConverter {
    String convert(String input) throws NumberFormatException;
}

class BinaryToDecimalConverter implements CompSciConverter {
    @Override
    public String convert(String input) {
        int decimal = Integer.parseInt(input, 2); // Parse binary to decimal
        return String.valueOf(decimal);
    }
}

class DecimalToBinaryConverter implements CompSciConverter {
    @Override
    public String convert(String input) {
        int decimal = Integer.parseInt(input); // Parse input as decimal
        return Integer.toBinaryString(decimal); // Convert to binary
    }
}

class HexToDecimalConverter implements CompSciConverter {
    @Override
    public String convert(String input) {
        int decimal = Integer.parseInt(input, 16); // Parse hex to decimal
        return String.valueOf(decimal);
    }
}

class DecimalToHexConverter implements CompSciConverter {
    @Override
    public String convert(String input) {
        int decimal = Integer.parseInt(input); // Parse input as decimal
        return Integer.toHexString(decimal).toUpperCase(); // Convert to hex
    }
}

class BinaryToHexConvertor implements CompSciConverter {
    @Override
    public String convert(String input) {
        int decimal = Integer.parseInt(input, 2); // Parse binary to decimal
        return Integer.toHexString(decimal).toUpperCase(); // Convert to hex
    }
}

class HexToBinaryConvertor implements CompSciConverter {
    @Override
    public String convert(String input) {
        int decimal = Integer.parseInt(input, 16); // Parse hex to decimal
        return Integer.toBinaryString(decimal); // Convert to hex
    }
}