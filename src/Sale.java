public class Sale {
    private int saleId;
    private String productName;
    private double amount;
    private int quantity;

    public Sale(int saleId, String productName, double amount, int quantity) {
        this.saleId = saleId;
        this.productName = productName;
        this.amount = amount;
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return this.amount * this.quantity;
    }

    public void applyDiscount(double percent) {
        this.amount -= (this.amount * percent / 100);
    }

    @Override
    public String toString() {
        return "Sale{id=" + saleId + ", product='" + productName + "', total=" + calculateTotal() + "}";
    }
}