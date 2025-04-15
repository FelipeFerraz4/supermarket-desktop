package controllers;

import dtos.ProductDTO;
import model.products.Product;
import repository.product.ProductRepositoryHashMap;
import services.ProductServices;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductControllers {
    private final ProductRepositoryHashMap repository;

    public ProductControllers() {
        this.repository = new ProductRepositoryHashMap();
    }

    public void registerProduct(ProductDTO dto) {
        ProductServices.registerProduct(dto, repository);
    }

    public List<ProductDTO> listAll() {
        return repository.search().stream()
                .map(ProductServices::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO findById(UUID id) {
        return ProductServices.toDTO(repository.searchById(id));
    }

    public ProductDTO findByCod(String cod) {
        return ProductServices.toDTO(repository.searchByCod(cod));
    }

    public ProductDTO findByName(String name) {
        return ProductServices.toDTO(repository.searchByName(name));
    }

    public void updateStock(UUID id, int changeInAmount) {
        ProductServices.updateStock(id, changeInAmount, repository);
    }

    public void deleteProduct(UUID id) {
        repository.delete(id);
    }

    public List<ProductDTO> listByType(Class<?> clazz) {
        return repository.getByType(clazz).stream()
                .map(ProductServices::toDTO)
                .collect(Collectors.toList());
    }
}
