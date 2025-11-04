import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * StreamAggregationDemo processes a product dataset using advanced Stream 
 * operations for grouping, finding maximum values, and calculating averages.
 */
public class StreamAggregationDemo {

    // Helper class for data structure
    static class Product {
        private final String name;
        private final String category;
        private final double price;

        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return String.format("%s (%.2f)", name, price);
        }
    }

    public static void main(String[] args) {
        // Large-ish sample dataset
        List<Product> products = Arrays.asList(
            new Product("Laptop Pro", "Electronics", 1500.00),
            new Product("Mouse Pad XL", "Accessories", 15.50),
            new Product("4K Monitor", "Electronics", 450.00),
            new Product("Mechanical Keyboard", "Accessories", 130.00),
            new Product("Java Book", "Books", 55.00),
            new Product("Wireless Mouse", "Accessories", 45.00),
            new Product("E-Reader", "Electronics", 280.00),
            new Product("Algorithm Guide", "Books", 72.50)
        );

        System.out.println("--- Dataset Size: " + products.size() + " Products ---");

        // 1. Grouping and Aggregating (Average and Count)
        // Use Collectors.groupingBy with Collectors.summarizingDouble
        Map<String, DoubleSummaryStatistics> statsByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.summarizingDouble(Product::getPrice)
            ));

        System.out.println("\n--- 1. Average Price and Count by Category ---");
        statsByCategory.forEach((category, stats) -> {
            System.out.printf("Category: %s | Count: %d | Avg Price: $%,.2f\n", 
                              category, stats.getCount(), stats.getAverage());
        });

        // 2. Grouping and Finding Maximum (Most Expensive Product)
        // Use Collectors.groupingBy with Collectors.maxBy
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
            ));
        
        System.out.println("\n--- 2. Most Expensive Product per Category ---");
        mostExpensiveByCategory.forEach((category, productOptional) -> {
            // Unwrap the Optional. If it's empty, something is fundamentally broken.
            String productInfo = productOptional.map(p -> p.getName() + " ($" + p.getPrice() + ")")
                                                .orElse("ERROR: No product found.");
            System.out.printf("Category: %s | Max Product: %s\n", category, productInfo);
        });
    }
}
