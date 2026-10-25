# Strategy Pattern – Calculator
 
**Author:** Mumina Mukazhan, group SE-2530
**Assignment:** 5 – Strategy Pattern (Option B – Calculator)
 
## About
 
A console calculator that performs arithmetic using the **Strategy** behavioral design pattern. Each operation is an interchangeable algorithm behind a common interface, so the calculator contains no `if/switch` on the operation type and the operation can be switched at runtime.
 
## Pattern roles
 
| Role | Class |
|---|---|
| Strategy interface | `OperationStrategy` |
| Concrete Strategies | `AddStrategy`, `SubtractStrategy`, `MultiplyStrategy`, `DivideStrategy` |
| Context | `Calculator` |
| Client | `Main` |
 
## Project structure
 
```
.
├── README.md
└── src/
    └── calculator/
        ├── Main.java
        ├── Calculator.java
        └── strategy/
            ├── OperationStrategy.java
            ├── AddStrategy.java
            ├── SubtractStrategy.java
            ├── MultiplyStrategy.java
            └── DivideStrategy.java
```
 
## How to build and run
 
**Requirements:** JDK 17 or newer.
 
### Option 1: IntelliJ IDEA
1. Open the project folder (`File > Open`).
2. Mark `src` as *Sources Root* (right-click `src` > *Mark Directory as* > *Sources Root*).
3. Open `Main.java` and click **Run**.
### Option 2: Command line (Linux / macOS)
```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out calculator.Main
```
 
### Option 2: Command line (Windows PowerShell)
```powershell
mkdir out
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp out calculator.Main
```
 
## Usage
 
On start, the program first demonstrates runtime strategy switching:
 
```
=== Runtime strategy switching demo ===
12 + 4 = 16.0
12 - 4 = 8.0
12 * 4 = 48.0
12 / 4 = 3.0
```
 
Then it enters interactive mode. Enter an expression in the format `<number> <operator> <number>`:
 
```
> 10 + 5
= 15.0
> 7 / 0
Error: Division by zero is not allowed
> exit
```
 
Supported operators: `+`, `-`, `*`, `/`. Type `exit` to quit.
 
## How to add a new strategy
 
Example: adding a power operation. **No existing class needs to be modified.**
 
1. Create a new class in `src/calculator/strategy/`:
```java
package calculator.strategy;
 
public class PowerStrategy implements OperationStrategy {
 
    @Override
    public double execute(double left, double right) {
        return Math.pow(left, right);
    }
}
```
 
2. Register it in `Main.createOperations()` with one line (and add the import):
```java
operations.put("^", new PowerStrategy());
```
 
3. Run the program and use it: `> 2 ^ 10` gives `= 1024.0`.
## Clean Code principles applied
 
1. Conditional logic replaced by polymorphism (no `if/switch` in `Calculator`).
2. Meaningful, intention-revealing names (`AddStrategy`, `OperationStrategy`, `execute`).
3. Single Responsibility: each strategy implements exactly one algorithm.
4. Context is algorithm-agnostic: `Calculator` depends only on the `OperationStrategy` interface.
5. Easy extensibility: new strategies are added without touching existing classes (Open/Closed Principle).
See the full report for details and before/after code examples.
 
