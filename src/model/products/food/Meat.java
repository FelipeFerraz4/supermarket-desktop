package model.products.food;

import java.time.LocalDate;

public class Meat extends FoodProduct {
    private String cutType;
    private String origin;
    private boolean isOrganic;
    private String animalType;
    private String storageInstructions;

    public Meat(String cod, String name, double price, int amount,
                LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
                String cutType, String origin, boolean isOrganic, String animalType, String storageInstructions) {
        super(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo);
        this.cutType = cutType;
        this.origin = origin;
        this.isOrganic = isOrganic;
        this.animalType = animalType;
        this.storageInstructions = storageInstructions;
    }

    public void setCutType(String cutType) {
        this.cutType = cutType;
    }

    public void setOrganic(boolean organic) {
        isOrganic = organic;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getCutType() {
        return cutType;
    }

    public String getOrigin() {
        return origin;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getStorageInstructions() {
        return storageInstructions;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setStorageInstructions(String storageInstructions) {
        this.storageInstructions = storageInstructions;
    }

    @Override
    public String toString() {
        return "Meat{" +
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
                ", cutType='" + cutType + '\'' +
                ", origin='" + origin + '\'' +
                ", isOrganic=" + isOrganic +
                ", animalType='" + animalType + '\'' +
                ", storageInstructions='" + storageInstructions + '\'' +
                '}';
    }
}
