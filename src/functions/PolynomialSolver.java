package functions;

import static menu.Colours.*;

public class PolynomialSolver {

    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private String[] roots;
    private static byte checker;

    public PolynomialSolver(double a, double b){ //linear equations constructor
        this.a = a;
        this.b = b;
    }

    public PolynomialSolver(double a, double b, double c){ // quadratic equations constructor
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public PolynomialSolver(double a, double b, double c, double d){ // cubic equations constructor
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public PolynomialSolver(double a, double b, double c, double d, double e){ // quartic equations constructor
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    //Basic formula to find a single root
    public String[] linearPolynomial(){
        //set size of array
        roots = new String[1];

        //Solve equation
        double root = - b / a;

        //add roots to array
        roots[0] = String.valueOf(root);

        // set checker to linear
        checker = 1;
        return roots;
    }

    //The minus b formula to find roots of a quadratic
    public String[] quadraticPolynomial(){

        if(a == 0){ //Handles situations where the user inputs the wrong type of polynomial
            PolynomialSolver linear = new PolynomialSolver(b, c);
            return linear.linearPolynomial();
        }

        //Sets array size
        roots = new String[2];

        //solves equation
        double discriminant = (b*b - 4*a*c);
        //Checks are the roots real or complex
        if(discriminant < 0){
            //Complex roots:
            discriminant = -discriminant; //Mark discriminant positive
            roots[0] = String.format("%f + %fi", -b / (2*a), Math.sqrt(discriminant) / (2*a));  //Adds roots to array
            roots[1] = String.format("%f - %fi", -b / (2*a), Math.sqrt(discriminant) / (2*a));  //Adds roots to array
        } else {
            //Real roots:
            roots[0] = String.valueOf(((-b + Math.sqrt(discriminant)) / (2*a)));    //Adds roots to array
            roots[1] = String.valueOf(((-b - Math.sqrt(discriminant)) / (2*a)));    //Adds roots to array
        }
        // set checker to quadratic
        checker = 2;
        return roots;
    }

    //Cardano's method for finding the roots of a cubic equation https://brilliant.org/wiki/cardano-method/
    public String[] cubicPolynomial() {

        if(a == 0){ //Handles situations where the user inputs the wrong type of polynomial
            PolynomialSolver quadratic = new PolynomialSolver(b, c, d);
            return quadratic.quadraticPolynomial();
        }

        //Sets array size
        roots = new String[3];

        // Normalize the polynomial
        if (a != 1) {
            b /= a;
            c /= a;
            d /= a;
            a = 1;
        }

        // Compute p and q
        double p = (3 * a * c - b * b) / (3 * a * a);
        double q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a * a);

        // Compute the discriminant
        double discriminant = Math.pow(q / 2, 2) + Math.pow(p / 3, 3);

        // Shift for depressed cubic
        double shift = -b / (3 * a);

        if (discriminant > 0) {
            // One real root, two complex conjugate roots
            double u = Math.cbrt(-q / 2 + Math.sqrt(discriminant));
            double v = Math.cbrt(-q / 2 - Math.sqrt(discriminant));

            double realRoot = u + v + shift;
            double imaginaryPart = Math.sqrt(3) * Math.abs(u - v) / 2;

            roots[0] = String.format("%.6f", realRoot);
            roots[1] = String.format("%.6f + %.6fi", -(u + v) / 2 + shift, imaginaryPart);
            roots[2] = String.format("%.6f - %.6fi", -(u + v) / 2 + shift, imaginaryPart);
        } else if (Math.abs(discriminant) < 1e-6) {
            // All roots real, at least two are equal
            double u = Math.cbrt(-q / 2);
            double doubleRoot = 2 * u + shift;
            double singleRoot = -u + shift;

            roots[0] = String.format("%.6f", doubleRoot);
            roots[1] = String.format("%.6f", doubleRoot);
            roots[2] = String.format("%.6f", singleRoot);
        } else {
            // All roots real and distinct
            double r = Math.sqrt(-Math.pow(p / 3, 3));
            double phi = Math.acos(-q / (2 * r));
            double root1 = 2 * Math.cbrt(r) * Math.cos(phi / 3) + shift;
            double root2 = 2 * Math.cbrt(r) * Math.cos((phi + 2 * Math.PI) / 3) + shift;
            double root3 = 2 * Math.cbrt(r) * Math.cos((phi + 4 * Math.PI) / 3) + shift;

            roots[0] = String.format("%.6f", root1);
            roots[1] = String.format("%.6f", root2);
            roots[2] = String.format("%.6f", root3);
        }

        // set checker to cubic
        checker = 3;
        return roots;
    }


    //Ferrari's method for solving fourth degree polynomials https://encyclopediaofmath.org/wiki/Ferrari_method
    public String[] quarticPolynomial() {

        if (a == 0) {
            PolynomialSolver ps = new PolynomialSolver(b, c, d, e);
            return ps.cubicPolynomial();
        }

        roots = new String[4];

        // Normalize the polynomial
        if (a != 1) {
            a /= a;
            b /= a;
            c /= a;
            d /= a;
            e /= a;
        }

        // Step 1: Depress the quartic equation
        double alpha = -3 * Math.pow(b, 2) / 8 + c;
        double beta = Math.pow(b, 3) / 8 - (b * c) / 2 + d;
        double gamma = -3 * Math.pow(b, 4) / 256 + (Math.pow(b, 2) * c) / 16 - (b * d) / 4 + e;

        // Step 2: Solve the resolvent cubic
        double aRes = 1;
        double bRes = 2 * alpha;
        double cRes = alpha * alpha - 4 * gamma;
        double dRes = -beta * beta;

        // set checker to quadratic
        PolynomialSolver cubicSolver = new PolynomialSolver(aRes, bRes, cRes, dRes);
        String[] cubicRoots = cubicSolver.cubicPolynomial();

        // Correctly choose the largest real root of the cubic
        double y = Double.NEGATIVE_INFINITY;
        for (String root : cubicRoots) {
            if (!root.contains("i")) {
                double realRoot = Double.parseDouble(root);
                if (realRoot > y) {
                    y = realRoot;
                }
            }
        }

        if (y == Double.NEGATIVE_INFINITY) {
            throw new ArithmeticException("No real root found in the resolvent cubic.");
        }

        // Step 3: Solve the two quadratic equations
        double r1 = Math.sqrt(y);
        if (Double.isNaN(r1)) {
            throw new ArithmeticException("Invalid square root for y: " + y);
        }

        double discriminant1 = 2 * alpha + 2 * y - beta / r1;
        double discriminant2 = 2 * alpha + 2 * y + beta / r1;

        double r2 = discriminant1 >= 0 ? Math.sqrt(discriminant1) : Double.NaN;
        double r3 = discriminant2 >= 0 ? Math.sqrt(discriminant2) : Double.NaN;

        roots[0] = String.format("%.6f", -b / 4 + r1 / 2 + (Double.isNaN(r2) ? 0 : r2 / 2));
        roots[1] = String.format("%.6f", -b / 4 + r1 / 2 - (Double.isNaN(r2) ? 0 : r2 / 2));
        roots[2] = String.format("%.6f", -b / 4 - r1 / 2 + (Double.isNaN(r3) ? 0 : r3 / 2));
        roots[3] = String.format("%.6f", -b / 4 - r1 / 2 - (Double.isNaN(r3) ? 0 : r3 / 2));

        checker = 4;
        return roots;
    }



    public String rootsString(String[] roots, double[]coeffieients){
        int boxWidth = 66; // Adjust this for wider equations if needed

        // top of box
        StringBuilder answerBox = new StringBuilder(CYAN + "╔" + "═".repeat(boxWidth) + "╗\n" + RESET);

        // Format + add equation
        String equation = getEquation(coeffieients);
        answerBox.append(formatLine(equation, boxWidth));

        //Add Divider
        answerBox.append(CYAN + "╠" + "═".repeat(boxWidth) + "╣\n" + RESET);

        //add roots
        answerBox.append(formatRoots(boxWidth, roots));
        answerBox.append(CYAN + "╚" + "═".repeat(boxWidth) + "╝\n" + RESET);
        return answerBox.toString();
    }

    private String formatRoots(int boxWidth, String[] roots) {
        StringBuilder rootsStringBuilder = new StringBuilder();

        //Cycle through each root, format it and add it to StringBuilder
        for (int eachRoot = 0; eachRoot < roots.length; eachRoot++) {
            String rootLine = String.format("Root %d: %s", eachRoot + 1, roots[eachRoot]);
            rootsStringBuilder.append(formatLine(rootLine, boxWidth));
        }

        return rootsStringBuilder.toString();
    }

    private String formatLine(String content, int boxWidth) {
        int paddingSize = boxWidth - content.length() - 4; // Account for borders and spaces
        int paddingLeft = paddingSize / 2; //Padding before the roots
        int paddingRight = paddingSize - paddingLeft; // Padding after the roots

        //Return formatted roots
        return CYAN + "║" + " ".repeat(paddingLeft) + WHITE + content + " ".repeat(paddingRight) + CYAN + "    ║\n" + RESET;
    }

    private String getEquation(double[]coeffieients) {
        // Find number of coeffieients
        byte numberOfCoeffients = (byte) coeffieients.length;

        //ensures correct formatting for equation
        String equation;
        if(checker == 1) {
            //Cycles backwards through the array
            b = coeffieients[numberOfCoeffients-1];
            a = coeffieients[numberOfCoeffients-2];

            equation = String.format("Roots of %.2fx + %.2f", a, b);
        }else if(checker == 2){
            //Cycles backwards through the array
            c = coeffieients[numberOfCoeffients-1];
            b = coeffieients[numberOfCoeffients-2];
            a = coeffieients[numberOfCoeffients-3];

            equation = String.format("Roots of %.2fx^2 + %.2fx + %.2f", a, b, c);
        }else if(checker == 3){
            //Cycles backwards through the array
            d = coeffieients[numberOfCoeffients-1];
            c = coeffieients[numberOfCoeffients-2];
            b = coeffieients[numberOfCoeffients-3];
            a = coeffieients[numberOfCoeffients-4];

            equation = String.format("Roots of %.2fx^3 + %.2fx^2 + %.2fx + %.2f", a, b, c, d);
        } else if (checker == 4) {
            equation = String.format("Roots of %.2fx^4 + %.2fx^3 + %.2fx^2 + %.2fx + %.2f", a, b, c, d, e);
        }else{
            throw new IllegalArgumentException("This only supports up to quartic equations");
        }
        return equation;
    }
}