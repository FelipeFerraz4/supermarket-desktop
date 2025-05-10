package interfaces;

import exceptions.EntityNotFoundException;
import model.products.Product;

import java.util.List;

public interface IProductRepository extends IRepository<Product> {
    Product searchByCod(String cod) throws IllegalArgumentException, EntityNotFoundException;
    Product searchByName(String name) throws IllegalArgumentException, EntityNotFoundException;
    List<Product> getByType(Class<?> clazz) throws IllegalArgumentException;
}
