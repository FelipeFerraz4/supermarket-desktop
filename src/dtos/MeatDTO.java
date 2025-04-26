package dtos;

import model.products.Product;
import model.products.food.Meat;

import java.time.LocalDate;

public record MeatDTO (
    String cod,
    String name,
    Double price,
    Integer amount,
    LocalDate expirationDate,
    Double weight,
    Boolean refrigerated,
    String nutritionalInfo,
    String cutType,
    String origin,
    Boolean isOrganic,
    String animalType,
    String storageInstructions
){
    public static MeatDTO toDTO(Product product){
        Meat meat = (Meat) product;

        return new MeatDTO(
                meat.getCod(),
                meat.getName(),
                meat.getPrice(),
                meat.getAmount(),
                meat.getExpirationDate(),
                meat.getWeight(),
                meat.isRefrigerated(),
                meat.getNutritionalInfo(),
                meat.getCutType(),
                meat.getOrigin(),
                meat.isOrganic(),
                meat.getAnimalType(),
                meat.getStorageInstructions()
        );
    }

    public static Meat toEntity(MeatDTO meatDTO){
        return new Meat(
                meatDTO.cod(),
                meatDTO.name(),
                meatDTO.price(),
                meatDTO.amount(),
                meatDTO.expirationDate(),
                meatDTO.weight(),
                meatDTO.refrigerated(),
                meatDTO.nutritionalInfo(),
                meatDTO.cutType(),
                meatDTO.origin(),
                meatDTO.isOrganic(),
                meatDTO.animalType(),
                meatDTO.storageInstructions()
        );
    }

    public MeatDTO withCode(String code) {
        return new MeatDTO(
                code,
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withName(String name) {
        return new MeatDTO(
                this.cod(),
                name,
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withPrice(Double price) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                price,
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withAmount(Integer amount) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                amount,
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withExpirationDate(LocalDate expirationDate) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                expirationDate,
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withWeight(Double weight) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                weight,
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withRefrigerated(Boolean refrigerated) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                refrigerated,
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withNutritionalInfo(String nutritionalInfo) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                nutritionalInfo,
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withCutType(String cutType) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                cutType,
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withOrigin(String origin) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                origin,
                this.isOrganic(),
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withIsOrganic(Boolean isOrganic) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                isOrganic,
                this.animalType(),
                this.storageInstructions()
        );
    }

    public MeatDTO withAnimalType(String animalType) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                animalType,
                this.storageInstructions()
        );
    }

    public MeatDTO withStorageInstructions(String storageInstructions) {
        return new MeatDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.cutType(),
                this.origin(),
                this.isOrganic(),
                this.animalType(),
                storageInstructions
        );
    }
}
