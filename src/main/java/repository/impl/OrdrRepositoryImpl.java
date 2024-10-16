package repository.impl;

import database.DBConnection;
import model.Order;
import model.Product;
import repository.OrderRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdrRepositoryImpl implements OrderRepository {
    private Connection connection;
    public OrdrRepositoryImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Order> getAllOrder() {
        List<Order> products = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("employee_id"),
                        rs.getDouble("total_cost"),
                        rs.getString("payment_type"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getString("customer_email"),
                        rs.getString("customer_name"),
                        rs.getString("phone_number")
                );
                products.add(order);
                System.out.println(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return products;
    }
}
