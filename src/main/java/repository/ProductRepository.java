package repository;

import model.Product;

import java.util.List;

public interface ProductRepository {
    boolean addProduct(Product product);
    boolean removeProduct(int productId);
    List<Product> getAllProducts();
    boolean updateProduct(Product product);
    Product searchProduct(int productId);
}
