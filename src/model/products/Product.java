package model.products;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Product {
    private final UUID id;
    private String cod;
    private String name;
    private double price;
    private int amount;
    private LocalDate latestStockUpdate;

    public Product(String cod, String name, double price, int amount) {
        this.id = UUID.randomUUID();
        this.cod = cod;
        this.name = name;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.latestStockUpdate = LocalDate.now();
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
                ", price=" + price +
                ", amount=" + amount +
                ", latestStockUpdate=" + latestStockUpdate +
                '}';
    }
}
