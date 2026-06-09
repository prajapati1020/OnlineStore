package online.store.services;

import online.store.model.Order;
import online.store.repositories.OrderRepository;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrders(Iterable<Order> orders){
        orderRepository.saveAll(orders);
    }
}
