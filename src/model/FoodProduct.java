package model;

public class FoodProduct extends Product {

    private boolean expired;

    public FoodProduct(int id, String name, double price) {
        super(id, name, price);
        this.expired = false;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String getCategory() {
        return "Food product";
    }

    public void checkExpiration() {
        if (expired) {
            System.out.println(name + " is expired");
        } else {
            System.out.println(name + " is fresh");
        }
    }
}