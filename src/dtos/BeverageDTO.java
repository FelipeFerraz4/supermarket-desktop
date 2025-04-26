package dtos;

import model.products.Product;
import model.products.food.Beverage;

import java.time.LocalDate;

public record BeverageDTO(
        String cod,
        String name,
        Double price,
        Integer amount,
        LocalDate expirationDate,
        Double weight,
        Boolean refrigerated,
        String nutritionalInfo,
        Double volume,
        Boolean alcoholic,
        String flavor,
        String brand
) {
    public static BeverageDTO toDTO(Product product) {
        Beverage beverage = (Beverage) product;

        return new BeverageDTO(
                beverage.getCod(),
                beverage.getName(),
                beverage.getPrice(),
                beverage.getAmount(),
                beverage.getExpirationDate(),
                beverage.getWeight(),
                beverage.isRefrigerated(),
                beverage.getNutritionalInfo(),
                beverage.getVolume(),
                beverage.isAlcoholic(),
                beverage.getFlavor(),
                beverage.getBrand()
        );
    }

    public static Beverage toEntity(BeverageDTO dto) {
        return new Beverage(
            dto.cod(),
            dto.name(),
            dto.price(),
            dto.amount(),
            dto.expirationDate(),
            dto.weight(),
            dto.refrigerated(),
            dto.nutritionalInfo(),
            dto.volume(),
            dto.alcoholic(),
            dto.flavor(),
            dto.brand()
        );
    }

    public BeverageDTO withCode(String code) {
        return new BeverageDTO(code, this.name(), this.price(), this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withName(String name) {
        return new BeverageDTO(this.cod(), name, this.price(), this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withPrice(Double price) {
        return new BeverageDTO(this.cod(), this.name(), price, this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withAmount(Integer amount) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), amount,
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withExpirationDate(LocalDate expirationDate) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                expirationDate, this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withWeight(Double weight) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                this.expirationDate(), weight, this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withRefrigerated(Boolean refrigerated) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                this.expirationDate(), this.weight(), refrigerated,
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withNutritionalInfo(String nutritionalInfo) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                nutritionalInfo, this.volume(), this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withVolume(Double volume) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), volume, this.alcoholic(),
                this.flavor(), this.brand());
    }

    public BeverageDTO withAlcoholic(Boolean alcoholic) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), alcoholic,
                this.flavor(), this.brand());
    }

    public BeverageDTO withFlavor(String flavor) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                flavor, this.brand());
    }

    public BeverageDTO withBrand(String brand) {
        return new BeverageDTO(this.cod(), this.name(), this.price(), this.amount(),
                this.expirationDate(), this.weight(), this.refrigerated(),
                this.nutritionalInfo(), this.volume(), this.alcoholic(),
                this.flavor(), brand);
    }
}
