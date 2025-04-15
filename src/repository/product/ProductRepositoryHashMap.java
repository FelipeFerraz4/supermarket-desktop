package repository.product;

import interfaces.IProductRepository;
import interfaces.IRepository;
import model.products.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepositoryHashMap implements IRepository<Product>, IProductRepository {
    private final Map<UUID, Product> products = new HashMap<>();

    @Override
    public void add(Product product) {
        products.putIfAbsent(product.getId(), product);
    }

    @Override
    public List<Product> search() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product searchById(UUID id) {
        return products.get(id);
    }

    @Override
    public Product searchByCod(String cod) {
        for (Product product : products.values()) {
            if (product.getCod().equalsIgnoreCase(cod)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product searchByName(String name) {
        for (Product product : products.values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(Product updatedProduct) {
        UUID id = updatedProduct.getId();
        if (products.containsKey(id)) {
            products.put(id, updatedProduct);
        }
    }

    @Override
    public void delete(UUID id) {
        products.remove(id);
    }

    @Override
    public List<Product> getByType(Class<?> clazz) {
        return products.values().stream()
                .filter(clazz::isInstance)
                .collect(Collectors.toList());
    }
}
