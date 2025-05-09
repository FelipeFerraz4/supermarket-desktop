package controllers;

import dtos.*;
import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;
import interfaces.IProductRepository;
import model.products.Product;
import repository.product.ProductRepositoryHashMap;
import services.ProductServices;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ProductController {
    private final IProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepositoryHashMap();
        try {
            this.initializeProducts();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing products", e);
        }
    }

    public void registerBeverage(BeverageDTO beverageDTO) throws IllegalArgumentException, DuplicateEntityException {
        ProductServices.registerBeverage(beverageDTO, repository);
    }

    public void updateBeverage(UUID id, BeverageDTO beverageDTO) throws IllegalArgumentException, EntityNotFoundException {
        ProductServices.updateBeverage(id, beverageDTO, repository);
    }

    public void registerProcessedFood(ProcessedFoodDTO processedFoodDTO) throws IllegalArgumentException, DuplicateEntityException {
        ProductServices.registerProcessedFood(processedFoodDTO, repository);
    }

    public void updateProcessedFood(UUID id,ProcessedFoodDTO processedFoodDTO) throws IllegalArgumentException, EntityNotFoundException {
        ProductServices.updateProcessedFood(id, processedFoodDTO, repository);
    }

    public void registerMeat(MeatDTO meatDTO) throws IllegalArgumentException, DuplicateEntityException {
        ProductServices.registerMeat(meatDTO, repository);
    }

    public void updateMeat(UUID id, MeatDTO meatDTO) throws IllegalArgumentException, EntityNotFoundException {
        ProductServices.updateMeat(id, meatDTO, repository);
    }

    public void registerFruit(FruitDTO fruitDTO) throws IllegalArgumentException, DuplicateEntityException {
        ProductServices.registerFruit(fruitDTO, repository);
    }

    public void updateFruit(UUID id, FruitDTO fruitDTO) throws IllegalArgumentException, EntityNotFoundException {
        ProductServices.updateFruit(id, fruitDTO,repository);
    }

    public void registerHygieneProduct(HygieneProductDTO hygieneProductDTO) throws IllegalArgumentException, DuplicateEntityException {
        ProductServices.registerHygieneProduct(hygieneProductDTO, repository);
    }

    public void updateHygieneProduct(UUID id, HygieneProductDTO hygieneProductDTO) throws IllegalArgumentException, EntityNotFoundException {
        ProductServices.updateHygieneProduct(id, hygieneProductDTO, repository);
    }

    public void registerUtensil(UtensilDTO utensilDTO) throws IllegalArgumentException, DuplicateEntityException {
        ProductServices.registerUtensil(utensilDTO, repository);
    }

    public void updateUtensil(UUID id, UtensilDTO utensilDTO) throws IllegalArgumentException, EntityNotFoundException {
        ProductServices.updateUtensil(id, utensilDTO, repository);
    }

    public Product searchById(UUID id) throws IllegalArgumentException {
        return repository.searchById(id);
    }

    public Product searchByName(String name) throws IllegalArgumentException {
        return repository.searchByName(name);
    }

    public Product searchByCod(String cod) throws IllegalArgumentException{
        return repository.searchByCod(cod);
    }

    public void deleteProduct(UUID id) throws IllegalArgumentException, EntityNotFoundException {
        repository.delete(id);
    }

    public List<Product> getAllProducts() {
        return repository.getAll();
    }

    public List<Product> getProductsByCategory(Class<?> categoryClass) throws IllegalArgumentException {
        return repository.getByType(categoryClass);
    }

    public void initializeProducts() throws IllegalArgumentException, DuplicateEntityException {
        ProductServices.registerBeverage(
                new BeverageDTO("BE0001", "Coca-Cola", 5.99, 50, LocalDate.of(2024, 12, 31), 1.5, true, "Coca-Cola", 2.0, true, "Cola", "Coca-Cola"),
                repository
        );
        ProductServices.registerBeverage(
                new BeverageDTO("BE0002", "Pepsi", 4.99, 30, LocalDate.of(2024, 11, 30), 1.5, true, "Pepsi", 2.0, true, "Cola", "Pepsi"),
                repository
        );
        ProductServices.registerBeverage(
                new BeverageDTO("BE0003", "Fanta", 3.99, 20, LocalDate.of(2024, 10, 31), 1.5, true, "Fanta", 2.0, true, "Orange", "Coca-Cola"),
                repository
        );

        ProductServices.registerMeat(
                new MeatDTO("ME0001", "Picanha", 49.99, 10, LocalDate.of(2024, 12, 31), 1.0, true, "Beef", "Ribeye", "Brazil", true, "Beef", "Keep refrigerated"),
                repository
        );
        ProductServices.registerMeat(
                new MeatDTO("ME0002", "Fraldinha", 39.99, 15, LocalDate.of(2024, 11, 30), 1.0, true, "Beef", "Flank", "Brazil", true, "Beef", "Keep refrigerated"),
                repository
        );
        ProductServices.registerMeat(
                new MeatDTO("ME0003", "Alcatra", 44.99, 12, LocalDate.of(2024, 10, 31), 1.0, true, "Beef", "Sirloin", "Brazil", true, "Beef", "Keep refrigerated"),
                repository
        );

        ProductServices.registerFruit(
                new FruitDTO("FR0001", "Apple", 2.99, 100, LocalDate.of(2024, 12, 31), 0.2, true, "Nutritional info", "Fuji", "USA", true, "Plastic"),
                repository
        );
        ProductServices.registerFruit(
                new FruitDTO("FR0002", "Banana", 1.99, 150, LocalDate.of(2024, 11, 30), 0.2, true, "Nutritional info", "Cavendish", "Ecuador", true, "Plastic"),
                repository
        );
        ProductServices.registerFruit(
                new FruitDTO("FR0003", "Orange", 3.49, 80, LocalDate.of(2024, 10, 31), 0.2, true, "Nutritional info", "Navel", "Spain", true, "Plastic"),
                repository
        );

        ProductServices.registerProcessedFood(
                new ProcessedFoodDTO("PF0001", "Cereal", 4.99, 50, LocalDate.of(2024, 12, 31), 0.5, true, "Nutritional info", "Corn Flakes", "Kellogg's", true, "Breakfast"),
                repository
        );
        ProductServices.registerProcessedFood(
                new ProcessedFoodDTO("PF0002", "Pasta", 2.49, 30, LocalDate.of(2024, 11, 30), 0.5, true, "Nutritional info", "Spaghetti", "Barilla", true, "Dinner"),
                repository
        );
        ProductServices.registerProcessedFood(
                new ProcessedFoodDTO("PF0003", "Rice", 1.99, 20, LocalDate.of(2024, 10, 31), 0.5, true, "Nutritional info", "Basmati", "Tilda", true, "Lunch"),
                repository
        );

        ProductServices.registerHygieneProduct(
                new HygieneProductDTO("HY0001", "Shampoo", 9.99, 50, "Hair care", "Dove", true, "Apply and rinse", false, "Floral", 0.5),
                repository
        );
        ProductServices.registerHygieneProduct(
                new HygieneProductDTO("HY0002", "Soap", 1.99, 30, "Body wash", "Lux", true, "Use with water", false, "Lavender", 0.2),
                repository
        );
        ProductServices.registerHygieneProduct(
                new HygieneProductDTO("HY0003", "Toothpaste", 3.49, 20, "Oral care", "Colgate", true, "Brush teeth twice a day", false, "Mint", 0.1),
                repository
        );

        ProductServices.registerUtensil(
                new UtensilDTO("UT0001", "Spoon", 1.99, 100, "Stainless steel", "Kitchen", true, "Medium"),
                repository
        );
        ProductServices.registerUtensil(
                new UtensilDTO("UT0002", "Fork", 1.49, 80, "Stainless steel", "Kitchen", true, "Medium"),
                repository
        );
        ProductServices.registerUtensil(
                new UtensilDTO("UT0003", "Knife", 2.49, 60, "Stainless steel", "Kitchen", true, "Medium"),
                repository
        );
    }
}
