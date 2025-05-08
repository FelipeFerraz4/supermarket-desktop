package services;

import dtos.*;
import interfaces.IRepository;
import model.products.Product;
import model.products.Utensil;
import model.products.HygieneProduct;
import model.products.food.Beverage;
import model.products.food.ProcessedFood;
import model.products.food.Meat;
import model.products.food.Fruit;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class ProductServices {

    private static <T> void updateIfPresent(T value, Consumer<T> setter) {
        Optional.ofNullable(value).ifPresent(setter);
    }

    public static void registerBeverage(BeverageDTO beverageDTO, IRepository<Product> repository) {
        Beverage beverage = BeverageDTO.toEntity(beverageDTO);
        repository.add(beverage);
    }

    public static void updateBeverage(UUID id, BeverageDTO beverageDTO, IRepository<Product> repository) {
        Product product = repository.searchById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        if (!(product instanceof Beverage beverage)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não é uma bebida.");
        }

        updateIfPresent(beverageDTO.cod(), beverage::setCod);
        updateIfPresent(beverageDTO.name(), beverage::setName);
        updateIfPresent(beverageDTO.price(), beverage::setPrice);
        updateIfPresent(beverageDTO.amount(), beverage::setAmount);
        updateIfPresent(beverageDTO.expirationDate(), beverage::setExpirationDate);
        updateIfPresent(beverageDTO.weight(), beverage::setWeight);
        updateIfPresent(beverageDTO.refrigerated(), beverage::setRefrigerated);
        updateIfPresent(beverageDTO.nutritionalInfo(), beverage::setNutritionalInfo);
        updateIfPresent(beverageDTO.volume(), beverage::setVolume);
        updateIfPresent(beverageDTO.alcoholic(), beverage::setAlcoholic);
        updateIfPresent(beverageDTO.flavor(), beverage::setFlavor);
        updateIfPresent(beverageDTO.brand(), beverage::setBrand);

        repository.update(beverage);
    }

    public static void registerHygieneProduct(HygieneProductDTO hygieneProductDTO, IRepository<Product> repository) {
        HygieneProduct hygieneProduct = HygieneProductDTO.toEntity(hygieneProductDTO);
        repository.add(hygieneProduct);
    }

    public static void updateHygieneProduct(UUID id, HygieneProductDTO hygieneProductDTO, IRepository<Product> repository) {
        Product product = repository.searchById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        if (!(product instanceof HygieneProduct hygieneProduct)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não é um produto de higiene.");
        }

        updateIfPresent(hygieneProductDTO.cod(), hygieneProduct::setCod);
        updateIfPresent(hygieneProductDTO.name(), hygieneProduct::setName);
        updateIfPresent(hygieneProductDTO.price(), hygieneProduct::setPrice);
        updateIfPresent(hygieneProductDTO.amount(), hygieneProduct::setAmount);
        updateIfPresent(hygieneProductDTO.type(), hygieneProduct::setType);
        updateIfPresent(hygieneProductDTO.brand(), hygieneProduct::setBrand);
        updateIfPresent(hygieneProductDTO.forSensitiveSkin(), hygieneProduct::setForSensitiveSkin);
        updateIfPresent(hygieneProductDTO.usageInstructions(), hygieneProduct::setUsageInstructions);
        updateIfPresent(hygieneProductDTO.toxic(), hygieneProduct::setToxic);
        updateIfPresent(hygieneProductDTO.scent(), hygieneProduct::setScent);
        updateIfPresent(hygieneProductDTO.volume(), hygieneProduct::setVolume);

        repository.update(hygieneProduct);
    }

    public static void registerUtensil(UtensilDTO utensilDTO, IRepository<Product> repository) {
        Utensil utensil = UtensilDTO.toEntity(utensilDTO);
        repository.add(utensil);
    }

    public static void updateUtensil(UUID id, UtensilDTO utensilDTO, IRepository<Product> repository) {
        Product product = repository.searchById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        if (!(product instanceof Utensil utensil)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não é um utensílio.");
        }

        updateIfPresent(utensilDTO.cod(), utensil::setCod);
        updateIfPresent(utensilDTO.name(), utensil::setName);
        updateIfPresent(utensilDTO.price(), utensil::setPrice);
        updateIfPresent(utensilDTO.amount(), utensil::setAmount);
        updateIfPresent(utensilDTO.material(), utensil::setMaterial);
        updateIfPresent(utensilDTO.category(), utensil::setCategory);
        updateIfPresent(utensilDTO.reusable(), utensil::setReusable);
        updateIfPresent(utensilDTO.size(), utensil::setSize);

        repository.update(utensil);
    }

    public static void registerProcessedFood(ProcessedFoodDTO processedFoodDTO, IRepository<Product> repository) {
        ProcessedFood processedFood = ProcessedFoodDTO.toEntity(processedFoodDTO);
        repository.add(processedFood);
    }

    public static void updateProcessedFood(UUID id, ProcessedFoodDTO processedFoodDTO, IRepository<Product> repository) {
        Product product = repository.searchById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        if (!(product instanceof ProcessedFood processedFood)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não é um alimento processado.");
        }

        updateIfPresent(processedFoodDTO.cod(), processedFood::setCod);
        updateIfPresent(processedFoodDTO.name(), processedFood::setName);
        updateIfPresent(processedFoodDTO.price(), processedFood::setPrice);
        updateIfPresent(processedFoodDTO.amount(), processedFood::setAmount);
        updateIfPresent(processedFoodDTO.expirationDate(), processedFood::setExpirationDate);
        updateIfPresent(processedFoodDTO.weight(), processedFood::setWeight);
        updateIfPresent(processedFoodDTO.refrigerated(), processedFood::setRefrigerated);
        updateIfPresent(processedFoodDTO.nutritionalInfo(), processedFood::setNutritionalInfo);
        updateIfPresent(processedFoodDTO.category(), processedFood::setCategory);
        updateIfPresent(processedFoodDTO.brand(), processedFood::setBrand);
        updateIfPresent(processedFoodDTO.containsPreservatives(), processedFood::setContainsPreservatives);
        updateIfPresent(processedFoodDTO.cookingInstructions(), processedFood::setCookingInstructions);

        repository.update(processedFood);
    }

    public static void registerMeat(MeatDTO meatDTO, IRepository<Product> repository) {
        Meat meat = MeatDTO.toEntity(meatDTO);
        repository.add(meat);
    }

    public static void updateMeat(UUID id, MeatDTO meatDTO, IRepository<Product> repository) {
        Product product = repository.searchById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        if (!(product instanceof Meat meat)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não é uma carne.");
        }

        updateIfPresent(meatDTO.cod(), meat::setCod);
        updateIfPresent(meatDTO.name(), meat::setName);
        updateIfPresent(meatDTO.price(), meat::setPrice);
        updateIfPresent(meatDTO.amount(), meat::setAmount);
        updateIfPresent(meatDTO.expirationDate(), meat::setExpirationDate);
        updateIfPresent(meatDTO.weight(), meat::setWeight);
        updateIfPresent(meatDTO.refrigerated(), meat::setRefrigerated);
        updateIfPresent(meatDTO.nutritionalInfo(), meat::setNutritionalInfo);
        updateIfPresent(meatDTO.cutType(), meat::setCutType);
        updateIfPresent(meatDTO.isOrganic(), meat::setOrganic);
        updateIfPresent(meatDTO.animalType(), meat::setAnimalType);
        updateIfPresent(meatDTO.origin(), meat::setOrigin);
        updateIfPresent(meatDTO.storageInstructions(), meat::setStorageInstructions);

        repository.update(meat);
    }

    public static void registerFruit(FruitDTO fruitDTO, IRepository<Product> repository) {
        Fruit fruit = FruitDTO.toEntity(fruitDTO);
        repository.add(fruit);
    }

    public static void updateFruit(UUID id, FruitDTO fruitDTO, IRepository<Product> repository) {
        Product product = repository.searchById(id);
        if (product == null) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        if (!(product instanceof Fruit fruit)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não é uma fruta.");
        }

        updateIfPresent(fruitDTO.cod(), fruit::setCod);
        updateIfPresent(fruitDTO.name(), fruit::setName);
        updateIfPresent(fruitDTO.price(), fruit::setPrice);
        updateIfPresent(fruitDTO.amount(), fruit::setAmount);
        updateIfPresent(fruitDTO.expirationDate(), fruit::setExpirationDate);
        updateIfPresent(fruitDTO.weight(), fruit::setWeight);
        updateIfPresent(fruitDTO.refrigerated(), fruit::setRefrigerated);
        updateIfPresent(fruitDTO.nutritionalInfo(), fruit::setNutritionalInfo);
        updateIfPresent(fruitDTO.variety(), fruit::setVariety);
        updateIfPresent(fruitDTO.origin(), fruit::setOrigin);
        updateIfPresent(fruitDTO.seasonal(), fruit::setSeasonal);
        updateIfPresent(fruitDTO.packagingType(), fruit::setPackagingType);

        repository.update(fruit);
    }
}
