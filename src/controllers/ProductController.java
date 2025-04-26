package controllers;

import dtos.*;
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
            BeverageDTO beverageDTO) {
        ProductServices.registerBeverage(beverageDTO, repository);
    }

    public void updateBeverage(UUID id, BeverageDTO beverageDTO) {
        ProductServices.updateBeverage(id, beverageDTO, repository);
    }

    public void registerProcessedFood(ProcessedFoodDTO processedFoodDTO) {
        ProductServices.registerProcessedFood(processedFoodDTO, repository);
    }

    public void updateProcessedFood(UUID id,ProcessedFoodDTO processedFoodDTO) {
        ProductServices.updateProcessedFood(id, processedFoodDTO, repository);
    }

    public void registerMeat(MeatDTO meatDTO) {
        ProductServices.registerMeat(meatDTO, repository);
    }

    public void updateMeat(UUID id, MeatDTO meatDTO) {
        ProductServices.updateMeat(id, meatDTO, repository);
    }

    public void registerFruit(FruitDTO fruitDTO) {
        ProductServices.registerFruit(fruitDTO, repository);
    }

    public void updateFruit(UUID id, FruitDTO fruitDTO) {
        ProductServices.updateFruit(id, fruitDTO,repository);
    }

    public void registerHygieneProduct(HygieneProductDTO hygieneProductDTO) {
        ProductServices.registerHygieneProduct(hygieneProductDTO, repository);
    }

    public void updateHygieneProduct(UUID id, HygieneProductDTO hygieneProductDTO) {
        ProductServices.updateHygieneProduct(id, hygieneProductDTO, repository);
    }

    public void registerUtensil(HygieneProductDTO hygieneProductDTO) {
        ProductServices.registerUtensil(hygieneProductDTO, repository);
    }

    public void updateUtensil(UUID id, UtensilDTO utensilDTO) {
        ProductServices.updateUtensil(id, utensilDTO, repository);
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
