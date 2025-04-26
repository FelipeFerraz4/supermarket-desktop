package dtos;

import model.products.HygieneProduct;
import model.products.Product;

public record HygieneProductDTO (
     String cod,
     String name,
     Double price,
     Integer amount,
     String type,
     String brand,
     Boolean forSensitiveSkin,
     String usageInstructions,
     Boolean toxic,
     String scent,
     Double volume
){
    public static HygieneProductDTO toDto(Product product){
        HygieneProduct hygieneProduct = (HygieneProduct) product;

        return new HygieneProductDTO(
                product.getCod(),
                product.getName(),
                product.getPrice(),
                product.getAmount(),
                hygieneProduct.getType(),
                hygieneProduct.getBrand(),
                hygieneProduct.isForSensitiveSkin(),
                hygieneProduct.getUsageInstructions(),
                hygieneProduct.isToxic(),
                hygieneProduct.getScent(),
                hygieneProduct.getVolume()
        );
    }

    public static HygieneProduct toEntity(HygieneProductDTO hygieneProductDTO){
        return new HygieneProduct(
                hygieneProductDTO.cod(),
                hygieneProductDTO.name(),
                hygieneProductDTO.price(),
                hygieneProductDTO.amount(),
                hygieneProductDTO.type(),
                hygieneProductDTO.brand(),
                hygieneProductDTO.forSensitiveSkin(),
                hygieneProductDTO.usageInstructions(),
                hygieneProductDTO.toxic(),
                hygieneProductDTO.scent(),
                hygieneProductDTO.volume()
        );
    }

    public HygieneProductDTO withCode(String code) {
        return new HygieneProductDTO(
                code,
                this.name(),
                this.price(),
                this.amount(),
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withName(String name) {
        return new HygieneProductDTO(
                this.cod(),
                name,
                this.price(),
                this.amount(),
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withPrice(Double price) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                price,
                this.amount(),
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withAmount(Integer amount) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                amount,
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withType(String type) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                type,
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withBrand(String brand) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.type(),
                brand,
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withForSensitiveSkin(Boolean forSensitiveSkin) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.type(),
                this.brand(),
                forSensitiveSkin,
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withUsageInstructions(String usageInstructions) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                usageInstructions,
                this.toxic(),
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withToxic(Boolean toxic) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                toxic,
                this.scent(),
                this.volume()
        );
    }

    public HygieneProductDTO withScent(String scent) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                scent,
                this.volume()
        );
    }

    public HygieneProductDTO withVolume(Double volume) {
        return new HygieneProductDTO(
                this.cod(),
                this.name(),
                this.price(),
                this.amount(),
                this.type(),
                this.brand(),
                this.forSensitiveSkin(),
                this.usageInstructions(),
                this.toxic(),
                this.scent(),
                volume
        );
    }
}
