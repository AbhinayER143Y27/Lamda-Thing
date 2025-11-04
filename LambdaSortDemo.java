import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * LambdaSortDemo demonstrates sorting a list of custom objects (Employee) 
 * using concise lambda expressions for various comparison criteria.
 */
public class LambdaSortDemo {

    // Helper class for data structure (should be a separate file, but kept here for one-file mandate)
    static class Employee {
        private final String name;
        private final int age;
        private final double salary; // Use double for financial calculations is bad practice, but good for demo

        public Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return String.format("%-15s | Age: %-3d | Salary: $%,.2f", name, age, salary);
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice Smith", 34, 75000.00));
        employees.add(new Employee("Charlie Brown", 22, 55000.00));
        employees.add(new Employee("Bob Johnson", 41, 120000.00));
        employees.add(new Employee("Alice Williams", 34, 62000.00)); // Duplicate name/age to test tie-breaking

        System.out.println("--- Original List ---");
        employees.forEach(System.out::println);

        // 1. Sort by Name (Alphabetical)
        // Lambda expression (e1, e2) -> e1.getName().compareTo(e2.getName())
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        System.out.println("\n--- 1. Sorted by Name (Lambda) ---");
        employees.forEach(System.out::println);

        // 2. Sort by Salary (Highest to Lowest)
        // Using Comparator.comparingDouble() for cleaner numeric sorting, then reversed()
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        System.out.println("\n--- 2. Sorted by Salary (Stream Utility + Method Reference) ---");
        employees.forEach(System.out::println);

        // 3. Sort by Age (Ascending), then by Name (for tie-breaking)
        // This chain is clean, professional code.
        employees.sort(Comparator
                .comparingInt(Employee::getAge)
                .thenComparing(Employee::getName));
        
        System.out.println("\n--- 3. Sorted by Age then by Name (Chained Comparator) ---");
        employees.forEach(System.out::println);
    }
}
