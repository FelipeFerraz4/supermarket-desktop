package dtos;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String cod,
        String name,
        double price) {
}
