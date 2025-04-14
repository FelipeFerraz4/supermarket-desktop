package model.products;

import java.time.LocalDate;

public class Beverage extends FoodProduct {
    private double volume;
    private boolean alcoholic;
    private String flavor;
    private String brand;

    public Beverage(String cod, String name, double price, int amount,
                    LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
                    double volume, boolean alcoholic, String flavor, String brand) {
        super(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo);
        this.volume = volume;
        this.alcoholic = alcoholic;
        this.flavor = flavor;
        this.brand = brand;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" | Volume: %.2fL | Alcoholic: %s | Flavor: %s | Brand: %s",
                        volume, alcoholic ? "Yes" : "No", flavor, brand);
    }
}
