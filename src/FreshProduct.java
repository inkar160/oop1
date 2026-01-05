public class FreshProduct extends Product {

    private int days;

    public FreshProduct(int id, String name, double price, int quantity, int days) {
        super(id, name, price, quantity);
        this.days = days;
    }

    @Override
    public void showInfo() {
        System.out.println("Fresh product: " + name +
                ", expires in " + days + " days");
    }
}