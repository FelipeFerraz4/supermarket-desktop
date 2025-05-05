package model.products;

public class Utensil extends Product{
    private String material;
    private String category;
    private boolean isReusable;
    private String size;

    public Utensil(
            String cod, String name, double price, int amount,
            String material, String category, boolean isReusable, String size) {
        super(cod, name, price, amount, ProductType.UTENSIL);
        this.material = material;
        this.category = category;
        this.isReusable = isReusable;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isReusable() {
        return isReusable;
    }

    public void setReusable(boolean reusable) {
        isReusable = reusable;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Utensil{" +
                "id=" + getId() + '\'' +
                ", cod='" + getCod() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() + '\'' +
                ", type=" + getTypeProduct() + '\'' +
                ", amount=" + getAmount() + '\'' +
                ", latestStockUpdate=" + getLatestStockUpdate() + '\'' +
                ", material='" + material + '\'' +
                ", category='" + category + '\'' +
                ", isReusable=" + isReusable +
                ", size='" + size + '\'' +
                '}';
    }
}
