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
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        if (product.getId() == null) throw new IllegalArgumentException("Product ID cannot be null.");
        if (products.containsKey(product.getId())) {
            throw new IllegalStateException("A product with ID " + product.getId() + " already exists.");
        }
        products.put(product.getId(), product);
    }

    @Override
    public List<Product> search() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product searchById(UUID id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        return products.get(id);
    }

    @Override
    public Product searchByCod(String cod) {
        if (cod == null || cod.isBlank()) throw new IllegalArgumentException("Code cannot be null or blank.");
        for (Product product : products.values()) {
            if (product.getCod().equalsIgnoreCase(cod)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product searchByName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank.");
        for (Product product : products.values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(Product updatedProduct) {
        if (updatedProduct == null) throw new IllegalArgumentException("Product cannot be null.");
        UUID id = updatedProduct.getId();
        if (id == null) throw new IllegalArgumentException("Product ID cannot be null.");
        if (!products.containsKey(id)) {
            throw new NoSuchElementException("Product with ID " + id + " not found for update.");
        }
        products.put(id, updatedProduct);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null.");
        if (!products.containsKey(id)) {
            throw new NoSuchElementException("Product with ID " + id + " not found for deletion.");
        }
        products.remove(id);
    }

    @Override
    public List<Product> getByType(Class<?> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Type cannot be null.");
        return products.values().stream()
                .filter(clazz::isInstance)
                .collect(Collectors.toList());
    }
}
