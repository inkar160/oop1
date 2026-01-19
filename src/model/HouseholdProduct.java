package model;

public class HouseholdProduct extends Product {

    private String usageType;

    public HouseholdProduct(int id, String name, double price) {
        super(id, name, price);
        this.usageType = "General";
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        if (usageType == null || usageType.isEmpty()) {
            throw new IllegalArgumentException("Usage type cannot be empty");
        }
        this.usageType = usageType;
    }

    @Override
    public String getCategory() {
        return "Household product";
    }

    public void showUsage() {
        System.out.println("Usage type: " + usageType);
    }
}