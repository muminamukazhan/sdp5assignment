package calculator.strategy;

public class AddStrategy implements OperationStrategy {

    @Override
    public double execute(double left, double right) {
        return left + right;
    }
}