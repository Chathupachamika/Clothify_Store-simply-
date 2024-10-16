package repository;

import model.Order;
import model.Product;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrder();
}
