package model.products;

public class HygieneProduct extends Product{
    private String type; // e.g., soap, disinfectant
    private String brand;
    private boolean forSensitiveSkin;
    private String usageInstructions;
    private boolean toxic;
    private String scent;
    private double volume;

    public HygieneProduct(
            String cod, String name, double price, int amount,
            String type, String brand, boolean forSensitiveSkin,
            String usageInstructions, boolean toxic, String scent, double volume) {
        super(cod, name, price, amount);
        this.type = type;
        this.brand = brand;
        this.forSensitiveSkin = forSensitiveSkin;
        this.usageInstructions = usageInstructions;
        this.toxic = toxic;
        this.scent = scent;
        this.volume = volume;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setToxic(boolean toxic) {
        this.toxic = toxic;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public boolean isForSensitiveSkin() {
        return forSensitiveSkin;
    }

    public String getUsageInstructions() {
        return usageInstructions;
    }

    public boolean isToxic() {
        return toxic;
    }

    public String getScent() {
        return scent;
    }

    public double getVolume() {
        return volume;
    }

    public void setForSensitiveSkin(boolean forSensitiveSkin) {
        this.forSensitiveSkin = forSensitiveSkin;
    }

    public void setUsageInstructions(String usageInstructions) {
        this.usageInstructions = usageInstructions;
    }

    @Override
    public String toString() {
        return "HygieneProduct{" +
                "id=" + getId() + '\'' +
                ", cod='" + getCod() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() + '\'' +
                ", amount=" + getAmount() + '\'' +
                ", latestStockUpdate=" + getLatestStockUpdate() + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", forSensitiveSkin=" + forSensitiveSkin +
                ", usageInstructions='" + usageInstructions + '\'' +
                ", toxic=" + toxic +
                ", scent='" + scent + '\'' +
                ", volume=" + volume +
                '}';
    }
}
