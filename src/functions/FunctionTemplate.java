package functions;

import java.util.Scanner;

//Abstract superclass as a template for creating different classes that use functions
abstract public class FunctionTemplate {

    String functionInput;
    double startRange;
    double endRange;
    double stepSize;

    //Default constructor
    public FunctionTemplate(){}

    // Constructor taking input, range, step size
    public FunctionTemplate(String functionInput, double startRange, double endRange, double stepSize){
        this.functionInput = functionInput;
        this.startRange = startRange;
        this.endRange = endRange;
        this.stepSize = stepSize;
    }

}
