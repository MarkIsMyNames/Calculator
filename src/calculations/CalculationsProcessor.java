package calculations;


public class CalculationsProcessor extends processor.InputProcessor {

    private static String processedString;

    public CalculationsProcessor(String input) {
        super(input);

        input = getProcessedString();

        setProcessedString(input);

    }

    public static String getProcessedString() {
        return processedString;
    }

    public static void setProcessedString(String processedString) {
        CalculationsProcessor.processedString = processedString;
    }

    @Override
    public String toString() {
        return getProcessedString();
    }
}