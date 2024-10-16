package service.impl;

import model.Product;
import repository.ProductRepository;
import repository.impl.ProductRepositoryImpl;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public boolean addProduct(Product product) {
        try {
            return productRepository.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean removeProduct(int productId) {
        try {
            return productRepository.removeProduct(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateProduct(Product product) {
        try {
            return productRepository.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Product> getAllProducts() {
            return productRepository.getAllProducts();
    }

    @Override
    public Product searchProduct(int productId) {
        return productRepository.searchProduct(productId);
    }
}
