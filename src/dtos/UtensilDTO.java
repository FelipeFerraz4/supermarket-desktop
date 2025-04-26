package dtos;

import model.products.Product;
import model.products.Utensil;

public record UtensilDTO (
    String cod,
    String name,
    Double price,
    Integer amount,
    String material,
    String category,
    Boolean reusable,
    String size
){
    public static UtensilDTO toDTO(Product product){
        Utensil utensil = (Utensil) product;
        return new UtensilDTO(
                utensil.getCod(),
                utensil.getName(),
                utensil.getPrice(),
                utensil.getAmount(),
                utensil.getMaterial(),
                utensil.getCategory(),
                utensil.isReusable(),
                utensil.getSize()
        );
    }

    public static Utensil toEntity(UtensilDTO utensilDTO){
        return new Utensil(
                utensilDTO.cod(),
                utensilDTO.name(),
                utensilDTO.price(),
                utensilDTO.amount(),
                utensilDTO.material(),
                utensilDTO.category(),
                utensilDTO.reusable(),
                utensilDTO.size()
        );
    }

    public UtensilDTO withCode(String code) {
        return new UtensilDTO(
                code,
                name(),
                price(),
                amount(),
                material(),
                category(),
                reusable(),
                size()
        );
    }

    public UtensilDTO withName(String name) {
        return new UtensilDTO(
                cod(),
                name,
                price(),
                amount(),
                material(),
                category(),
                reusable(),
                size()
        );
    }

    public UtensilDTO withPrice(Double price) {
        return new UtensilDTO(
                cod(),
                name(),
                price,
                amount(),
                material(),
                category(),
                reusable(),
                size()
        );
    }

    public UtensilDTO withAmount(Integer amount) {
        return new UtensilDTO(
                cod(),
                name(),
                price(),
                amount,
                material(),
                category(),
                reusable(),
                size()
        );
    }

    public UtensilDTO withMaterial(String material) {
        return new UtensilDTO(
                cod(),
                name(),
                price(),
                amount(),
                material,
                category(),
                reusable(),
                size()
        );
    }

    public UtensilDTO withCategory(String category) {
        return new UtensilDTO(
                cod(),
                name(),
                price(),
                amount(),
                material(),
                category,
                reusable(),
                size()
        );
    }

    public UtensilDTO withReusable(Boolean reusable) {
        return new UtensilDTO(
                cod(),
                name(),
                price(),
                amount(),
                material(),
                category(),
                reusable,
                size()
        );
    }

    public UtensilDTO withSize(String size) {
        return new UtensilDTO(
                cod(),
                name(),
                price(),
                amount(),
                material(),
                category(),
                reusable(),
                size
        );
    }
}
