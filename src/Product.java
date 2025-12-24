public class Product {
    private String name;
    private double price;
    private int stockQuantity;
    private String category;

    public Product(String name, double price, int stockQuantity, String category) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public Product() {
        this.name = "Unknown";
        this.price = 0.0;
        this.stockQuantity = 0;
        this.category = "General";
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public void restock(int amount) {
        this.stockQuantity += amount;
    }

    public boolean isInStock() {
        return this.stockQuantity > 0;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", stock=" + stockQuantity + ", category='" + category + "'}";
    }
}