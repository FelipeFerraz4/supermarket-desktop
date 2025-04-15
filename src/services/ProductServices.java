package services;

import dtos.ProductDTO;
import model.products.Product;
import repository.product.ProductRepositoryHashMap;

import java.util.UUID;

public class ProductServices {

    public static void registerProduct(ProductDTO dto, ProductRepositoryHashMap repository) {

        Product product = new Product(dto.cod(), dto.name(), dto.price(), 0);
        repository.add(product);
    }

    public static void updateStock(UUID id, int changeInAmount, ProductRepositoryHashMap repository) {
        Product product = repository.searchById(id);
        if (product != null) {
            product.updateStock(changeInAmount);
            repository.update(product);
        }
    }

    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getCod(),
                product.getName(),
                product.getPrice()
        );
    }

}
