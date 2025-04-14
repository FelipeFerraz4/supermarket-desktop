package model.products.food;

import java.time.LocalDate;

public class ProcessedFood extends FoodProduct {
    private String category;
    private String brand;
    private boolean containsPreservatives;
    private String cookingInstructions;

    public ProcessedFood(String cod, String name, double price, int amount,
                         LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
                         String category, String brand, boolean containsPreservatives, String cookingInstructions) {
        super(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo);
        this.category = category;
        this.brand = brand;
        this.containsPreservatives = containsPreservatives;
        this.cookingInstructions = cookingInstructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isContainsPreservatives() {
        return containsPreservatives;
    }

    public void setContainsPreservatives(boolean containsPreservatives) {
        this.containsPreservatives = containsPreservatives;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    @Override
    public String toString() {
        return "ProcessedFood{" +
                "id=" + getId() + '\'' +
                ", cod='" + getCod() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() + '\'' +
                ", amount=" + getAmount() + '\'' +
                ", latestStockUpdate=" + getLatestStockUpdate() + '\'' +
                ", expirationDate=" + getExpirationDate() +
                ", weight=" + getWeight() +
                ", refrigerated=" + isRefrigerated() +
                ", nutritionalInfo='" + getNutritionalInfo() + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", containsPreservatives=" + containsPreservatives +
                ", cookingInstructions='" + cookingInstructions + '\'' +
                '}';
    }
}
