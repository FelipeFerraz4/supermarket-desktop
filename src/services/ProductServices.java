package services;

import interfaces.IRepository;
import model.products.Product;
import model.products.Utensil;
import model.products.HygieneProduct;
import model.products.food.Beverage;

import java.time.LocalDate;
import java.util.UUID;

public class ProductServices {

    public static void registerBeverage(
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            double volume, boolean alcoholic, String flavor, String brand, IRepository<Product> repository){
        Beverage beverage = new Beverage(cod, name, price, amount, expirationDate, weight, refrigerated, nutritionalInfo, volume, alcoholic, flavor, brand);
        repository.add(beverage);
    }

    public static void updateBeverage(
            UUID id,
            String cod, String name, double price, int amount,
            LocalDate expirationDate, double weight, boolean refrigerated, String nutritionalInfo,
            double volume, boolean alcoholic, String flavor, String brand, IRepository<Product> repository) {
        Product product = repository.searchById(id);

        if (product instanceof Beverage beverage) {
            beverage.setCod(cod);
            beverage.setName(name);
            beverage.setPrice(price);
            beverage.setAmount(amount);
            beverage.setExpirationDate(expirationDate);
            beverage.setWeight(weight);
            beverage.setRefrigerated(refrigerated);
            beverage.setNutritionalInfo(nutritionalInfo);
            beverage.setVolume(volume);
            beverage.setAlcoholic(alcoholic);
            beverage.setFlavor(flavor);
            beverage.setBrand(brand);

            repository.update(beverage);
        } else {
            throw new IllegalArgumentException("Produto com ID " + id + " não é uma bebida.");
        }
    }

    // HygieneProduct
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


}
