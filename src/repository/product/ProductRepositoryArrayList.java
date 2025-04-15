package repository.product;

import interfaces.IProductRepository;
import interfaces.IRepository;
import model.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductRepositoryArrayList implements IRepository<Product>, IProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) {
        if (searchById(product.getId()) == null) {
            products.add(product);
        }
    }

    @Override
    public List<Product> search() {
        return new ArrayList<>(products);
    }

    @Override
    public Product searchById(UUID id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product searchByCod(String cod) {
        for (Product product : products) {
            if (product.getCod().equalsIgnoreCase(cod)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product searchByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(updatedProduct.getId())) {
                products.set(i, updatedProduct);
                return;
            }
        }
    }

    @Override
    public void delete(UUID id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public List<Product> getByType(Class<?> clazz) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (clazz.isInstance(product)) {
                result.add(product);
            }
        }
        return result;
    }
}
