package interfaces;

import model.products.Product;

import java.util.List;

public interface IProductRepository {
    Product searchByCod(String cod) throws IllegalArgumentException;
    Product searchByName(String name) throws IllegalArgumentException;
    List<Product> getByType(Class<?> clazz) throws IllegalArgumentException;
}
