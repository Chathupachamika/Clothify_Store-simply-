package service.impl;

import model.Order;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.impl.OrdrRepositoryImpl;
import repository.impl.ProductRepositoryImpl;
import service.OrderService;

import java.util.List;

public class OrdertServiceImpl implements OrderService {
    private OrderRepository orderRepository = new OrdrRepositoryImpl();
    @Override
    public List<Order> getAllOrder() {
        return orderRepository.getAllOrder();
    }

}
