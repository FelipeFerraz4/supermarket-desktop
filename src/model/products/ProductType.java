package model.products;

import model.products.food.Beverage;
import model.products.food.ProcessedFood;
import model.products.food.Meat;
import model.products.food.Fruit;

public enum ProductType {
    DRINK,
    FOOD,
    MEAT,
    FRUIT,
    HYGIENE,
    UTENSIL;

    @Override
    public String toString() {
        return switch (this) {
            case DRINK -> "Bebida";
            case FOOD -> "Processados";
            case MEAT -> "Carne";
            case FRUIT -> "Fruta";
            case HYGIENE -> "Higiene";
            case UTENSIL -> "Utensílio";
        };
    }

    public static ProductType fromString(String type) {
        return switch (type.toLowerCase()) {
            case "bebida" -> DRINK;
            case "processados" -> FOOD;
            case "carne" -> MEAT;
            case "fruta" -> FRUIT;
            case "higiene" -> HYGIENE;
            case "utensílio", "utensilio" -> UTENSIL;
            default -> throw new IllegalArgumentException("Tipo de produto desconhecido: " + type);
        };
    }

    public static Class<?> getProductClass(ProductType type) {
        return switch (type) {
            case DRINK -> Beverage.class;
            case FOOD -> ProcessedFood.class;
            case MEAT -> Meat.class;
            case FRUIT -> Fruit.class;
            case HYGIENE -> HygieneProduct.class;
            case UTENSIL -> Utensil.class;
        };
    }
}
