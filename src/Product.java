public class Product {

    protected int id;
    protected String name;
    protected double price;
    protected int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        setPrice(price);
        setQuantity(quantity);
    }

    public void setPrice(double price) {
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }


    public void showInfo() {
        System.out.println("Product: " + name + ", price: " + price);
    }

    @Override
    public String toString() {
        return "Product: " + name +
                ", price=" + price +
                ", quantity=" + quantity;
    }
}