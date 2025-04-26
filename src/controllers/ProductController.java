package controllers;

import model.products.Product;
import repository.product.ProductRepositoryHashMap;
import services.ProductServices;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ProductController {
    private final ProductRepositoryHashMap repository;

    public ProductController() {
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

    public void registerProcessedFood(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String category, String brand, boolean containsPreservatives, String cookingInstructions) {

        ProductServices.registerProcessedFood(
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                category, brand, containsPreservatives, cookingInstructions,
                repository
        );
    }

    public void updateProcessedFood(
            UUID id,
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String category, String brand, boolean containsPreservatives, String cookingInstructions) {

        ProductServices.updateProcessedFood(
                id,
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                category, brand, containsPreservatives, cookingInstructions,
                repository
        );
    }

    public void registerMeat(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String cutType, String origin, boolean isOrganic, String animalType, String storageInstructions) {

        ProductServices.registerMeat(
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                cutType, origin, isOrganic, animalType, storageInstructions,
                repository
        );
    }

    public void updateMeat(
            UUID id,
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String origin, String storageInstructions) {

        ProductServices.updateMeat(
                id,
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                origin, storageInstructions,
                repository
        );
    }

    public void registerFruit(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String variety, String origin, boolean seasonal, String packagingType) {

        ProductServices.registerFruit(
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                variety, origin, seasonal, packagingType,
                repository
        );
    }

    public void updateFruit(
            UUID id,
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String variety, String origin, boolean seasonal, String packagingType) {

        ProductServices.updateFruit(
                id,
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                variety, origin, seasonal, packagingType,
                repository
        );
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

    public Product searchById(UUID id) {
        return repository.searchById(id);
    }

    public Product searchByName(String name) {
        return repository.searchByName(name);
    }

    public Product searchByCod(String cod) {
        return repository.searchByCod(cod);
    }

    public void deleteProduct(UUID id) {
        repository.delete(id);
    }

    public List<Product> getAllProducts() {
        return repository.search();
    }

    public List<Product> getProductsByCategory(Class<?> categoryClass) {
        return repository.getByType(categoryClass);
    }

}
