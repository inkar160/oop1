package model;

public class FoodProduct extends Product {

    public FoodProduct(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getCategory() {
        return "Food";
    }
}