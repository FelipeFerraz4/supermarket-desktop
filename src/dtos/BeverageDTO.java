package dtos;

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
) {}
