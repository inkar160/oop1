import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // test data
        products.add(new Product(1, "Sugar", 400, 10));
        products.add(new FreshProduct(2, "Milk", 500, 5, 2));
        products.add(new PackagedProduct(3, "Rice", 800, 7, "Makfa"));

        boolean run = true;

        while (run) {
            System.out.println("\n=== GROCERY STORE ===");
            System.out.println("1. Add product");
            System.out.println("2. View products");
            System.out.println("3. Polymorphism demo");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    polymorphismDemo();
                    break;
                case 0:
                    run = false;
                    break;
            }
        }
    }

    private static void addProduct() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        System.out.print("Quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine();

        products.add(new Product(0, name, price, qty));
        System.out.println("Added!");
    }

    private static void viewProducts() {
        for (Product p : products) {
            System.out.println(p);

            if (p instanceof FreshProduct) {
                System.out.println("  (this is fresh product)");
            }
        }
    }

    private static void polymorphismDemo() {
        System.out.println("\n--- SAME METHOD, DIFFERENT RESULT ---");
        for (Product p : products) {
            p.showInfo(); // POLYMORPHISM
        }
    }
}