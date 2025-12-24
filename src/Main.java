public class Main {
    public static void main(String[] args) {
        System.out.println("=== Grocery Store Management System ===\n");

        Product p1 = new Product("Milk", 500.0, 10, "Dairy");
        Product p2 = new Product("Bread", 150.0, 5, "Bakery");
        Customer c1 = new Customer(1, "Alisher", "Regular", 10000.0);
        Customer c2 = new Customer(2, "Elena", "Gold", 60000.0);
        Sale s1 = new Sale(101, "Milk", 500.0, 2);

        System.out.println("--- Initial State ---");
        System.out.println(p1);
        System.out.println(c2);
        System.out.println();

        System.out.println("--- Testing Setters/Getters ---");
        p1.setPrice(550.0);
        System.out.println("New price for " + p1.getName() + ": " + p1.getPrice());
        System.out.println();

        System.out.println("--- Testing Logic Methods ---");

        System.out.println("Before restock: " + p2.getStockQuantity());
        p2.restock(20);
        System.out.println("After restock: " + p2.getStockQuantity());

        System.out.println(c1.getName() + " is VIP: " + c1.isVIP());
        System.out.println(c2.getName() + " is VIP: " + c2.isVIP());

        System.out.println("Sale total: " + s1.calculateTotal());
        s1.applyDiscount(10);
        System.out.println("After 10% discount: " + s1.calculateTotal());

        System.out.println("\n=== Program Complete ===");
    }
}