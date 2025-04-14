package model.products;

import java.time.LocalDate;

public class Meat extends FoodProduct {
    private final String cutType;
    private String origin;
    private final boolean isOrganic;
    private final String animalType;
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
        return super.toString() +
                String.format(" | Cut: %s | Origin: %s | Organic: %s | Animal: %s | Storage: %s",
                        cutType, origin, isOrganic ? "Yes" : "No", animalType, storageInstructions);
    }
}
