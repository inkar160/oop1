public class Customer {
    private int customerId;
    private String name;
    private String membershipLevel;
    private double totalPurchases;

    public Customer(int customerId, String name, String membershipLevel, double totalPurchases) {
        this.customerId = customerId;
        this.name = name;
        this.membershipLevel = membershipLevel;
        this.totalPurchases = totalPurchases;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getTotalPurchases() { return totalPurchases; }

    public boolean isVIP() {
        return this.totalPurchases > 50000;
    }

    public void addPurchase(double amount) {
        this.totalPurchases += amount;
    }

    @Override
    public String toString() {
        return "Customer{id=" + customerId + ", name='" + name + "', level='" + membershipLevel + "', total=" + totalPurchases + "}";
    }
}