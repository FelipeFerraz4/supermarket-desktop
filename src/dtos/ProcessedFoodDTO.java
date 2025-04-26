package dtos;

import model.products.Product;
import model.products.food.ProcessedFood;

import java.time.LocalDate;

public record ProcessedFoodDTO (
    String cod,
    String name,
    Double price,
    Integer amount,
    LocalDate expirationDate,
    Double weight,
    Boolean refrigerated,
    String nutritionalInfo,
    String category,
    String brand,
    Boolean containsPreservatives,
    String cookingInstructions
){
    public static ProcessedFoodDTO toDTO(Product product){
        ProcessedFood processedFood = (ProcessedFood) product;

        return new ProcessedFoodDTO(
                processedFood.getCod(),
                processedFood.getName(),
                processedFood.getPrice(),
                processedFood.getAmount(),
                processedFood.getExpirationDate(),
                processedFood.getWeight(),
                processedFood.isRefrigerated(),
                processedFood.getNutritionalInfo(),
                processedFood.getCategory(),
                processedFood.getBrand(),
                processedFood.isContainsPreservatives(),
                processedFood.getCookingInstructions()
        );
    }

    public static ProcessedFood toEntity(ProcessedFoodDTO processedFoodDTO){
        return new ProcessedFood(
                processedFoodDTO.cod(),
                processedFoodDTO.name(),
                processedFoodDTO.price(),
                processedFoodDTO.amount(),
                processedFoodDTO.expirationDate(),
                processedFoodDTO.weight(),
                processedFoodDTO.refrigerated(),
                processedFoodDTO.nutritionalInfo(),
                processedFoodDTO.category(),
                processedFoodDTO.brand(),
                processedFoodDTO.containsPreservatives(),
                processedFoodDTO.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withCode(String code) {
        return new ProcessedFoodDTO(
                code,
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withName(String name) {
        return new ProcessedFoodDTO(
                this.cod(),
                name,
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withPrice(Double price) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                price,
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withAmount(Integer amount) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                amount,
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withExpirationDate(LocalDate expirationDate) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                expirationDate,
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withWeight(Double weight) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                weight,
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withRefrigerated(Boolean refrigerated) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                refrigerated,
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withNutritionalInfo(String nutritionalInfo) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                nutritionalInfo,
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withCategory(String category) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                category,
                this.brand(),
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withBrand(String brand) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                brand,
                this.containsPreservatives(),
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withContainsPreservatives(Boolean containsPreservatives) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                containsPreservatives,
                this.cookingInstructions()
        );
    }

    public ProcessedFoodDTO withCookingInstructions(String cookingInstructions) {
        return new ProcessedFoodDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.category(),
                this.brand(),
                this.containsPreservatives(),
                cookingInstructions
        );
    }
}
