package service;

import model.Product;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product);
    boolean removeProduct(int productId);
    boolean updateProduct(Product product);
    List<Product> getAllProducts();
    Product searchProduct(int productId);
}
