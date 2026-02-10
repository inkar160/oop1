package menu;

import model.*;
import database.ProductDAO;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {
    private ProductDAO productDAO;
    private Scanner scanner;

    public MenuManager() {
        this.productDAO = new ProductDAO();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n===== GROCERY STORE MENU =====");
        System.out.println("1. Add food product");
        System.out.println("2. Add household product");
        System.out.println("3. Show all products");
        System.out.println("4. Update product");
        System.out.println("5. Delete product");
        System.out.println("6. Search by name");
        System.out.println("7. Search by price range");
        System.out.println("8. Search expensive products (min price)");
        System.out.println("9. Check food expiration");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addFoodProduct();
                    case 2 -> addHouseholdProduct();
                    case 3 -> showAllProducts();
                    case 4 -> updateProduct();
                    case 5 -> deleteProduct();
                    case 6 -> searchByName();
                    case 7 -> searchByPriceRange();
                    case 8 -> searchByMinPrice();
                    case 9 -> checkFoodExpiration();
                    case 0 -> {
                        running = false;
                        System.out.println("Program finished. Goodbye!");
                    }
                    default -> System.out.println("Wrong menu option");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void addFoodProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Is it expired? (true/false): ");
            boolean expired = Boolean.parseBoolean(scanner.nextLine());

            FoodProduct food = new FoodProduct(0, name, price);
            food.setExpired(expired);

            boolean success = productDAO.insertProduct(food);
            if (success) {
                System.out.println("Food product added to database!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }

    private void addHouseholdProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter usage type: ");
            String usageType = scanner.nextLine();

            HouseholdProduct household = new HouseholdProduct(0, name, price);
            household.setUsageType(usageType);

            boolean success = productDAO.insertProduct(household);
            if (success) {
                System.out.println("Household product added to database!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }

    private void showAllProducts() {
        List<Product> products = productDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println(" No products in the store");
            return;
        }

        System.out.println("\n===== ALL PRODUCTS IN STORE =====");
        for (Product product : products) {
            product.displayInfo();
            System.out.println("Category: " + product.getCategory());

            if (product instanceof FoodProduct) {
                System.out.println("Expired: " + ((FoodProduct) product).isExpired());
            } else if (product instanceof HouseholdProduct) {
                System.out.println("Usage Type: " + ((HouseholdProduct) product).getUsageType());
            }

            System.out.println("----------------------");
        }
        System.out.println("Total products: " + products.size());
    }

    private void updateProduct() {
        try {
            System.out.print("Enter product ID to update: ");
            int productId = Integer.parseInt(scanner.nextLine());

            Product existingProduct = productDAO.getProductById(productId);
            if (existingProduct == null) {
                System.out.println("No product found with ID: " + productId);
                return;
            }

            System.out.println("\nCurrent product information:");
            existingProduct.displayInfo();

            System.out.print("\nEnter new name [" + existingProduct.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existingProduct.getName();
            }

            System.out.print("Enter new price [" + existingProduct.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            double newPrice = priceInput.trim().isEmpty()
                    ? existingProduct.getPrice()
                    : Double.parseDouble(priceInput);

            boolean success;
            if (existingProduct instanceof FoodProduct) {
                FoodProduct foodProduct = (FoodProduct) existingProduct;
                System.out.print("Is it expired? [" + foodProduct.isExpired() + "] (true/false): ");
                String expiredInput = scanner.nextLine();
                boolean newExpired = expiredInput.trim().isEmpty()
                        ? foodProduct.isExpired()
                        : Boolean.parseBoolean(expiredInput);

                FoodProduct updatedProduct = new FoodProduct(productId, newName, newPrice);
                updatedProduct.setExpired(newExpired);
                success = productDAO.updateProduct(updatedProduct);

            } else if (existingProduct instanceof HouseholdProduct) {
                HouseholdProduct householdProduct = (HouseholdProduct) existingProduct;
                System.out.print("Enter new usage type [" + householdProduct.getUsageType() + "]: ");
                String newUsageType = scanner.nextLine();
                if (newUsageType.trim().isEmpty()) {
                    newUsageType = householdProduct.getUsageType();
                }

                HouseholdProduct updatedProduct = new HouseholdProduct(productId, newName, newPrice);
                updatedProduct.setUsageType(newUsageType);
                success = productDAO.updateProduct(updatedProduct);

            } else {
                System.out.println("Unknown product type");
                return;
            }

            if (success) {
                System.out.println("Product updated successfully!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }

    private void deleteProduct() {
        try {
            System.out.print("Enter product ID to delete: ");
            int productId = Integer.parseInt(scanner.nextLine());

            Product product = productDAO.getProductById(productId);
            if (product == null) {
                System.out.println("No product found with ID: " + productId);
                return;
            }

            System.out.println("\nProduct to delete:");
            product.displayInfo();

            System.out.print("\nAre you sure you want to delete? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                boolean success = productDAO.deleteProduct(productId);
                if (success) {
                    System.out.println("Product deleted successfully!");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }

    private void searchByName() {
        System.out.print("Enter product name (or part of name): ");
        String name = scanner.nextLine();

        List<Product> results = productDAO.searchByName(name);

        if (results.isEmpty()) {
            System.out.println(" No products found with name containing: " + name);
        } else {
            System.out.println("\n===== SEARCH RESULTS =====");
            for (Product product : results) {
                product.displayInfo();
                System.out.println("Category: " + product.getCategory());
                System.out.println("----------------------");
            }
            System.out.println("Found: " + results.size() + " products");
        }
    }

    private void searchByPriceRange() {
        try {
            System.out.print("Enter minimum price: ");
            double minPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter maximum price: ");
            double maxPrice = Double.parseDouble(scanner.nextLine());

            List<Product> results = productDAO.searchByPriceRange(minPrice, maxPrice);

            if (results.isEmpty()) {
                System.out.println("No products found in price range: " + minPrice + " - " + maxPrice);
            } else {
                System.out.println("\n===== PRODUCTS IN PRICE RANGE =====");
                for (Product product : results) {
                    System.out.println(product.getName() + " - " + product.getPrice() + " KZT");
                }
                System.out.println("Found: " + results.size() + " products");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }

    private void searchByMinPrice() {
        try {
            System.out.print("Enter minimum price: ");
            double minPrice = Double.parseDouble(scanner.nextLine());

            List<Product> results = productDAO.searchByMinPrice(minPrice);

            if (results.isEmpty()) {
                System.out.println("No products found with price >= " + minPrice);
            } else {
                System.out.println("\n===== EXPENSIVE PRODUCTS (>= " + minPrice + ") =====");
                for (Product product : results) {
                    System.out.println(product.getName() + " - " + product.getPrice() + " KZT");
                }
                System.out.println("Found: " + results.size() + " products");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }

    private void checkFoodExpiration() {
        List<Product> allProducts = productDAO.getAllProducts();
        int expiredCount = 0;

        System.out.println("\n===== EXPIRATION CHECK =====");
        for (Product product : allProducts) {
            if (product instanceof FoodProduct) {
                FoodProduct food = (FoodProduct) product;
                food.checkExpiration();
                if (food.isExpired()) {
                    expiredCount++;
                }
            }
        }

        if (expiredCount == 0) {
            System.out.println("All food products are fresh!");
        } else {
            System.out.println("Found " + expiredCount + " expired food products");
        }
    }

    private void showProducts() {
        showAllProducts();
    }
}