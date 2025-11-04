import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StreamFilterSortDemo uses the Java Stream API and lambda expressions to 
 * efficiently filter a list of Student objects, sort them by marks, and 
 * map them to a list of names.
 */
public class StreamFilterSortDemo {

    // Helper class for data structure
    static class Student {
        private final String name;
        private final int marks;
        private final String subject;

        public Student(String name, int marks, String subject) {
            this.name = name;
            this.marks = marks;
            this.subject = subject;
        }

        public String getName() { return name; }
        public int getMarks() { return marks; }

        @Override
        public String toString() {
            return String.format("%s (%d%%) in %s", name, marks, subject);
        }
    }

    public static void main(String[] args) {
        // Sample dataset
        List<Student> students = Arrays.asList(
            new Student("Jake P.", 88, "Physics"),
            new Student("Maya K.", 72, "Chemistry"),
            new Student("Leo T.", 95, "Physics"),
            new Student("Zoe A.", 68, "Math"),
            new Student("Kai W.", 80, "Math"),
            new Student("Mia B.", 76, "Chemistry")
        );

        System.out.println("--- All Students ---");
        students.forEach(System.out::println);

        // Objective: Filter students with marks > 75%, sort them by marks (highest first), 
        // and then collect only their names.
        
        // This is the entire processing pipeline. Study it.
        List<String> topPerformerNames = students.stream()
            // 1. Filtering: Keep only students scoring above 75.
            .filter(s -> s.getMarks() > 75) // Lambda expression for the Predicate
            
            // 2. Sorting: Sort by marks in descending order (highest first).
            .sorted((s1, s2) -> Integer.compare(s2.getMarks(), s1.getMarks())) // Lambda expression for the Comparator
            
            // 3. Mapping: Transform the Student object stream into a String (name) stream.
            .map(Student::getName) // Method reference for the Function
            
            // 4. Terminal Operation: Collect the resulting names into a new List.
            .collect(Collectors.toList());

        System.out.println("\n--- Results: Filtered (>75%), Sorted (Marks Descending), Names Collected ---");
        
        if (topPerformerNames.isEmpty()) {
             System.out.println("No students met the criteria. Raise the bar higher.");
        } else {
            topPerformerNames.forEach(name -> System.out.println("-> " + name));
        }
    }
}
