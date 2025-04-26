package dtos;

import model.products.Product;
import model.products.food.Fruit;

import java.time.LocalDate;

public record FruitDTO(
        String cod,
        String name,
        Double price,
        Integer amount,
        LocalDate expirationDate,
        Double weight,
        Boolean refrigerated,
        String nutritionalInfo,
        String variety,
        String origin,
        Boolean seasonal,
        String packagingType
) {
    public static FruitDTO toDTO(Product product) {
        Fruit fruit = (Fruit) product;

        return new FruitDTO(
                fruit.getCod(),
                fruit.getName(),
                fruit.getPrice(),
                fruit.getAmount(),
                fruit.getExpirationDate(),
                fruit.getWeight(),
                fruit.isRefrigerated(),
                fruit.getNutritionalInfo(),
                fruit.getVariety(),
                fruit.getOrigin(),
                fruit.isSeasonal(),
                fruit.getPackagingType()
        );
    }

    public static Fruit toEntity(FruitDTO fruitDTO) {
        return new Fruit(
                fruitDTO.cod(),
                fruitDTO.name(),
                fruitDTO.price(),
                fruitDTO.amount(),
                fruitDTO.expirationDate(),
                fruitDTO.weight(),
                fruitDTO.refrigerated(),
                fruitDTO.nutritionalInfo(),
                fruitDTO.variety(),
                fruitDTO.origin(),
                fruitDTO.seasonal(),
                fruitDTO.packagingType()
        );
    }

    public FruitDTO withCode(String code) {
        return new FruitDTO(
                code,
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withName(String name) {
        return new FruitDTO(
                this.cod(),
                name,
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withPrice(Double price) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                price,
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withAmount(Integer amount) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                amount,
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withExpirationDate(LocalDate expirationDate) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                expirationDate,
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withWeight(Double weight) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                weight,
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withRefrigerated(Boolean refrigerated) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                refrigerated,
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withNutritionalInfo(String nutritionalInfo) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                nutritionalInfo,
                this.variety(),
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withVariety(String variety) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                variety,
                this.origin(),
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withOrigin(String origin) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                origin,
                this.seasonal(),
                this.packagingType()
        );
    }

    public FruitDTO withSeasonal(Boolean seasonal) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                seasonal,
                this.packagingType()
        );
    }

    public FruitDTO withPackagingType(String packagingType) {
        return new FruitDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.expirationDate(),
                this.weight(),
                this.refrigerated(),
                this.nutritionalInfo(),
                this.variety(),
                this.origin(),
                this.seasonal(),
                packagingType
        );
    }

}
