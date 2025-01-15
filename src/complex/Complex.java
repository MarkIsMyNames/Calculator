package complex;

import calculations.Calculations;

public class Complex extends Calculations {
        private double real;       // Real part
        private double imaginary;  // Imaginary part

        // Constructor
        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        //Default no argument constructor
        public Complex() {}

        // Getter for real part
        public double getReal() {
            return real;
        }

        // Getter for imaginary part
        public double getImaginary() {
            return imaginary;
        }

        // Add another complex number
        public Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imaginary + other.imaginary);
        }

        // Subtract another complex number
        public Complex subtract(Complex other) {
            return new Complex(this.real - other.real, this.imaginary - other.imaginary);
        }

        // Multiply by another complex number
        public Complex multiply(Complex other) {
            double realPart = (this.real * other.real) - (this.imaginary * other.imaginary);
            double imaginaryPart = (this.real * other.imaginary) + (this.imaginary * other.real);
            return new Complex(realPart, imaginaryPart);
        }

        // Divide by another complex number
        public Complex divide(Complex other) {
            double denominator = (other.real * other.real) + (other.imaginary * other.imaginary);
            if (denominator == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            double realPart = ((this.real * other.real) + (this.imaginary * other.imaginary)) / denominator;
            double imaginaryPart = ((this.imaginary * other.real) - (this.real * other.imaginary)) / denominator;
            return new Complex(realPart, imaginaryPart);
        }

        // Compute the magnitude (r)
        public double getMagnitude() {
            return Math.sqrt(real * real + imaginary * imaginary);
        }

        // Compute the angle (theta) in radians
        public double getAngle() {
            return Math.atan2(imaginary, real);
        }

        // Display polar form as a string
        public String toPolarForm() {
            double magnitude = getMagnitude();
            double angle = getAngle(); // In radians
            return String.format("Polar Form: %.2f (cos(%.2f) + i*sin(%.2f))", magnitude, angle, angle);
        }

        // String representation of the complex number
        @Override
        public String toString() {
            if (imaginary >= 0) {
                return real + " + " + imaginary + "i";
            } else {
                return real + " - " + Math.abs(imaginary) + "i";
            }
        }
}
