package controllers;

import interfaces.IRepository;
import model.products.Product;
import repository.product.ProductRepositoryHashMap;
import services.ProductServices;

import java.time.LocalDate;
import java.util.UUID;

public class ProductControllers {
    private final ProductRepositoryHashMap repository;

    public ProductControllers() {
        this.repository = new ProductRepositoryHashMap();
    }

    public void registerBeverage(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            double volume, boolean alcoholic, String flavor, String brand) {
        ProductServices.registerBeverage(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand, repository);
    }

    public void updateBeverage(UUID id,
                               String cod, String name, double price, int amount,
                               LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
                               double volume, boolean alcoholic, String flavor, String brand) {
        ProductServices.updateBeverage(id, cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand, repository);
    }

    public void registerHygieneProduct(
            String cod, String name, double price, int amount,
            String type, String brand, boolean forSensitiveSkin,
            String usageInstructions, boolean toxic, String scent, double volume) {
        ProductServices.registerHygieneProduct(cod, name, price, amount, type, brand, forSensitiveSkin,
                usageInstructions, toxic, scent, volume, repository);
    }

    public void updateHygieneProduct(UUID id,
                                     String cod, String name, double price, int amount,
                                     String type, String brand, boolean forSensitiveSkin,
                                     String usageInstructions, boolean toxic, String scent, double volume) {
        ProductServices.updateHygieneProduct(id, cod, name, price, amount, type, brand, forSensitiveSkin,
                usageInstructions, toxic, scent, volume, repository);
    }

    public void registerUtensil(
            String cod, String name, double price, int amount,
            String material, String category, boolean isReusable, String size) {
        ProductServices.registerUtensil(cod, name, price, amount, material, category, isReusable, size, repository);
    }

    public void updateUtensil(UUID id,
                              String cod, String name, double price, int amount,
                              String material, String category, boolean isReusable, String size) {
        ProductServices.updateUtensil(id, cod, name, price, amount, material, category, isReusable, size, repository);
    }


    public void deleteProduct(UUID id) {
        repository.delete(id);
    }
}
