package model.products;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class Product {
    private final UUID id;
    private String cod;
    private String name;
    private double price;
    private int amount;
    private LocalDate latestStockUpdate;
    private final ProductType typeProduct;

    public Product(String cod, String name, double price, int amount, ProductType type) {
        this.id = UUID.randomUUID();
        this.cod = cod;
        this.name = name;
        this.typeProduct = type;
        this.price = price;
        this.amount = amount;
        this.latestStockUpdate = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public String getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getLatestStockUpdate() {
        return latestStockUpdate;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        this.price = price;
    }

    public void setAmount(int amount) throws IllegalArgumentException{
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be non-negative.");
        }
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public ProductType getTypeProduct() {
        return this.typeProduct;
    }

    public void updateStock(int change) {
        this.amount += change;
        this.latestStockUpdate = LocalDate.now();
    }

    public double calculateTotal() {
        return price * amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", cod='" + cod + '\'' +
                ", name='" + name + '\'' +
                ", type=" + typeProduct +
                ", price=" + price +
                ", amount=" + amount +
                ", latestStockUpdate=" + latestStockUpdate +
                '}';
    }
}
