package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private ArrayList<Product> products;
    private Scanner scanner;

    public MenuManager() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("===== GROCERY STORE MENU =====");
        System.out.println("1. Add food product");
        System.out.println("2. Add household product");
        System.out.println("3. Show all products");
        System.out.println("0. Exit");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Choose option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    addFoodProduct();
                } else if (choice == 2) {
                    addHouseholdProduct();
                } else if (choice == 3) {
                    showProducts();
                } else if (choice == 0) {
                    running = false;
                    System.out.println("Program finished");
                } else {
                    System.out.println("Wrong menu option");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addFoodProduct() {
        System.out.print("Enter id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        FoodProduct food = new FoodProduct(id, name, price);
        products.add(food);

        System.out.println("Food product added");
    }

    private void addHouseholdProduct() {
        System.out.print("Enter id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        HouseholdProduct household = new HouseholdProduct(id, name, price);
        products.add(household);

        System.out.println("Household product added");
    }

    private void showProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in store");
        }

        for (Product product : products) {
            product.displayInfo();
            System.out.println("Category: " + product.getCategory());
            System.out.println("----------------------");
        }
    }
}
