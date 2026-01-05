public class PackagedProduct extends Product {

    private String brand;

    public PackagedProduct(int id, String name, double price, int quantity, String brand) {
        super(id, name, price, quantity);
        this.brand = brand;
    }

    @Override
    public void showInfo() {
        System.out.println("Packaged product: " + name +
                ", brand: " + brand);
    }
}