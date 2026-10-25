package calculator;

import calculator.strategy.OperationStrategy;

import java.util.Objects;

public class Calculator {

    private OperationStrategy strategy;

    public Calculator(OperationStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy must not be null");
    }

    public double calculate(double left, double right) {
        return strategy.execute(left, right);
    }
}