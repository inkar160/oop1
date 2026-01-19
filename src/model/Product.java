package model;

public abstract class Product {

    protected int id;
    protected String name;
    protected double price;

    public Product(int id, String name, double price) {
        setId(id);
        setName(name);
        setPrice(price);
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void displayInfo() {
        System.out.println(id + " | " + name + " | " + price);
    }

    // abstract method
    public abstract String getCategory();
}