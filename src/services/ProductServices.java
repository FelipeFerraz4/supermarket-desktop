package services;

import interfaces.IRepository;
import model.products.Product;
import model.products.Utensil;
import model.products.HygieneProduct;
import model.products.food.Beverage;
import model.products.food.ProcessedFood;
import model.products.food.Meat;
import model.products.food.Fruit;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class ProductServices {

    public static void registerBeverage(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            double volume, boolean alcoholic, String flavor, String brand, IRepository<Product> repository){
        Beverage beverage = new Beverage(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand);
        repository.add(beverage);
    }

    private static <T> void updateIfPresent(T value, Consumer<T> setter) {
        Optional.ofNullable(value).ifPresent(setter);
    }


    public static void updateBeverage(
            UUID id,
            String cod, String name, Double price, Integer amount,
            LocalDate expirationDate, Double weight, Boolean refrigerated, String nutritionalInfo,
            Double volume, Boolean alcoholic, String flavor, String brand, IRepository<Product> repository) {

        Product product = repository.searchById(id);

        if (product instanceof Beverage beverage) {
            updateIfPresent(cod, beverage::setCod);
            updateIfPresent(name, beverage::setName);
            updateIfPresent(price, beverage::setPrice);
            updateIfPresent(amount, beverage::setAmount);
            updateIfPresent(expirationDate, beverage::setExpirationDate);
            updateIfPresent(weight, beverage::setWeight);
            updateIfPresent(refrigerated, beverage::setRefrigerated);
            updateIfPresent(nutritionalInfo, beverage::setNutritionalInfo);
            updateIfPresent(volume, beverage::setVolume);
            updateIfPresent(alcoholic, beverage::setAlcoholic);
            updateIfPresent(flavor, beverage::setFlavor);
            updateIfPresent(brand, beverage::setBrand);

            repository.update(beverage);
        } else {
            throw new IllegalArgumentException("Produto com ID " + id + " não é uma bebida.");
        }
    }


    public static void registerHygieneProduct(
            String cod, String name, double price, int amount,
            String type, String brand, boolean forSensitiveSkin,
            String usageInstructions, boolean toxic, String scent, double volume, IRepository<Product> repository) {

        HygieneProduct hygieneProduct = new HygieneProduct(
                cod, name, price, amount,
                type, brand, forSensitiveSkin, usageInstructions, toxic, scent, volume
        );
        repository.add(hygieneProduct);
    }

    public static void updateHygieneProduct(
            UUID id,
            String cod, String name, double price, int amount,
            String type, String brand, boolean forSensitiveSkin,
            String usageInstructions, boolean toxic, String scent, double volume, IRepository<Product> repository) {

        Product product = repository.searchById(id);

        if (product instanceof HygieneProduct hygieneProduct) {
            hygieneProduct.setCod(cod);
            hygieneProduct.setName(name);
            hygieneProduct.setPrice(price);
            hygieneProduct.setAmount(amount);
            hygieneProduct.setForSensitiveSkin(forSensitiveSkin);
            hygieneProduct.setUsageInstructions(usageInstructions);
            // os campos finais não podem ser alterados diretamente

            repository.update(hygieneProduct);
        } else {
            throw new IllegalArgumentException("Produto com ID " + id + " não é um produto de higiene.");
        }
    }

    public static void registerUtensil(
            String cod, String name, double price, int amount,
            String material, String category, boolean isReusable, String size, IRepository<Product> repository) {

        Utensil utensil = new Utensil(
                cod, name, price, amount,
                material, category, isReusable, size
        );
        repository.add(utensil);
    }

    public static void updateUtensil(
            UUID id,
            String cod, String name, double price, int amount,
            String material, String category, boolean isReusable, String size, IRepository<Product> repository) {

        Product product = repository.searchById(id);

        if (product instanceof Utensil utensil) {
            utensil.setCod(cod);
            utensil.setName(name);
            utensil.setPrice(price);
            utensil.setAmount(amount);
            utensil.setMaterial(material);
            utensil.setCategory(category);
            utensil.setReusable(isReusable);
            utensil.setSize(size);

            repository.update(utensil);
        } else {
            throw new IllegalArgumentException("Produto com ID " + id + " não é um utensílio.");
        }
    }

    public static void registerProcessedFood(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String category, String brand, boolean containsPreservatives, String cookingInstructions,
            IRepository<Product> repository) {

        ProcessedFood processedFood = new ProcessedFood(
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                category, brand, containsPreservatives, cookingInstructions
        );

        repository.add(processedFood);
    }

    public static void updateProcessedFood(
            UUID id,
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String category, String brand, boolean containsPreservatives, String cookingInstructions,
            IRepository<Product> repository) {

        Product product = repository.searchById(id);

        if (product instanceof ProcessedFood processedFood) {
            processedFood.setCod(cod);
            processedFood.setName(name);
            processedFood.setPrice(price);
            processedFood.setAmount(amount);
            processedFood.setExpirationDate(expirationDate);
            processedFood.setWeight(weight);
            processedFood.setRefrigerated(refrigerated);
            processedFood.setNutritionalInfo(nutritionalInfo);
            processedFood.setCategory(category);
            processedFood.setBrand(brand);
            processedFood.setContainsPreservatives(containsPreservatives);
            processedFood.setCookingInstructions(cookingInstructions);

            repository.update(processedFood);
        } else {
            throw new IllegalArgumentException("Produto com ID " + id + " não é um alimento processado.");
        }
    }

    public static void registerMeat(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String cutType, String origin, boolean isOrganic, String animalType, String storageInstructions,
            IRepository<Product> repository) {

        Meat meat = new Meat(
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                cutType, origin, isOrganic, animalType, storageInstructions
        );

        repository.add(meat);
    }

    public static void updateMeat(
            UUID id,
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String origin, String storageInstructions,
            IRepository<Product> repository) {

        Product product = repository.searchById(id);

        if (product instanceof Meat meat) {
            meat.setCod(cod);
            meat.setName(name);
            meat.setPrice(price);
            meat.setAmount(amount);
            meat.setExpirationDate(expirationDate);
            meat.setWeight(weight);
            meat.setRefrigerated(refrigerated);
            meat.setNutritionalInfo(nutritionalInfo);

            // Os imutáveis não podem ser alterados (cutType, isOrganic, animalType)
            meat.setOrigin(origin);
            meat.setStorageInstructions(storageInstructions);

            repository.update(meat);
        } else {
            throw new IllegalArgumentException("Produto com ID " + id + " não é uma carne.");
        }
    }

    public static void registerFruit(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String variety, String origin, boolean seasonal, String packagingType,
            IRepository<Product> repository) {

        Fruit fruit = new Fruit(
                cod, name, price, amount,
                expirationDate, weight, refrigerated, nutritionalInfo,
                variety, origin, seasonal, packagingType
        );

        repository.add(fruit);
    }

    public static void updateFruit(
            UUID id,
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            String variety, String origin, boolean seasonal, String packagingType,
            IRepository<Product> repository) {

        Product product = repository.searchById(id);

        if (product instanceof Fruit fruit) {
            fruit.setCod(cod);
            fruit.setName(name);
            fruit.setPrice(price);
            fruit.setAmount(amount);
            fruit.setExpirationDate(expirationDate);
            fruit.setWeight(weight);
            fruit.setRefrigerated(refrigerated);
            fruit.setNutritionalInfo(nutritionalInfo);

            fruit.setVariety(variety);
            fruit.setOrigin(origin);
            fruit.setSeasonal(seasonal);
            fruit.setPackagingType(packagingType);

            repository.update(fruit);
        } else {
            throw new IllegalArgumentException("Produto com ID " + id + " não é uma fruta.");
        }
    }

}
