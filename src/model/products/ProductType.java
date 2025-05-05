package model.products;

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
}
