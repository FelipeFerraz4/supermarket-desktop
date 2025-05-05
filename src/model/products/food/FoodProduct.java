package model.products.food;

import model.products.Product;
import model.products.ProductType;

import java.time.LocalDate;

public abstract class FoodProduct extends Product {
    private LocalDate expirationDate;
    private double weight;
    private boolean refrigerated;
    private String nutritionalInfo;

    public FoodProduct(String cod, String name, double price, int amount,
                       LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo, ProductType type) {
        super(cod, name, price, amount, type);
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
        return "FoodProduct{" +
                "id=" + getId() + '\'' +
                ", cod='" + getCod() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() + '\'' +
                ", type=" + getTypeProduct() + '\'' +
                ", amount=" + getAmount() + '\'' +
                ", latestStockUpdate=" + getLatestStockUpdate() + '\'' +
                ", expirationDate=" + expirationDate +
                ", weight=" + weight +
                ", refrigerated=" + refrigerated +
                ", nutritionalInfo='" + nutritionalInfo + '\'' +
                '}';
    }
}
