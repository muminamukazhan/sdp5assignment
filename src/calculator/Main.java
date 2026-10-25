package calculator;

import calculator.strategy.AddStrategy;
import calculator.strategy.DivideStrategy;
import calculator.strategy.MultiplyStrategy;
import calculator.strategy.OperationStrategy;
import calculator.strategy.SubtractStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, OperationStrategy> operations = createOperations();
        Calculator calculator = new Calculator(operations.get("+"));

        demonstrateRuntimeSwitching(calculator, operations);
        runInteractiveMode(calculator, operations);
    }

    private static Map<String, OperationStrategy> createOperations() {
        Map<String, OperationStrategy> operations = new LinkedHashMap<>();
        operations.put("+", new AddStrategy());
        operations.put("-", new SubtractStrategy());
        operations.put("*", new MultiplyStrategy());
        operations.put("/", new DivideStrategy());
        return operations;
    }

    private static void demonstrateRuntimeSwitching(Calculator calculator,
                                                    Map<String, OperationStrategy> operations) {
        System.out.println("=== Runtime strategy switching demo ===");
        for (Map.Entry<String, OperationStrategy> entry : operations.entrySet()) {
            calculator.setStrategy(entry.getValue());
            System.out.println("12 " + entry.getKey() + " 4 = " + calculator.calculate(12, 4));
        }
        System.out.println();
    }

    private static void runInteractiveMode(Calculator calculator,
                                           Map<String, OperationStrategy> operations) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Interactive mode ===");
        System.out.println("Format: <number> <operator> <number>   (type 'exit' to quit)");
        System.out.println("Available operators: " + String.join(" ", operations.keySet()));

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            processLine(line, calculator, operations);
        }
    }

    private static void processLine(String line, Calculator calculator,
                                    Map<String, OperationStrategy> operations) {
        String[] parts = line.split("\\s+");
        if (parts.length != 3) {
            System.out.println("Invalid format. Example: 10 + 5");
            return;
        }

        OperationStrategy strategy = operations.get(parts[1]);
        if (strategy == null) {
            System.out.println("Unknown operator: " + parts[1]);
            return;
        }

        try {
            double left = Double.parseDouble(parts[0]);
            double right = Double.parseDouble(parts[2]);
            calculator.setStrategy(strategy);
            System.out.println("= " + calculator.calculate(left, right));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number in input");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}