package menu;

import model.*;
import java.util.ArrayList;

public class MenuManager implements Menu {

    private ArrayList<Product> products = new ArrayList<>();

    @Override
    public void run() {

        products.add(new FoodProduct(1, "Milk", 450));
        products.add(new HouseholdProduct(2, "Soap", 300));

        for (Product product : products) {
            product.displayInfo();
            System.out.println("Category: " + product.getCategory());
        }
    }
}