package repository.impl;

import database.DBConnection;
import model.Order;
import model.Product;
import repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private Connection connection;
    public ProductRepositoryImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean addProduct(Product product) {
        String query = "INSERT INTO products (product_id, name, category, size, price, quantity, image, supplier_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getSize());
            stmt.setDouble(5, product.getPrice());
            stmt.setInt(6, product.getQuantity());
            stmt.setString(7, product.getImage());
            stmt.setInt(8, product.getSupplierId());
            stmt.setDate(9, Date.valueOf(product.getCreatedAt()));
            stmt.setDate(10, Date.valueOf(product.getUpdatedAt()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Product employee = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("size"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("image"),
                        rs.getInt("supplier_id"),
                        rs.getDate("created_at").toLocalDate(),
                        rs.getDate("updated_at").toLocalDate(),
                        rs.getDouble("discount")
                );
                products.add(employee);
                System.out.println(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean removeProduct(int productId) {
        String query = "DELETE FROM products WHERE product_id = ?";

        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, productId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, category = ?, size = ?, price = ?, quantity = ?, image = ?, supplier_id = ?, created_at = ?, updated_at = ? WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getSize());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.setString(6, product.getImage());
            stmt.setInt(7, product.getSupplierId());
            stmt.setDate(8, Date.valueOf(product.getCreatedAt()));
            stmt.setDate(9, Date.valueOf(product.getUpdatedAt()));
            stmt.setInt(10, product.getProductId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product searchProduct(int productId) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        Product product = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getString("size"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getString("image"),
                            rs.getInt("supplier_id"),
                            rs.getDate("created_at").toLocalDate(),
                            rs.getDate("updated_at").toLocalDate(),
                            rs.getDouble("discount")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
