package service.impl;

import database.DBConnection;
import model.Order;
import model.OrderList;
import service.OrderTableService;

import java.sql.*;

public class OrderTableServiceImpl implements OrderTableService {
    private Connection connection;
    public OrderTableServiceImpl() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean addOrder(Order order) {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement("INSERT INTO orders(order_id, employee_id, total_cost, payment_type, order_date, customer_email, customer_name, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                stmt.setInt(1, order.getOrderId());
                stmt.setInt(2, order.getEmployeeId());
                stmt.setDouble(3, order.getTotalCost());
                stmt.setString(4, order.getPaymentType());
                stmt.setDate(5, Date.valueOf(order.getOrderDate()));
                stmt.setString(6, order.getCustomerEmail());
                stmt.setString(7, order.getCustomerName());
                stmt.setString(8, order.getPhoneNumber());
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
}
