package model;

public class HouseholdProduct extends Product {

    public HouseholdProduct(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getCategory() {
        return "Household";
    }
}