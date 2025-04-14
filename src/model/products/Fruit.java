package model.products;

import java.time.LocalDate;

public class Fruit extends FoodProduct {
    private String variety;
    private String origin;
    private boolean seasonal;
    private String packagingType;

    public Fruit(String cod, String name, double price, int amount,
                 LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
                 String variety, String origin, boolean seasonal, String packagingType) {
        super(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo);
        this.variety = variety;
        this.origin = origin;
        this.seasonal = seasonal;
        this.packagingType = packagingType;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isSeasonal() {
        return seasonal;
    }

    public void setSeasonal(boolean seasonal) {
        this.seasonal = seasonal;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    @Override
    public String toString() {
        return "Fruit{" +
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
                ", variety='" + variety + '\'' +
                ", origin='" + origin + '\'' +
                ", seasonal=" + seasonal +
                ", packagingType='" + packagingType + '\'' +
                '}';
    }
}
