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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be less than or equal to zero");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public void displayInfo() {
        System.out.println("Product ID: " + id);
        System.out.println("Product name: " + name);
        System.out.println("Product price: " + price);
    }

    // abstract method
    public abstract String getCategory();
}
