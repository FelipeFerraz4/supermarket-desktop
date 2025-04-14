package model.products;

import java.time.LocalDate;

public abstract class FoodProduct extends Product {
    private LocalDate expirationDate;
    private double weight;
    private boolean refrigerated;
    private String nutritionalInfo;

    public FoodProduct(String cod, String name, double price, int amount,
                       LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo) {
        super(cod, name, price, amount);
        this.expirationDate = expirationDate;
        this.weight = weight;
        this.refrigerated = refrigerated;
        this.nutritionalInfo = nutritionalInfo;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    public String getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }

    public void setNutritionalInfo(String nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " | Expiration: %s | Weight: %.2fkg | Refrigerated: %s | Nutrition: %s",
                expirationDate, weight, refrigerated ? "Yes" : "No", nutritionalInfo);
    }
}
