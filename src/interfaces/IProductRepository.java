package interfaces;

import model.products.Product;

import java.util.List;

public interface IProductRepository {
    Product searchByCod(String cod);
    Product searchByName(String name);
    List<Product> getByType(Class<?> clazz);
}
