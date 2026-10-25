package calculator.strategy;

public class DivideStrategy implements OperationStrategy {

    @Override
    public double execute(double left, double right) {
        if (right == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return left / right;
    }
}