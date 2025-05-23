package repository.product;

import exceptions.DuplicateEntityException;
import exceptions.EntityNotFoundException;
import interfaces.IProductRepository;
import model.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductRepositoryArrayList implements IProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) throws IllegalArgumentException, DuplicateEntityException {
        if (product == null) {
            throw new IllegalArgumentException("A produto não pode ser nula.");
        }
        if (searchById(product.getId()) != null) {
            throw new DuplicateEntityException("Produto com ID já existe: " + product.getId());
        }
        products.add(product);
    }

    @Override
    public Product searchById(UUID id) throws IllegalArgumentException, EntityNotFoundException {
        if (id == null) throw new IllegalArgumentException("ID não pode ser nulo.");
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new EntityNotFoundException("Produto com ID " + id + " não encontrado.");
    }

    @Override
    public Product searchByCod(String cod) throws IllegalArgumentException, EntityNotFoundException {
        if (cod == null || cod.isBlank()) {
            throw new IllegalArgumentException("codigo inválido.");
        }
        for (Product product : products) {
            if (product.getCod().equalsIgnoreCase(cod)) {
                return product;
            }
        }
        throw new EntityNotFoundException("Produto com código " + cod + " não encontrado.");
    }

    @Override
    public Product searchByName(String name) throws IllegalArgumentException, EntityNotFoundException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        throw new EntityNotFoundException("Produto com nome " + name + " não encontrado.");
    }

    @Override
    public void update(Product updatedProduct) throws IllegalArgumentException, EntityNotFoundException {
        if (updatedProduct == null) {
            throw new IllegalArgumentException("Produto não pode ser nula.");
        }
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(updatedProduct.getId())) {
                products.set(i, updatedProduct);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new EntityNotFoundException("Produto com ID " + updatedProduct.getId() + " não encontrada para atualização.");
        }
    }

    @Override
    public void delete(UUID id) throws IllegalArgumentException, EntityNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        boolean removed = products.removeIf(product -> product.getId().equals(id));

        if (!removed) {
            throw new EntityNotFoundException("Produto com ID " + id + " não encontrado para exclusão.");
        }
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

    @Override
    public List<Product> getByType(Class<?> clazz) throws IllegalArgumentException {
        if (clazz == null) throw new IllegalArgumentException("Classe não pode ser nula.");
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (clazz.isInstance(product)) {
                result.add(product);
            }
        }
        return result;
    }
}
