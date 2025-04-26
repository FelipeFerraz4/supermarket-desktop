package dtos;

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
}
